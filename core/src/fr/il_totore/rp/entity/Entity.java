package fr.il_totore.rp.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity {

    private EntityType type;
    private Rectangle boundingBox;

    public Entity(EntityType type, Rectangle boundingBox){
        this.type = type;
        this.boundingBox = boundingBox;
    }

    public EntityType getType() {
        return type;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public abstract boolean isDead();
    public abstract void render(SpriteBatch batch);
    public abstract void tick(float delta);
}
