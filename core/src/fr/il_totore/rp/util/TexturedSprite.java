package fr.il_totore.rp.util;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class TexturedSprite {

    private Texture[] textures;
    private int interval;
    private BiFunction<TexturedSprite, Integer, Texture> frameFunction;

    public TexturedSprite(Texture[] textures, int interval, BiFunction<TexturedSprite, Integer, Texture> frameFunction) {
        this.textures = textures;
        this.interval = interval;
        this.frameFunction = frameFunction;
    }

    public Texture[] getTextures() {
        return textures;
    }

    public int size() {
        return textures.length;
    }

    public int getInterval() {
        return interval;
    }

    public Texture getTexture(int frame) {
        return frameFunction.apply(this, frame);
    }

    public static final BiFunction<TexturedSprite, Integer, Texture> LOOP = (sprite, i) -> sprite.getTextures()[i % sprite.size()];
    public static final BiFunction<TexturedSprite, Integer, Texture> SIMPLE = (sprite, i) -> sprite.getTextures()[i >= sprite.size() ? sprite.size() - 1 : i];

    public static Builder builder() {
        return new Builder();
    }

    public static TexturedSprite of(Texture texture){
        return new TexturedSprite(new Texture[]{texture}, 0, (sprite, i) -> sprite.getTextures()[0]);
    }

    public static class Builder {

        private List<Texture> textures = new ArrayList<>();
        private int interval;
        private BiFunction<TexturedSprite, Integer, Texture> frameFunction = LOOP;

        private Builder() {
        }

        public Builder withTexture(int i, Texture texture) {
            textures.add(i, texture);
            return this;
        }

        public Builder withTexture(Texture texture) {
            textures.add(texture);
            return this;
        }

        public Builder interval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder time(int frameTime) {
            return interval(frameTime / textures.size());
        }

        public Builder animFunction(BiFunction<TexturedSprite, Integer, Texture> frameFunction) {
            this.frameFunction = frameFunction;
            return this;
        }

        public TexturedSprite build() {
            return new TexturedSprite(textures.toArray(new Texture[]{}), interval, frameFunction);
        }
    }
}
