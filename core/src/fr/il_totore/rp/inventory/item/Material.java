/*
 * Copyright KukubeTeam (c) 2020. For all uses ask KukubeTeam for approval before.
 */
package fr.il_totore.rp.inventory.item;

import fr.il_totore.rp.inventory.item.meta.BasicItemMeta;
import fr.il_totore.rp.inventory.item.meta.ItemMeta;

import java.util.Optional;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public enum Material {

    APPLE(0, "Apple", new BasicItemMeta());

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ItemMeta getDefaultItemMeta() {
        return defaultItemMeta;
    }

    private final int id;
    private final String name;
    private final ItemMeta defaultItemMeta;

    Material(int id, String name, ItemMeta defaultItemMeta){
        this.id = id;
        this.name = name;
        this.defaultItemMeta = defaultItemMeta;
    }

    public static Optional<Material> getById(int id){
        return Optional.ofNullable(values()[id]);
    }
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
