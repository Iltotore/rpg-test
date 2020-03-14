package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class LivingEntity extends Entity implements DamageableEntity {

    private double health;

    public LivingEntity(EntityType type, Rectangle boundingBox) {
        super(type, boundingBox);
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public void damage() {

    }
}
