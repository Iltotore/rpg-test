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
import fr.il_totore.rp.util.Comparison;
import fr.il_totore.rp.util.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return ((TiledMapTileLayer)map.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer)map.getLayers().get(0)).getHeight();
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
        if(getLayers() <= (int)position.z) return Optional.empty();
        List<List<Tile>> layer = getLayer((int) position.z);
        if(layer.size() <= (int)position.x) return Optional.empty();
        List<Tile> line = layer.get((int) position.x);
        if(line.size() <= (int)position.y) return Optional.empty();
        return Optional.of(line.get((int)position.y));
    }

    @Override
    public List<Tile> getTilesBetween(List<Tile> list, Vector3 start, Vector3 end, Rectangle rectangle) {
        Comparison<Float> compX = Comparison.of(start.x, end.x);
        Comparison<Float> compY = Comparison.of(start.y, end.y);
        Comparison<Float> compZ = Comparison.of(start.z, end.z);
        for(int z = compZ.getMin().intValue(); z < compZ.getMax(); z++) {
            for(int x = compX.getMin().intValue(); x < compX.getMax(); x++) {
                for(int y = compY.getMin().intValue(); y < compY.getMax(); y++) {
                    /*list.add(getTile(new Vector3(x+rectangle.x, y+rectangle.y, z)));
                    list.add(getTile(new Vector3(x+rectangle.width, y+rectangle.y, z)));
                    list.add(getTile(new Vector3(x+rectangle.x, y+rectangle.height, z)));
                    list.add(getTile(new Vector3(x+rectangle.width, y+rectangle.height, z)));*/
                }
            }
        }
        return list;
    }

    @Override
    public Optional<Tile> findFirstCollision(Vector3 position, Rectangle boundingBox, Vector3 end) {
        Vector3 intPos = new Vector3((int)position.x, (int)position.y, (int)position.z);
        Vector3 intEnd = new Vector3((int)end.x, (int)end.y, (int)end.z);
        Vector3 direction = intEnd.sub(intPos).nor();
        Rectangle intBB = new Rectangle((int)position.x, (int)position.y, (int)boundingBox.width, (int)boundingBox.height);
        float dst = intPos.dst(intEnd);
        float angleRad = new Vector2(direction.x, direction.y).angleRad();
        boolean right = Math.cos(angleRad) > 0;
        boolean up = Math.sin(angleRad) > 0;
        Condition<Tile> condition = Condition.empty();
        for(float alpha = 0; alpha < dst; alpha++){
            intPos.add(direction);
            intBB.setPosition(intPos.x, intPos.y);
            if(right || up) condition = Condition.of(getTile(new Vector3(intBB.x+intBB.width, intBB.y+intBB.height, intPos.z)))
                    .onlyIf(tile -> tile.isColliding(position, boundingBox));
            if(condition.isPresent()) return condition.asOptional();

            if(right || !up) condition = Condition.of(getTile(new Vector3(intBB.x+intBB.width, intBB.y, intPos.z)))
                    .onlyIf(tile -> tile.isColliding(position, boundingBox));
            if(condition.isPresent()) return condition.asOptional();

            if(!right || up) condition = Condition.of(getTile(new Vector3(intBB.x, intBB.y+intBB.height, intPos.z)))
                    .onlyIf(tile -> tile.isColliding(position, boundingBox));
            if(condition.isPresent()) return condition.asOptional();

            if(!right || !up) condition = Condition.of(getTile(new Vector3(intBB.x, intBB.y, intPos.z)))
                    .onlyIf(tile -> tile.isColliding(position, boundingBox));
            if(condition.isPresent()) return condition.asOptional();
        }

        return Optional.empty();
    }
}
