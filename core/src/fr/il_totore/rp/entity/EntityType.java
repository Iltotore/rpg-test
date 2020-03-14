package fr.il_totore.rp.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum EntityType {

    ;

    private String id;
    private TextureRegion texture;

    EntityType(String id, TextureRegion texture){
        this.id = id;
        this.texture = texture;
    }

    public String getId() {
        return id;
    }

    public TextureRegion getTexture() {
        return texture;
    }
}
