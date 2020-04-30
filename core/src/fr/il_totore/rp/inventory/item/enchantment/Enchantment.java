package fr.il_totore.rp.inventory.item.enchantment;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Enchantment {

    PROTECTION(0, 4, "Protection");

    /**
     * <p>The {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} id,
     * as returned by {@link #getId()}.</p>
     *
     * <p>The field is initialized with the enum value first parameter.</p>
     * */
    private final int id;

    /**
     * <p>The highest level of {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} possible,
     * as returned by {@link #getMaxLevel()}.</p>
     *
     * <p>The field is initialized with the enum value second parameter.</p>
     * */
    private final int maxLevel;

    /**
     * <p>The {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} name,
     * as returned by {@link #getName()}.</p>
     *
     * <p>The field is initialized with the enum value third parameter.</p>
     * */
    private final String name;

    /**
     * @return current {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} id.
     * */
    public int getId() {
        return id;
    }

    /**
     * @return current {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} highest possible level.
     * */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * @return current {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} name.
     * */
    public String getName() {
        return name;
    }

    Enchantment(int id, int maxLevel, String name){
        this.id = id;
        this.maxLevel = maxLevel;
        this.name = name;
    }

    /**
     * @return an {@link java.util.Optional} containing the
     * {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} which
     * has the given id as {@linkplain #id},
     * empty if any {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} matches
     * */
    public static Optional<Enchantment> getById(int id){
        return Optional.ofNullable(values()[id]);
    }

    /**
     * @return an {@link java.util.Optional} containing the
     * {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} which
     * has the given name as {@linkplain #name}
     * empty if any {@link fr.il_totore.rp.inventory.item.enchantment.Enchantment} matches.
     *
     * <p>This method ignore {@link java.lang.String} case</p>
     * */
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
