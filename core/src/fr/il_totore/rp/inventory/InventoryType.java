package fr.il_totore.rp.inventory;

public enum InventoryType {

    /**
     * 4 armor slots
     * 9 basic inventory slots
     * */
    PLAYER(13, "Player");

    /**
     * <p>The default {@link fr.il_totore.rp.inventory.Inventory} size,
     * as returned by {@link #getSize()}.</p>
     *
     * <p>The field is initialized with the enum value first parameter.</p>
     * */
    private final int size;

    /**
     * <p>The default {@link fr.il_totore.rp.inventory.Inventory} title,
     * as returned by {@link #getTitle()}. </p>
     *
     * <p>The field is initialized with the enum value second parameter.</p>
     * */
    private final String title;

    InventoryType(int size, String title){
        this.size = size;
        this.title = title;
    }

    /**
     * @return default {@link fr.il_totore.rp.inventory.Inventory} size.
     * */
    public int getSize() {
        return size;
    }

    /**
     * @return default {@link fr.il_totore.rp.inventory.Inventory} title.
     * */
    public String getTitle() {
        return title;
    }

}
