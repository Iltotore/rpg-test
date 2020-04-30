package fr.il_totore.rp.inventory.item;

import fr.il_totore.rp.inventory.item.meta.BasicItemMeta;
import fr.il_totore.rp.inventory.item.meta.ItemMeta;

import java.util.Optional;

public enum Material {

    APPLE(0, "Apple", new BasicItemMeta());

    /**
     * <p>The {@link fr.il_totore.rp.inventory.item.Material} id,
     * as returned by {@link #getId()}.</p>
     *
     * <p>The field is initialized with the enum value first parameter.</p>
     * */
    private final int id;

    /**
     * <p>The {@link fr.il_totore.rp.inventory.item.Material} name,
     * as returned by {@link #getName()}.</p>
     * <p>The field is initialized with the enum value second parameter.</p>
     * */
    private final String name;

    /**
     * <p>The {@link fr.il_totore.rp.inventory.item.Material} default
     * {@link fr.il_totore.rp.inventory.item.meta.ItemMeta},
     * as returned by {@link #getDefaultItemMeta()}.</p>
     *
     * <p>The field is initialized with the enum value third parameter.</p>
     * */
    private final ItemMeta defaultItemMeta;

    Material(int id, String name, ItemMeta defaultItemMeta){
        this.id = id;
        this.name = name;
        this.defaultItemMeta = defaultItemMeta;
    }

    /**
     * @return {@link fr.il_totore.rp.inventory.item.Material}} id.
     * */
    public int getId() {
        return id;
    }

    /**
     * @return {@link fr.il_totore.rp.inventory.item.Material}} name.
     * */
    public String getName() {
        return name;
    }

    /**
     * @return {@link fr.il_totore.rp.inventory.item.Material}}
     * default {@link fr.il_totore.rp.inventory.item.meta.ItemMeta}.
     * */
    public ItemMeta getDefaultItemMeta() {
        return defaultItemMeta;
    }

    /**
     * @return an {@link java.util.Optional} containing the
     * {@link fr.il_totore.rp.inventory.item.Material} which
     * has the given id as {@linkplain #id},
     * empty if any {@link fr.il_totore.rp.inventory.item.Material} matches
     * */
    public static Optional<Material> getById(int id){
        return Optional.ofNullable(values()[id]);
    }

    /**
     * @return an {@link java.util.Optional} containing the
     * {@link fr.il_totore.rp.inventory.item.Material} which
     * has the given name as {@linkplain #name}
     * empty if any {@link fr.il_totore.rp.inventory.item.Material} matches
     *
     * <p>This method ignore {@link java.lang.String} case</p>
     * */
    public static Optional<Material> getByName(String name){
        Material material = null;
        for(Material material1 : values()){
            if(material1.getName().equalsIgnoreCase(name)) {
                material = material1;
                break;
            }
        }
        return Optional.ofNullable(material);
    }
}
