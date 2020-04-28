package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.world.GameMap;

public abstract class LivingEntity extends Entity implements DamageableEntity {

    private double health;
    private float walkingSpeed = 1.5f;

    public LivingEntity(EntityType<? extends LivingEntity> type, Rectangle boundingBox, GameMap map, Vector3 position) {
        super(type, boundingBox, map, position);
    }

    @Override
    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public void damage() {
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

    public void setWalkingSpeed(float walkingSpeed) {
        this.walkingSpeed = walkingSpeed;
    }

    public void walk(String key, Vector3 direction){
        setVelocity(key, direction.cpy().scl(walkingSpeed));
    }
}
