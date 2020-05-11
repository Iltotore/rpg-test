package fr.il_totore.rp;

import com.badlogic.gdx.math.Rectangle;

public interface PhysicalObject {

    Rectangle getBoundingBox();

    Rectangle getPhysicalBox();
}
