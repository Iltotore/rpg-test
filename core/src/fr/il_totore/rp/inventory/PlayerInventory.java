/*
 * Copyright KukubeTeam (c) 2020. For all uses ask KukubeTeam for approval before.
 */
package fr.il_totore.rp.inventory;

import fr.il_totore.rp.inventory.item.ItemStack;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public class PlayerInventory implements Inventory{

    private ItemStack[] contents;
    private final InventoryHolder inventoryHolder;

    public PlayerInventory(InventoryHolder inventoryHolder, ItemStack[] contents){
        this.inventoryHolder = inventoryHolder;
        this.contents = contents;
    }
    public PlayerInventory(InventoryHolder inventoryHolder){
        this.inventoryHolder = inventoryHolder;
        this.contents = new ItemStack[InventoryType.PLAYER.getSize()];
    }
    @Override
    public InventoryHolder getInventoryHolder() {
        return inventoryHolder;
    }

    @Override
    public ItemStack[] getContents() {
        return contents;
    }

    @Override
    public void setContents(ItemStack[] contents) {
        this.contents = contents;
    }

    @Override
    public void clear() {
        this.contents = new ItemStack[contents.length];
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack) throws IllegalArgumentException {
        if(slot > contents.length - 1) throw new IllegalArgumentException("Slot cannot be greater than " + this.contents.length);
        if(slot < 0) throw new IllegalArgumentException("Slot cannot be lower than 0");
        contents[slot] = itemStack;
    }

    @Override
    public void addItemStack(ItemStack itemStack) throws IllegalArgumentException {
        boolean set = false;
        for (int i = 0; i < contents.length; i++) {
            if(contents[i] == null){
                contents[i] = itemStack;
                set = true;
                break;
            }
        }
        if(!set) throw new IllegalArgumentException("ItemStack could not be added to Inventory : inventory full");
    }

    @Override
    public Optional<ItemStack> getFirst(ItemStack itemStack) {
        return Arrays.stream(contents).filter(it -> it.isSimilar(itemStack)).findFirst();
    }

    @Override
    public Optional<ItemStack> getItemStack(int slot) throws IllegalArgumentException {
        if(slot > contents.length - 1) throw new IllegalArgumentException("Slot cannot be greater than " + this.contents.length);
        return Optional.ofNullable(contents[slot]);
    }

    @Override
    public int getSize() {
        return contents.length;
    }

    @Override
    public InventoryType getType() {
        return InventoryType.PLAYER;
    }
}
