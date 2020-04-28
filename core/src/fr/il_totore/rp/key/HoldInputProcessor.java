package fr.il_totore.rp.key;

public class HoldInputProcessor extends WaitInputProcessor {

    public HoldInputProcessor(int key) {
        super(new Input(KeyInputType.DOWN, key), new Input(KeyInputType.UP, key));
    }
}
