package fr.il_totore.rp.util;

@FunctionalInterface
public interface TriFunction<A, B, C, O> {

    O apply(A a, B b, C c);
}
