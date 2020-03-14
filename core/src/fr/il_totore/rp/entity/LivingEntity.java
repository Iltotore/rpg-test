package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LivingEntity extends Entity implements DamageableEntity {

    private double health;

    public LivingEntity(Actor actor, Rectangle boundingBox) {
        super(actor, boundingBox);
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public void damage() {
        
    }
}
