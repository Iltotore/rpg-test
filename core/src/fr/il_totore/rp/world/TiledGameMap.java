package fr.il_totore.rp.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.entity.Entity;
import fr.il_totore.rp.util.Comparison;

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

    public void load(){
        for(int y = 0; y < getLayers(); y++){
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(y);
            List<List<Tile>> lineList = new ArrayList<>();
            for(int x = 0; x < layer.getWidth(); x++) {
                List<Tile> columnList = new ArrayList<>();
                for(int z = 0; z < layer.getHeight(); z++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, z);
                    Tile tile = TileType.getById(cell.getTile().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid type id: " + cell.getTile().getId()))
                            .createTile(new Vector3(x, y, z));
                    columnList.add(tile);
                }
                lineList.add(columnList);
            }
            loadedTiles.add(lineList);
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
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getLayers() {
        return 0;
    }

    @Override
    public List<List<Tile>> getLayer(int y) {
        return loadedTiles.get(y);
    }

    @Override
    public Tile getTile(Vector3 position) {
        return getLayer((int) position.y).get((int) position.x).get((int) position.z);
    }

    @Override
    public List<Tile> getTilesBetween(List<Tile> list, Vector3 start, Vector3 end) {
        Comparison<Float> compX = Comparison.of(start.x, end.x);
        Comparison<Float> compY = Comparison.of(start.y, end.y);
        Comparison<Float> compZ = Comparison.of(start.z, end.z);
        for(int y = compY.getMin().intValue(); y < compY.getMax(); y++) {
            for(int x = compX.getMin().intValue(); x < compX.getMax(); x++) {
                for(int z = compZ.getMin().intValue(); z < compZ.getMax(); z++){
                    list.add(getTile(new Vector3(x, y, z)));
                }
            }
        }
        return list;
    }
}
