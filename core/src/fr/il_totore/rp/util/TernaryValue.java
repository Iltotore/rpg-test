package fr.il_totore.rp.util;

import java.util.Optional;

public class TernaryValue {

    private Optional<Boolean> value = Optional.empty();


    public boolean isTrue(){
        return value.orElse(false);
    }

    public boolean isFalse(){
        return !value.orElse(true);
    }

    public boolean isEmpty(){
        return !value.isPresent();
    }

    public TernaryValue orElse(boolean falseValue){
        if(falseValue) value = Optional.of(false);
        return this;
    }

    public static TernaryValue of(boolean trueValue){
        TernaryValue ternaryValue = new TernaryValue();
        if(trueValue) ternaryValue.value = Optional.of(true);
        return ternaryValue;
    }
}
