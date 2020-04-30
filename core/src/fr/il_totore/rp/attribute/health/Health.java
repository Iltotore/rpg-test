package fr.il_totore.rp.attribute.health;

import fr.il_totore.rp.attribute.damage.Damage;

public class Health
{
    private double value;

    public Health(double health) {
        this.value = health;
    }

    public void damage(Health damage)
    {
        this.value -=damage.value;
    }

    public boolean isLessOrEquals(double i) {
        return this.value <= i;
    }

    public void add(Health healthToAdd) {
        this.value +=healthToAdd.value;
    }

    public Damage toDamage() {
        return new Damage(Math.abs(this.value));
    }

}
