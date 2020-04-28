package fr.il_totore.rp.util;

import com.badlogic.gdx.math.*;

import java.util.*;
import java.util.function.Consumer;

public class CompositeVelocity implements Iterable<Vector3>{

    private final Map<String, Vector3> velocities = new HashMap<>();

    public Vector3 getComponent(String key) {
        return velocities.getOrDefault(key, new Vector3());
    }

    public Vector3 getAndSetComponent(String key) {
        if(velocities.containsKey(key)) return velocities.get(key);
        Vector3 vector = new Vector3();
        velocities.put(key, vector);
        return vector;
    }

    public void setComponent(String key, Vector3 vector) {
        velocities.put(key, vector);
    }

    public Vector3 toFinalVector(){
        Vector3 finalVector = new Vector3();
        velocities.values().forEach(finalVector::add);
        return finalVector;
    }

    @Override
    public Iterator<Vector3> iterator() {
        return velocities.values().iterator();
    }

    @Override
    public void forEach(Consumer<? super Vector3> action) {
        velocities.values().forEach(action);
    }

    @Override
    public Spliterator<Vector3> spliterator() {
        return velocities.values().spliterator();
    }

    public static final String BASE = "base";
    public static final String RIGHT = "right";
    public static final String LEFT = "left";
    public static final String UP = "up";
    public static final String DOWN = "down";
}