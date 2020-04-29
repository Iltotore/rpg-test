/*
 * Copyright KukubeTeam (c) 2020. For all uses ask KukubeTeam for approval before.
 */
package fr.il_totore.rp.inventory.item.meta;

import fr.il_totore.rp.inventory.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public class BasicItemMeta implements ItemMeta {

    private String displayName;
    private final List<Enchantment> enchantments = new ArrayList<>();

    @Override
    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    @Override
    public void addEnchant(Enchantment enchantment) {
        enchantments.add(enchantment);
    }

    @Override
    public void removeEnchant(Enchantment enchantment) {
        enchantments.remove(enchantment);
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean isSimilar(ItemMeta itemMeta) {
        return true;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
