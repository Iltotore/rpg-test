package fr.il_totore.rp.attribute.damage;


import fr.il_totore.rp.attribute.health.Health;

public class Damage
{

    private double value;
    private final DamageType damageType;

    public Damage(double damage)
    {
        this.damageType = DamageType.PHYSICAL;
        this.value = damage;
    }

    public Damage(double damage, DamageType damageType)
    {
        this.damageType = damageType;
        this.value = damage;
    }

    void addDamage(Damage damageToAdd)
    {
        this.value +=damageToAdd.value;
    }

    public Health toHealth() {
        return new Health(this.value);
    }
}
