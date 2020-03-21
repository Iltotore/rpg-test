package fr.il_totore.rp.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.entity.Entity;
import fr.il_totore.rp.util.Physics;

import java.util.List;
import java.util.function.BiFunction;

public abstract class GameMap {

    private static final Physics Y_GRAVITY = Physics.builder()
            //.gravityY(-1)
            .inertness(1f)
            .build();

    private List<Entity> entities;
    private BiFunction<Vector3, Vector3, Vector3> physics = Y_GRAVITY;

    public GameMap(List<Entity> entities){
        this.entities = entities;
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }

    public void tick(float delta) {
        for (Entity entity : entities) {
            entity.tick(Y_GRAVITY, delta);
        }
    }


    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();
    public abstract List<List<Tile>> getLayer(int y);
    public abstract Tile getTile(Vector3 vector3);
    public abstract List<Tile> getTilesBetween(List<Tile> list, Vector3 start, Vector3 end);
    public abstract void dispose();
}
