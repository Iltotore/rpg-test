package fr.il_totore.rp.world;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Tile {

    private TileType type;
    private Vector3 position;
    private Rectangle boundingBox = new Rectangle(0, 0, 1, 1);

    public Tile(TileType type, Vector3 position) {
        this.type = type;
        this.position = position;
    }

    public TileType getType() {
        return type;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public void load(MapProperties properties) {
    }

    public boolean isColliding(Vector3 position, Rectangle boundingBox) {
        Rectangle physicalBox = new Rectangle(this.boundingBox).setPosition(this.position.x, this.position.y);
        Rectangle otherBox = new Rectangle(boundingBox).setPosition(position.x, position.y);
        boolean collision = physicalBox.overlaps(otherBox) || otherBox.contains(physicalBox) || physicalBox.contains(otherBox);
        return isCollidable() && collision;
    }

    public boolean isCollidable(){
        return type.isCollidable();
    }


    @Override
    public String toString() {
        return "Tile{" +
                "type=" + type +
                ", position=" + position +
                '}';
    }
}
