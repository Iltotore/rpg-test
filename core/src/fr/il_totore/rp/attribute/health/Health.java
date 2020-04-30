package fr.il_totore.rp.attribute.health;

import fr.il_totore.rp.attribute.damage.Damage;

public class Health
{
    private double health;

    public Health(double health) {
        this.health = health;
    }

    public void damage(Health damage)
    {
        this.health -=damage.health;
    }

    public boolean isLessOrEqualThan(double i) {
        return this.health <= i;
    }

    public void add(Health healthToAdd) {
        this.health +=healthToAdd.health;
    }

    public Damage toDamage() {
        return new Damage(Math.abs(this.health));
    }

}
