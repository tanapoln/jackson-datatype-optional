package com.tanapoln;

/**
 * Optional value, use same interface as of JDK8. Use with jackson to parse JSON along with information about
 * whether value intentionally set as null or it's absent.
 *
 * @param <T> type
 * @author Tanapol Nearunchorn
 */
public class Optional<T> {
    private T value;
    private boolean present = false;

    protected Optional() {
    }

    /**
     * Check if value is sent with json, whether it is null or blank.
     *
     * @return
     */
    public boolean isPresent() {
        return present;
    }

    /**
     * Gets value.
     *
     * @return value
     */
    public T get() {
        return value;
    }

    /**
     * Return Optional with present=false.
     *
     * @param <T>
     * @return
     */
    public static <T> Optional<T> absent() {
        Optional<T> o = new Optional<>();
        o.present = false;

        return o;
    }

    /**
     * Return Optional with present=true and value=null.
     *
     * @param <T>
     * @return
     */
    public static <T> Optional<T> presentAsNull() {
        Optional<T> o = new Optional<>();
        o.present = true;

        return o;
    }

    /**
     * Build Optional from value which allow null, present is set to be true
     *
     * @param value value
     * @param <T>   type
     * @return option of a given type with value set
     */
    public static <T> Optional<T> ofNullable(T value) {
        Optional<T> o = new Optional<>();
        o.value = value;
        o.present = true;

        return o;
    }

    /**
     * Build Optional from value, if value is null, exception will be thrown
     *
     * @param value value
     * @param <T>   type
     * @return optional of a given type
     */
    public static <T> Optional<T> of(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }

        Optional<T> o = new Optional<>();
        o.value = value;
        o.present = true;

        return o;
    }

    @Override
    public String toString() {
        return "Optional{" +
                "value=" + value +
                ", present=" + present +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Optional<?> optional = (Optional<?>) o;

        if (present != optional.present) return false;
        return value != null ? value.equals(optional.value) : optional.value == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (present ? 1 : 0);
        return result;
    }
}
