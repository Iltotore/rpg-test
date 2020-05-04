package fr.il_totore.rp.util;

import com.badlogic.gdx.math.Vector2;
import fr.il_totore.rp.world.Tile;

public class Collision {

    private Tile tile;
    private Vector2 relativePoint;

    public Collision(Tile tile, Vector2 relativePoint){
        this.tile = tile;
        this.relativePoint = relativePoint;
    }

    public Tile getTile() {
        return tile;
    }

    public Vector2 getRelativePoint() {
        return relativePoint;
    }
}
