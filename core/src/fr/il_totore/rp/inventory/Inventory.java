package fr.il_totore.rp.inventory;

import fr.il_totore.rp.inventory.item.ItemStack;

import java.util.Optional;

public interface Inventory {

    /**
     * @return an {@link java.util.Optional} containing current
     * {@link fr.il_totore.rp.inventory.Inventory} holder if not null,
     * empty otherwise
     * */
    Optional<InventoryHolder> getInventoryHolder();

    /**
     * @return an {@link java.util.Optional} containing current
     * {@link fr.il_totore.rp.inventory.Inventory} contents if not null,
     * empty otherwise
     * */
    Optional<ItemStack[]> getContents();

    /**
     * Clear this {@link fr.il_totore.rp.inventory.Inventory} contents
     * */
    void clear();

    /**
     * <p>Set this {@link fr.il_totore.rp.inventory.Inventory} contents slot
     * to given {@link fr.il_totore.rp.inventory.item.ItemStack}</p>
     *
     * @param slot Slot to be set
     * @param itemStack {@link fr.il_totore.rp.inventory.item.ItemStack} to set
     *
     * @throws IllegalArgumentException if slot is not valid
     * */
    void setItemStack(int slot, ItemStack itemStack);

    /**
     * <p>Add given {@link fr.il_totore.rp.inventory.item.ItemStack}
     * to the first empty slot found in this
     * {@link fr.il_totore.rp.inventory.Inventory} contents</p>
     *
     * @param itemStack {@link fr.il_totore.rp.inventory.item.ItemStack} to add
     *
     * @throws IllegalArgumentException if inventory is full
     * */
    void addItemStack(ItemStack itemStack);

    /**
     * @return an {@link java.util.Optional} containing the first
     * {@link fr.il_totore.rp.inventory.item.ItemStack} found if present
     * or empty otherwise
     * */
    Optional<ItemStack> getFirst(ItemStack itemStack);

    /**
     * @param slot the desired {@link fr.il_totore.rp.inventory.item.ItemStack} slot
     * @return an {@link java.util.Optional} containing {@link fr.il_totore.rp.inventory.item.ItemStack}
     * found at the given slot if present or empty otherwise
     *
     * @throws IllegalArgumentException if slot is not valid
     * */
    Optional<ItemStack> getItemStack(int slot);

    /**
     * @return the current {@link fr.il_totore.rp.inventory.Inventory} size
     * */
    int getSize();

    /**
     * @return Current {@link fr.il_totore.rp.inventory.Inventory} type
     * */
    InventoryType getType();

}
