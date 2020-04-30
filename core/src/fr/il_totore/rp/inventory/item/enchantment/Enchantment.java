package fr.il_totore.rp.inventory.item.enchantment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Enchantment {

    PROTECTION(0, 4, "Protection");

    private final int id;
    private final int maxLevel;
    private final String name;

    public int getId() {
        return id;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getName() {
        return name;
    }

    Enchantment(int id, int maxLevel, String name){
        this.id = id;
        this.maxLevel = maxLevel;
        this.name = name;
    }

    public static Optional<Enchantment> getById(int id){
        return Optional.ofNullable(values()[id]);
    }
    public static Optional<Enchantment> getByName(String name){
        Enchantment enchantment = null;
        for(Enchantment enchantment1 : values()){
            if(enchantment1.getName().equalsIgnoreCase(name)) {
                enchantment = enchantment1;
                break;
            }
        }
        return Optional.ofNullable(enchantment);
    }
    public static List<Enchantment> getAsList(){
        return Arrays.asList(values());
    }
}
