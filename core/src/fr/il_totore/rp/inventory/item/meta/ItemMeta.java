package fr.il_totore.rp.inventory.item.meta;

import fr.il_totore.rp.inventory.item.enchantment.Enchantment;

import java.util.List;

public interface ItemMeta {

    /**
     * @return a {@link java.util.List} of
     * {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment}
     * associated with the current {@link fr.il_totore.rp.inventory.item.meta.ItemMeta}
     */
    List<Enchantment> getEnchantments();

    /**
     * Add a {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment}
     * to the current enchantment list
     *
     * @param enchantment {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} to be added
     * */
    void addEnchant(Enchantment enchantment);

    /**
     * Remove a {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment}
     * from the current enchantment list if present
     *
     * @param enchantment {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} to be removed
     * */
    void removeEnchant(Enchantment enchantment);

    /**
     * Set the current {@link fr.il_totore.rp.inventory.item.meta.ItemMeta} displayName
     *
     * @param displayName to be set
     * */
    void setDisplayName(String displayName);

    /**
     * A method to check the similarity between two {@link fr.il_totore.rp.inventory.item.meta.ItemMeta}.
     *
     * @param itemMeta to be checked
     *
     * @return true if the given {@link fr.il_totore.rp.inventory.item.meta.ItemMeta}
     * has similar attributes with the current one, false otherwise
     * */
    boolean isSimilar(ItemMeta itemMeta);

    /**
     * @return current {@link fr.il_totore.rp.inventory.item.meta.ItemMeta} displayName
     */
    String getDisplayName();

    /**
     * A method to check if the current {@link fr.il_totore.rp.inventory.item.meta.ItemMeta} is applicable for a given type
     *
     * @param clazz to use for check
     *
     * @return true if the current {@link fr.il_totore.rp.inventory.item.meta.ItemMeta}
     * is an instance of the given class, false otherwise
     */
    boolean isOfType(Class<? extends ItemMeta> clazz);

    /**
     * A utility class to check the similarity between two objects.
     * */
    class SimilarCheck{

        /**
         * @param firstList the first list to use in check.
         * @param secondList the second list to use in check.
         *
         * @return true if <strong>firstList</strong> and <strong>secondList</strong> are equivalent,
         * false otherwise.
         * */
        static boolean areEnchantmentListsSimilar(List<Enchantment> firstList, List<Enchantment> secondList){
            if(firstList.size() != secondList.size()) return false;
            for (Enchantment enchantment : firstList) {
                if(!secondList.contains(enchantment)) return false;
            }
            return true;
        }
    }
}
