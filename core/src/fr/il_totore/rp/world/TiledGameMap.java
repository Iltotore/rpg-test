package fr.il_totore.rp.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.entity.Entity;
import fr.il_totore.rp.util.Collision;
import fr.il_totore.rp.util.Comparison;
import fr.il_totore.rp.util.TernaryValue;

import java.util.*;

public class TiledGameMap extends GameMap {

    private List<List<List<Tile>>> loadedTiles = new ArrayList<>();
    private TiledMap map;
    private TiledMapRenderer renderer;

    public TiledGameMap(TiledMap map, TiledMapRenderer renderer, List<Entity> entities) {
        super(entities);
        this.map = map;
        this.renderer = renderer;
    }

    public void load() {
        for(int z = 0; z < map.getLayers().getCount(); z++) {
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(z);
            List<List<Tile>> columns = new ArrayList<>();
            for(int x = 0; x < layer.getWidth(); x++) {
                List<Tile> tiles = new ArrayList<>();
                for(int y = 0; y < layer.getHeight(); y++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                    Tile tile;
                    if(cell == null || cell.getTile() == null) {
                        tile = new UnknownTile(new Vector3(x, y, z));
                    } else {
                        MapProperties properties = cell.getTile().getProperties();
                        tile = TileType.getById(properties.containsKey("Type") ? properties.get("Type", String.class) : "unknown")
                                .createTile(new Vector3(x, y, z));
                    }
                    tiles.add(tile);
                }
                columns.add(tiles);
            }
            loadedTiles.add(columns);
        }
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);

        renderer.setView(camera);
        renderer.render();
        batch.begin();
        super.render(camera, batch);
        batch.end();
    }

    @Override
    public void dispose() {
        map.dispose();
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return map.getLayers().getCount();
    }

    @Override
    public List<List<Tile>> getLayer(int z) {
        return loadedTiles.get(z);
    }

    @Override
    public Optional<Tile> getTile(Vector3 position) {
        if(position.x < 0 || position.y < 0 || position.z < 0) return Optional.empty();
        if(getLayers() <= (int) position.z) return Optional.empty();
        List<List<Tile>> layer = getLayer((int) position.z);
        if(layer.size() <= (int) position.x) return Optional.empty();
        List<Tile> line = layer.get((int) position.x);
        if(line.size() <= (int) position.y) return Optional.empty();
        return Optional.of(line.get((int) position.y));
    }

    @Override
    public Set<Collision> getTilesBetween(Set<Collision> set, Vector3 start, Vector3 end) {
        Comparison<Float> compX = Comparison.of(start.x, end.x);
        Comparison<Float> compY = Comparison.of(start.y, end.y);
        Comparison<Float> compZ = Comparison.of(start.z, end.z);
        for(float z = compZ.getMin(); z <= compZ.getMax(); z++) {
            for(float x = compX.getMin(); x <= compX.getMax(); x++) {
                for(float y = compY.getMin(); y <= compY.getMax(); y++) {
                    float diffX = x-start.x;
                    float diffY = y-start.y;
                    getTile(new Vector3(x, y, z)).ifPresent(tile -> set.add(new Collision(tile, new Vector2(diffX, diffY))));
                }
            }
        }
        return set;
    }

    @Override
    public Optional<Collision> findFirstCollision(Vector3 position, Rectangle boundingBox, Vector3 end) {
        Vector3 pos = position.cpy();
        Vector3 direction = end.cpy().sub(pos).nor();
        Rectangle intBB = new Rectangle((int) position.x, (int) position.y, boundingBox.width, boundingBox.height);
        float dst = pos.dst(end);
        float angleRad = new Vector2(direction.x, direction.y).angleRad();
        double cos = Math.round(Math.cos(angleRad)*1E6);
        double sin = Math.round(Math.sin(angleRad)*1E6);
        TernaryValue right = TernaryValue.of(cos > 0).orElse(cos < 0);
        TernaryValue up = TernaryValue.of(sin > 0).orElse(sin < 0);
        Set<Collision> set = new HashSet<>();
        for(float alpha = 0; alpha < dst; alpha++) {
            pos.add(direction);
            intBB.setPosition(pos.x, pos.y);
            Optional<Collision> collidingTile = getCollidingTile(set, position, boundingBox, right, up);
            if(collidingTile.isPresent()) return collidingTile;
        }

        return Optional.empty();
    }

    private Optional<Collision> getCollidingTile(Set<Collision> set, Vector3 position, Rectangle boundingBox, TernaryValue right, TernaryValue up){
        if(right.isTrue()) getTilesBetween(set, new Vector3(position.x+boundingBox.width, position.y, position.z), new Vector3(position.x+boundingBox.width, position.y+boundingBox.height, position.z));
        if(right.isFalse()) getTilesBetween(set, new Vector3(position.x, position.y, position.z), new Vector3(position.x, position.y+boundingBox.height, position.z));

        if(up.isTrue()) getTilesBetween(set, new Vector3(position.x, position.y+boundingBox.height, position.z), new Vector3(position.x+boundingBox.width, position.y+boundingBox.height, position.z));
        if(up.isFalse()) getTilesBetween(set, new Vector3(position.x, position.y, position.z), new Vector3(position.x+boundingBox.width, position.y, position.z));


        return set.stream()
                .filter(collision -> collision.getTile().isColliding(position, boundingBox))
                .findAny();
    }
}
