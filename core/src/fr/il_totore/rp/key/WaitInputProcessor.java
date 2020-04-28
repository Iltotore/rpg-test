package fr.il_totore.rp.key;

import java.util.Optional;

public class WaitInputProcessor implements InputProcessor {

    private Input firstInput;
    private Input lastInput;
    private boolean firstPressed = false;

    public WaitInputProcessor(Input firstInput, Input lastInput){
        this.firstInput = firstInput;
        this.lastInput = lastInput;
    }

    public Input getFirstInput() {
        return firstInput;
    }

    public Input getLastInput() {
        return lastInput;
    }

    public boolean isFirstPressed() {
        return firstPressed;
    }

    public void setFirstPressed(boolean firstPressed) {
        this.firstPressed = firstPressed;
    }

    @Override
    public boolean check(Optional<Input> input) {
        if(!input.isPresent()) return firstPressed;
        if(input.get().equals(firstInput) && !firstPressed) return firstPressed = true;
        if(input.get().equals(lastInput) && firstPressed) return firstPressed = false;
        return firstPressed;
    }
}
