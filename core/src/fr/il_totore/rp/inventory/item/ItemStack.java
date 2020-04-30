package fr.il_totore.rp.inventory.item;

import fr.il_totore.rp.inventory.item.meta.ItemMeta;

public class ItemStack {

    private int amount;
    private final ItemMeta itemMeta;
    private final Material material;

    public static Builder builder(Material material){
        return new Builder(material);
    }

    private ItemStack(Material material, int amount, ItemMeta itemMeta){
        this.material = material;
        this.amount = amount;
        this.itemMeta = itemMeta;
    }

    public ItemStack setAmount(int amount){
        this.amount = amount;
        return this;
    }

    public boolean isSimilar(ItemStack itemStack){
        if(itemStack == null) return false;
        if(itemStack == this) return true;
        if(this.itemMeta == null) {
            return this.amount == itemStack.amount && this.material == itemStack.material;
        }
        return this.amount == itemStack.amount && this.material == itemStack.material && this.itemMeta.isSimilar(itemStack.itemMeta);
    }


    static class Builder{

        private int amount = 0;
        private final Material material;
        private ItemMeta itemMeta;

        public Builder withAmount(int amount){
            this.amount = amount;
            return this;
        }
        public Builder withItemMeta(ItemMeta itemMeta){
            this.itemMeta = itemMeta;
            return this;
        }

        public ItemStack build(){
            return new ItemStack(material, amount, itemMeta);
        }

        private Builder(Material material){
            this.material = material;
            this.itemMeta = material.getDefaultItemMeta();
        }
    }
}
