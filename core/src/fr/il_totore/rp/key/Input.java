package fr.il_totore.rp.key;

import java.util.Objects;

public class Input {

    private InputType type;
    private int key;

    public Input(InputType type, int key){
        this.type = type;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public InputType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Input)) return false;
        Input input = (Input) o;
        return key == input.key &&
                Objects.equals(type, input.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, key);
    }
}
