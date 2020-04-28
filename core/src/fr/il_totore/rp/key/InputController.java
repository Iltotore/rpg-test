package fr.il_totore.rp.key;

import java.util.*;
import java.util.function.Consumer;

public class InputController {

    private Queue<Input> inputQueue = new ArrayDeque<>();
    private Map<InputProcessor, Consumer<Input>> inputs = new HashMap<>();

    public void put(InputProcessor input, Consumer<Input> action){
        inputs.put(input, action);
    }

    public void control(Input input){
        inputQueue.add(input);
    }

    public void update(){
        Input input = inputQueue.poll();

        do {
            for(Map.Entry<InputProcessor, Consumer<Input>> entry : inputs.entrySet()){
                if(entry.getKey().check(Optional.ofNullable(input))) entry.getValue().accept(input);
            }
            input = inputQueue.poll();
        }
        while(input != null);
    }
}