package fr.il_totore.rp.world;

import com.badlogic.gdx.math.Vector3;

import java.util.function.Function;

public enum TileType {

    UNKNOWN(-1, true, UnknownTile::new);

    private int id;
    private boolean collidable;
    private Function<Vector3, Tile> tileFunction;

    TileType(int id, boolean collidable, Function<Vector3, Tile> tileFunction){
        this.id = id;
        this.collidable = collidable;
        this.tileFunction = tileFunction;
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public Tile createTile(Vector3 position){
        return tileFunction.apply(position);
    }

    public static TileType getById(int id){
        for(TileType type : values()){
            if(type.getId() == id) return type;
        }
        return UNKNOWN;
    }
}
