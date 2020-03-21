package fr.il_totore.rp.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.util.TexturedSprite;
import fr.il_totore.rp.world.GameMap;
import fr.il_totore.rp.world.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public abstract class Entity {

    private EntityType<? extends Entity> type;
    private Rectangle boundingBox;
    private GameMap map;
    private Vector3 position;
    private int frame = 0;
    private float maxSpeed = 14;
    private Vector3 velocity = new Vector3();

    public Entity(EntityType<? extends Entity> type, Rectangle boundingBox, GameMap map, Vector3 position){
        this.type = type;
        this.boundingBox = boundingBox;
        this.map = map;
        this.position = position;
    }

    public EntityType<? extends Entity> getType() {
        return type;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public void render(SpriteBatch batch){
        TexturedSprite sprite = type.getSprite();
        Texture texture = sprite.getTexture(frame++);
        batch.draw(texture, position.x+boundingBox.getX(), position.z+boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
    }

    public void tick(BiFunction<Vector3, Vector3, Vector3> physics, float delta){
        position.set(physics.apply(position, velocity));
    }

    public void move(Vector3 movement){
        Vector3 nextPosition = new Vector3(position).add(movement);
        Vector3 direction = movement.nor();
        List<Tile> tiles = map.getTilesBetween(new ArrayList<>(), position, nextPosition);
        Optional<Tile> tileOptional = tiles.stream().filter(Tile::isCollidable).findFirst();
        tileOptional.ifPresent(tile -> nextPosition.set(tile.getPosition().add(0.5f).sub(direction.x*0.5f, direction.y*0.5f, direction.z*0.5f)));
        position.set(nextPosition);
    }

    public abstract boolean isDead();
}
