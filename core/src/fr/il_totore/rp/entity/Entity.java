package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity {

    private EntityType type;
    private Actor actor;
    private Rectangle boundingBox;

    public Entity(EntityType type, Actor actor, Rectangle boundingBox){
        this.actor = actor;
        this.boundingBox = boundingBox;
    }

    public EntityType getType() {
        return type;
    }

    public Actor getActor() {
        return actor;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public abstract boolean isDead();
}
