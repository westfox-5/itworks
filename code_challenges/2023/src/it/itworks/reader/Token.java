package it.itworks.reader;

public class Token<T> {
    private final Location location;
    private final T value;

    public static Token<Double> ofDouble(Location loc, Double value) {
        return new Token<>(loc, value);
    }
    public static Token<Integer> ofInteger(Location loc, Integer value) {
        return new Token<>(loc, value);
    }
    public static Token<String> ofString(Location loc, String value) {
        return new Token<>(loc, value);
    }
    private Token(Location location, T value) {
        this.location = location;
        this.value = value;
    }

    public Location getLocation() {
        return location;
    }

    public T getValue() {
        return value;
    }

}
