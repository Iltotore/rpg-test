package fr.il_totore.rp.inventory.item.meta;

import fr.il_totore.rp.inventory.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;

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
        return this.displayName.equals(itemMeta.getDisplayName()) && ItemMeta.SimilarCheck.areEnchantmentListsSimilar(this.enchantments, itemMeta.getEnchantments());
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isOfType(Class<? extends ItemMeta> clazz) {
        return this.getClass() == clazz;
    }
}
