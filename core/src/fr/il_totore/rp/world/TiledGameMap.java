package fr.il_totore.rp.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.entity.Entity;

import java.util.ArrayList;
import java.util.List;

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
                        tile = TileType.getById(cell.getTile().getId())
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
    public Tile getTile(Vector3 position) {
        return getLayer((int) position.z).get((int) position.x).get((int) position.y);
    }

    @Override
    public List<Tile> getTilesBetween(List<Tile> list, Vector3 start, Vector3 end) {
        float interpolation = 1/end.cpy().sub(start).len();
        for(float f = 0; f < 1; f+=interpolation){
            list.add(getTile(start.cpy().lerp(end, f)));
        }
        return list;
    }
}
