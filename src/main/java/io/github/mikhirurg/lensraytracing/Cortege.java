package io.github.mikhirurg.lensraytracing;

public class Cortege<T, U, V> {

    private final T first;

    private final U second;

    private final V third;

    public static <T, U, V> Cortege<T, U, V> of(T first, U second, V third) {
        return new Cortege<>(first, second, third);
    }

    public Cortege(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}