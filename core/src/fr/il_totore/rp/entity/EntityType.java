package fr.il_totore.rp.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.function.Supplier;

public enum EntityType {

    ;

    private String id;
    private Supplier<Actor> actorSupplier;

    EntityType(String id, Supplier<Actor> actorSupplier){
        this.id = id;
        this.actorSupplier = actorSupplier;
    }

    public String getId() {
        return id;
    }

    public Actor newActor(){
        return actorSupplier.get();
    }
}
