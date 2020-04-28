package fr.il_totore.rp.key;

import java.util.Optional;

public class SimpleInputProcessor implements InputProcessor {

    private Input input;

    public SimpleInputProcessor(Input input){
        this.input = input;
    }

    public Input getInput() {
        return input;
    }

    @Override
    public boolean check(Optional<Input> input) {
        return this.input.equals(input.orElse(null));
    }
}
