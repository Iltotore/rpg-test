package fr.il_totore.rp.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.inventory.Inventory;
import fr.il_totore.rp.inventory.InventoryHolder;
import fr.il_totore.rp.inventory.PlayerInventory;
import fr.il_totore.rp.util.CompositeVelocity;
import fr.il_totore.rp.world.GameMap;

public class Player extends LivingEntity implements InventoryHolder {

    private CompositeVelocity velocity;
    private PlayerInventory inventory;

    public Player(GameMap map, Vector3 position) {
        super(EntityType.PLAYER, new Rectangle(0, 0, 1.25f, 1.65f), map, position);
        this.inventory = new PlayerInventory(this);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
