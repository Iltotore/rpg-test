package fr.il_totore.rp.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.entity.Entity;
import fr.il_totore.rp.util.CompositeVelocity;
import fr.il_totore.rp.util.Physics;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class GameMap {

    private static final Physics Z_GRAVITY = Physics.builder()
            //.gravityZ(-1)
            .inertness(0.75f)
            .build();

    private List<Entity> entities;
    private Consumer<CompositeVelocity> physics = Z_GRAVITY;

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
            entity.tick(Z_GRAVITY, delta);
        }
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public Rectangle getRectangle(){
        return new Rectangle(-1, -1, getWidth(), getHeight());
    }


    //TODO findFirstCollision
    public abstract void load();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();
    public abstract List<List<Tile>> getLayer(int y);
    public abstract Optional<Tile> getTile(Vector3 vector3);
    public abstract Optional<Tile> findFirstCollision(Vector3 position, Rectangle boundingBox, Vector3 end);
    public abstract List<Tile> getTilesBetween(List<Tile> list, Vector3 start, Vector3 end, Rectangle rectangle);
    public abstract void dispose();
}
