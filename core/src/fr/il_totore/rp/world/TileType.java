package fr.il_totore.rp.world;

import com.badlogic.gdx.math.Vector3;

import java.util.function.Function;

public enum TileType {

    UNKNOWN("unknown", false, UnknownTile::new),
    VOID("void", true, VoidTile::new);

    private String id;
    private boolean collidable;
    private Function<Vector3, Tile> tileFunction;

    TileType(String id, boolean collidable, Function<Vector3, Tile> tileFunction){
        this.id = id;
        this.collidable = collidable;
        this.tileFunction = tileFunction;
    }

    public String getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public Tile createTile(Vector3 position){
        return tileFunction.apply(position);
    }

    public static TileType getById(String id){
        for(TileType type : values()){
            if(type.getId().equals(id)) return type;
        }
        return UNKNOWN;
    }
}
