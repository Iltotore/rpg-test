package fr.il_totore.rp.inventory;

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
