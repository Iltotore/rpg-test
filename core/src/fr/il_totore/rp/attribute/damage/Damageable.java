package fr.il_totore.rp.attribute.damage;

import fr.il_totore.rp.attribute.health.Health;

public interface Damageable
{

    Damage applyDamage(Damage damage);

    boolean isDestroyed();

    void addHealth(Health health);

    Damage getDamageRest();

}
