package fr.il_totore.rp.util;

import java.util.Comparator;

public class Comparison<T extends Comparable<T>> {

    private T max;
    private T min;
    private boolean equals;

    private Comparison(T max, T min) {
        this.max = max;
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public T getMin() {
        return min;
    }

    public Comparison<T> compare(Comparator<T> comparator) {
        T newMax = max;
        T newMin = min;
        int result = comparator.compare(max, min);
        equals = false;
        if(result < 0) {
            min = newMax;
            max = newMin;
        } else if(result == 0) {
            equals = true;
        }
        return this;
    }

    public Comparison<T> compare() {
        return compare(T::compareTo);
    }

    public boolean areEquals(){
        return equals;
    }

    public static <T extends Comparable<T>> Comparison<T> of(T min, T max, Comparator<T> comparator){
        return new Comparison<>(max, min).compare(comparator);
    }

    public static <T extends Comparable<T>> Comparison<T> of(T min, T max){
        return new Comparison<>(max, min).compare();
    }
}
