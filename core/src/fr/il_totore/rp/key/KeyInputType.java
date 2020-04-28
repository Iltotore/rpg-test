package fr.il_totore.rp.key;

public enum KeyInputType implements InputType {

    DOWN, UP, TYPED;

    @Override
    public String getId() {
        return getClass().getName() + "$" + name();
    }
}
