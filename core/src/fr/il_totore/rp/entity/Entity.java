package fr.il_totore.rp.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.util.Collision;
import fr.il_totore.rp.util.CompositeVelocity;
import fr.il_totore.rp.util.TexturedSprite;
import fr.il_totore.rp.world.GameMap;
import fr.il_totore.rp.world.Tile;

import java.util.Optional;
import java.util.function.Consumer;

public abstract class Entity {

    private EntityType<? extends Entity> type;
    private Rectangle boundingBox;
    private GameMap map;
    private Vector3 position;
    private int frame = 0;
    private float maxSpeed = 14;
    private final CompositeVelocity velocity = new CompositeVelocity();

    public Entity(EntityType<? extends Entity> type, Rectangle boundingBox, GameMap map, Vector3 position) {
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
        this.position.set(position);
    }

    public CompositeVelocity getVelocity() {
        return velocity;
    }

    public void setVelocity(String key, Vector3 vector) {
        velocity.setComponent(key, vector);
    }

    public void setVelocity(Vector3 vector) {
        setVelocity(CompositeVelocity.BASE, vector);
    }

    public void render(SpriteBatch batch) {
        TexturedSprite sprite = type.getSprite();
        Texture texture = sprite.getTexture(frame++);
        batch.draw(texture, (position.x + boundingBox.getX()) * 32, (position.y + boundingBox.getY()) * 32, boundingBox.getWidth() * 32, boundingBox.getHeight() * 32);
    }

    public void tick(Consumer<CompositeVelocity> physics, float delta) {
        move(velocity.toFinalVector());
        physics.accept(velocity);
    }


    public void move(Vector3 movement) {
        Vector3 nextPos = position.cpy().add(movement);
        Vector3 direction = nextPos.cpy().sub(position).nor();
        moveTo(nextPos, new Vector2(direction.x, direction.y));
    }

    public void moveTo(Vector3 nextPos, Vector2 direction) {
        Vector3 nextPosition = nextPos.cpy();

        Optional<Collision> tileOptional = map.findFirstCollision(position, boundingBox, nextPosition);

        tileOptional.ifPresent(collision -> {
            Tile tile = collision.getTile();
            double angle = Math.atan2(direction.y, direction.x);
            /*double quarterAngle = angle % (Math.PI / 4);
            double cos = Math.cos(quarterAngle);
            float length = (float) (0.5 / cos);
            direction.setLength(length);
            System.out.println("dir=" + direction);
            System.out.println("pt=" + collision.getRelativePoint());
            System.out.println("width=" + tile.getBoundingBox().width);

            nextPosition.set(tile.getPosition())
                    .add(tile.getBoundingBox().width / 2f, tile.getBoundingBox().height / 2f, 0)
                    .add(new Vector3(direction.x, direction.y, 0));*/

            Rectangle collideRect = new Rectangle(boundingBox).merge(tile.getBoundingBox());
            float cos = (float)(Math.round(Math.cos(angle)*1E6)/1E6);
            float sin = (float) (Math.round(Math.sin(angle)*1E6)/1E6);
            boolean dividerBool = Math.abs(cos) > Math.abs(sin);
            float divider = dividerBool ? cos : sin;
            float adjacent = dividerBool ? collideRect.width : collideRect.height;
            float length = adjacent/divider;
            direction.setLength(length);
            //if(length < 0) direction.scl(-1);

            Vector3 bbDirection = new Vector3(getBoundingBox().width, getBoundingBox().height, 1)
                    .scl(cos, sin, 1);

            System.out.println(collideRect);
            System.out.println(divider);
            System.out.println(length);
            System.out.println(direction);

            nextPosition.set(tile.getPosition())
                    //.sub(bbDirection)
                    .sub(direction.x, direction.y, 0);

            velocity.forEach(vector -> vector.scl(0));
        });

        setPosition(nextPosition);
    }

    public abstract boolean isDead();
}
