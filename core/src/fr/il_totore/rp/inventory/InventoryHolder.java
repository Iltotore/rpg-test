package fr.il_totore.rp.inventory;

import java.util.Optional;

public interface InventoryHolder {

    /**
     * @return an {@link java.util.Optional} containing
     * {@link fr.il_totore.rp.inventory.Inventory} owned by the current
     * {@link fr.il_totore.rp.inventory.InventoryHolder} if not null,
     * empty otherwise
     * */
    Optional<Inventory> getInventory();

}
