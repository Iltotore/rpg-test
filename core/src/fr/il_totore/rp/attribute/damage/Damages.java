package fr.il_totore.rp.attribute.damage;

import java.util.ArrayList;
import java.util.List;

public class Damages
{

    private List<Damage> damages;

    public Damages() {
        this.damages = new ArrayList<>();
    }

    public void addDamage(Damage damage)
    {
        this.damages.add(damage);
    }

    public Damage execute(Damageable damageable) {
        Damage rest = new Damage(0);
        for (Damage damage : this.damages)
        {
            rest.addDamage(damageable.damage(damage));
        }
        return rest;
    }
}
