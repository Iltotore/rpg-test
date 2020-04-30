package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.attribute.damage.Damage;
import fr.il_totore.rp.attribute.damage.Damageable;
import fr.il_totore.rp.attribute.health.Health;
import fr.il_totore.rp.world.GameMap;

public class LivingEntity extends Entity implements Damageable {

    private Health health;
    private float walkingSpeed = 1.5f;

    public LivingEntity(EntityType<? extends LivingEntity> type, Rectangle boundingBox, GameMap map, Vector3 position, Health health) {
        super(type, boundingBox, map, position);
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

    @Override
    public boolean isDestroy() {
        return this.health.isLessOrEquals(0);
    }

    @Override
    public void addHealth(Health health) {
        this.health.add(health);
    }

    @Override
    public Damage getDamageRest() {
        if(this.health.isLessOrEquals(0)) {
            Damage damage = this.health.toDamage();
            this.health = new Health(0);
            return damage;
        }
        return new Damage(0);
    }

    @Override
    public Damage damage(Damage damage) {
        Health healthDamage = damage.toHealth();
        this.health.damage(healthDamage);
        return this.getDamageRest();
    }
}
