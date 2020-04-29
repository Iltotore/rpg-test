/*
 * Copyright KukubeTeam (c) 2020. For all uses ask KukubeTeam for approval before.
 */
package fr.il_totore.rp.inventory;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public enum InventoryType {

    PLAYER(13, "Player");

    private final int size;
    private final String title;

    InventoryType(int size, String title){
        this.size = size;
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

}
