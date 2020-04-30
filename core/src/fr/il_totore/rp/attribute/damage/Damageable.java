package fr.il_totore.rp.attribute.damage;

import fr.il_totore.rp.attribute.health.Health;

public interface Damageable
{

    Damage damage(Damage damage);

    default Damage damages(Damages damages)
    {
        return damages.execute(this);
    }

    boolean isDestroyed();

    void addHealth(Health health);

    Damage getDamageRest();

}
