package org.example.pizza;

import java.util.Collections;
import java.util.List;

/** Immutable pizza with chosen attributes. */
public final class Pizza {
    /** Crust type. */
    private final String crust;
    
    /** Size. */
    private final String size;
    
    /** Cheese type. */
    private final String cheese;
    
    /** Toppings as list. */
    private final List<String> toppings;

    /**
     * Creates a pizza with the given attributes.
     * @param crustValue crust type
     * @param sizeValue size
     * @param cheeseValue cheese type
     * @param toppingsValue toppings list
     */
    Pizza(final String crustValue, final String sizeValue, final String cheeseValue,
          final List<String> toppingsValue) {
        this.crust = crustValue;
        this.size = sizeValue;
        this.cheese = cheeseValue;
        this.toppings = List.copyOf(toppingsValue);
    }

    /**
     * Returns the crust type.
     * @return the crust type
     */
    public String getCrust() {
        return crust;
    }

    /**
     * Returns the size.
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * Returns the cheese type.
     * @return the cheese type
     */
    public String getCheese() {
        return cheese;
    }

    /**
     * Returns an unmodifiable toppings list.
     * @return toppings list
     */
    public List<String> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    /**
     * Returns a string representation.
     * @return string representation
     */
    @Override
    public String toString() {
        return "Pizza{"
            + "crust='" + crust + '\''
            + ", size='" + size + '\''
            + ", cheese='" + cheese + '\''
            + ", toppings=" + toppings
            + '}';
    }
}


