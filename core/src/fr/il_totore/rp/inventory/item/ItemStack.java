package fr.il_totore.rp.inventory.item;

import fr.il_totore.rp.inventory.item.enchantment.Enchantment;
import fr.il_totore.rp.inventory.item.meta.ItemMeta;

public class ItemStack {

    private int amount;
    private final ItemMeta itemMeta;
    private final Material material;

    /**
     * This method is the only one possibility to get an {@link ItemStack}.
     *
     * @return a configurable {@link ItemStack}-{@link Builder}.
     * */
    public static Builder builder(Material material){
        return new Builder(material);
    }

    /**
     * <p>Private constructor of {@link ItemStack}.</p>
     * <p>Could only be instantiate using {@link Builder#build()}.</p>
     * */
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

        /***
         * Set amount of {@link ItemStack} in current stack.
         *
         * @param amount the amount of items contained in current stack.
         *
         * @return the current {@link Builder}.
         * */
        public Builder withAmount(int amount){
            this.amount = amount;
            return this;
        }

        /***
         * Set a read-for-use {@link ItemMeta} to the current {@link ItemStack}.
         *
         * @param itemMeta the item meta to set.
         *
         * @return the current {@link Builder}.
         * */
        public Builder withItemMeta(ItemMeta itemMeta){
            this.itemMeta = itemMeta;
            return this;
        }

        /**
         * Add an {@link Enchantment} to the current {@link fr.il_totore.rp.inventory.item.ItemStack}.
         *
         * @param enchantment the enchantment to add
         *
         * @return the current {@link Builder}.
         * */
        public Builder withEnchant(Enchantment enchantment){
            this.itemMeta.addEnchant(enchantment);
            return this;
        }

        /**
         * Set {@link fr.il_totore.rp.inventory.item.ItemStack} displayed name to the given one.
         *
         * @param displayName the name to display
         *
         * @return the current {@link Builder}.
         * */
        public Builder withDisplayName(String displayName){
            this.itemMeta.setDisplayName(displayName);
            return this;
        }
        /**
         * @return a {@link ItemStack} with previously given attributes.
         * */
        public ItemStack build(){
            return new ItemStack(material, amount, itemMeta);
        }

        /**
         * <p>Private constructor of {@link ItemStack}-{@link Builder}.</p>
         * <p>Could only be instantiate using {@link ItemStack#builder(Material)}.</p>
         * */
        private Builder(Material material){
            this.material = material;
            this.itemMeta = material.getDefaultItemMeta();
        }
    }
}
