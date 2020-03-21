package fr.il_totore.rp.world;

import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.util.TexturedSprite;

import java.util.Optional;
import java.util.function.Function;

public enum TileType {

    ;

    private int id;
    private TexturedSprite sprite;
    private boolean collidable;
    private Function<Vector3, Tile> tileFunction;

    TileType(int id, TexturedSprite sprite, boolean collidable, Function<Vector3, Tile> tileFunction){
        this.id = id;
        this.sprite = sprite;
        this.collidable = collidable;
        this.tileFunction = tileFunction;
    }

    public int getId() {
        return id;
    }

    public TexturedSprite getSprite() {
        return sprite;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public Tile createTile(Vector3 position){
        return tileFunction.apply(position);
    }

    public static Optional<TileType> getById(int id){
        for(TileType type : values()){
            if(type.getId() == id) return Optional.of(type);
        }
        return Optional.empty();
    }
}
