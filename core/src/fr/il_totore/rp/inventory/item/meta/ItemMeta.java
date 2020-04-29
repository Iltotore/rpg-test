/*
 * Copyright KukubeTeam (c) 2020. For all uses ask KukubeTeam for approval before.
 */
package fr.il_totore.rp.inventory.item.meta;

import fr.il_totore.rp.inventory.item.enchantment.Enchantment;

import java.util.List;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public interface ItemMeta {

    List<Enchantment> getEnchantments();

    void addEnchant(Enchantment enchantment);

    void removeEnchant(Enchantment enchantment);

    void setDisplayName(String displayName);

    boolean isSimilar(ItemMeta itemMeta);

    String getDisplayName();
}
