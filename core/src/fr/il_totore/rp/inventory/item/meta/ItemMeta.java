package fr.il_totore.rp.inventory.item.meta;

import fr.il_totore.rp.inventory.item.enchantment.Enchantment;

import java.util.List;

public interface ItemMeta {

    List<Enchantment> getEnchantments();

    void addEnchant(Enchantment enchantment);

    void removeEnchant(Enchantment enchantment);

    void setDisplayName(String displayName);

    boolean isSimilar(ItemMeta itemMeta);

    String getDisplayName();
}
