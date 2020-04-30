package fr.il_totore.rp.inventory;

import fr.il_totore.rp.inventory.item.ItemStack;

import java.util.Optional;

public interface Inventory {

    /**
     * @return Current {@link fr.il_totore.rp.inventory.Inventory} holder
     * */
    InventoryHolder getInventoryHolder();

    /**
     * @return Current {@link fr.il_totore.rp.inventory.Inventory} contents
     * */
    ItemStack[] getContents();

    /**
     * Set current {@link fr.il_totore.rp.inventory.Inventory} contents to given one
     *
     * @param contents New {@link fr.il_totore.rp.inventory.Inventory} contents to be set
     * */
    void setContents(ItemStack[] contents);
    /**
     * Clear this {@link fr.il_totore.rp.inventory.Inventory} contents
     * */
    void clear();

    /**
     * Set this {@link fr.il_totore.rp.inventory.Inventory} contents slot to given {@link fr.il_totore.rp.inventory.item.ItemStack}
     *
     * @param slot Slot to be set
     * @param itemStack {@link fr.il_totore.rp.inventory.item.ItemStack} to set
     *
     * @throws IllegalArgumentException if slot is not valid
     * */
    void setItemStack(int slot, ItemStack itemStack) throws IllegalArgumentException;

    /**
     * Add given {@link fr.il_totore.rp.inventory.item.ItemStack} to the first empty slot found in this {@link fr.il_totore.rp.inventory.Inventory} contents
     *
     * @param itemStack {@link fr.il_totore.rp.inventory.item.ItemStack} to add
     *
     * @throws IllegalArgumentException if inventory is full
     * */
    void addItemStack(ItemStack itemStack) throws IllegalArgumentException;

    /**
     * @return an {@link java.util.Optional} containing the first {@link fr.il_totore.rp.inventory.item.ItemStack} found if present or empty otherwise
     * */
    Optional<ItemStack> getFirst(ItemStack itemStack);

    /**
     * @param slot the desired {@link fr.il_totore.rp.inventory.item.ItemStack} slot
     * @return an {@link java.util.Optional} containing {@link fr.il_totore.rp.inventory.item.ItemStack} found at the given slot if present or empty otherwise
     *
     * @throws IllegalArgumentException if slot is not valid
     * */
    Optional<ItemStack> getItemStack(int slot) throws IllegalArgumentException;

    /**
     * @return the current {@link fr.il_totore.rp.inventory.Inventory} size
     * */
    int getSize();

    /**
     * @return Current {@link fr.il_totore.rp.inventory.Inventory} type
     * */
    InventoryType getType();

}
