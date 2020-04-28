package fr.il_totore.rp.key;

public enum MouseInputType implements InputType {

    DOWN, UP, DRAGGED;

    @Override
    public String getId() {
        return getClass().getName() + "$" + name();
    }
}