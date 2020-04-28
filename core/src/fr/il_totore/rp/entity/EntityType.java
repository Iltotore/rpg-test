package fr.il_totore.rp.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import fr.il_totore.rp.util.TexturedSprite;
import fr.il_totore.rp.world.GameMap;

import java.util.function.BiFunction;

public class EntityType<T extends Entity> {

    private int id;
    private TexturedSprite sprite;
    private BiFunction<GameMap, Vector3, T> entityFunction;

    private EntityType(int id, TexturedSprite sprite, BiFunction<GameMap, Vector3, T> entityFunction){
        this.id = id;
        this.sprite = sprite;
        this.entityFunction = entityFunction;
    }

    public int getId() {
        return id;
    }

    public TexturedSprite getSprite() {
        return sprite;
    }

    public T newEntity(GameMap map, Vector3 position){
        return entityFunction.apply(map, position);
    }

    public static final EntityType<Player> PLAYER = new EntityType<>(0, TexturedSprite.of(new Texture("player.png")), Player::new);
}
