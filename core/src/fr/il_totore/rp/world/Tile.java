package fr.il_totore.rp.world;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector3;

public class Tile {

    private TileType type;
    private Vector3 position;

    public Tile(TileType type, Vector3 position){
        this.type = type;
        this.position = position;
    }

    public TileType getType() {
        return type;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void load(MapProperties properties){
    }

    public boolean isCollidable(){
        return type.isCollidable();
    }

}
