package fr.il_totore.rp.attribute.damage;


import fr.il_totore.rp.attribute.health.Health;

public class Damage
{

    private double damage;
    private final DamageType damageType;

    public Damage(double damage)
    {
        this.damageType = DamageType.PHYSICAL;
        this.damage = damage;
    }

    public Damage(double damage, DamageType damageType)
    {
        this.damageType = damageType;
        this.damage = damage;
    }

    void addDamage(Damage damage)
    {
        this.damage+=damage.damage;
    }

    public Health toHealth() {
        return new Health(this.damage);
    }
}
