package fr.il_totore.rp.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Condition<T> {

    private Optional<T> optional;

    private Condition(Optional<T> optional){
        this.optional = optional;
    }

    public Condition<T> onlyIf(Predicate<T> predicate){
        if(optional.isPresent() && !predicate.test(optional.get())) optional = Optional.empty();
        return this;
    }

    public T getIfElse(Predicate<T> predicate, T defaultValue){
        return predicate.test(optional.get()) ? optional.get() : defaultValue;
    }

    public T get() {
        return optional.get();
    }

    public boolean isPresent() {
        return optional.isPresent();
    }

    public void ifPresent(Consumer<? super T> action) {
        optional.ifPresent(action);
    }


    public Optional<T> filter(Predicate<? super T> predicate) {
        return optional.filter(predicate);
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        return optional.map(mapper);
    }

    public <U> Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) {
        return optional.flatMap(mapper);
    }

    public T orElse(T other) {
        return optional.orElse(other);
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return optional.orElseGet(supplier);
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        return optional.orElseThrow(exceptionSupplier);
    }

    public Optional<T> asOptional(){
        return optional;
    }

    public static <T> Condition<T> of(Optional<T> optional){
        return new Condition<>(optional);
    }

    public static <T> Condition<T> ofNullable(T t){
        return of(Optional.ofNullable(t));
    }

    public static <T> Condition<T> empty(){
        return of(Optional.empty());
    }
}
