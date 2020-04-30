package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.attribute.health.Health;
import fr.il_totore.rp.util.CompositeVelocity;
import fr.il_totore.rp.world.GameMap;

public class Player extends LivingEntity {

    private CompositeVelocity velocity;

    public Player(GameMap map, Vector3 position) {
        super(EntityType.PLAYER, new Rectangle(0, 0, 1.25f, 1.65f), map, position, new Health(10));
    }

}
