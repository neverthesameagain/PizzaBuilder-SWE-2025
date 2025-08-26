package org.example.pizza;

import java.util.Collections;
import java.util.List;

/**
 * Represents a fully configured, immutable pizza.
 *
 * <p>This class is designed as a value object - once created, it cannot be changed.
 * All internal data is protected from external modification.
 *
 * <p>The Pizza object is created through the PizzaBuilder class.
 */
public final class Pizza {
    /** The type of crust (e.g., "Thin", "Thick", "Stuffed"). */
    private final String crust;
    
    /** The size of the pizza (e.g., "Small", "Medium", "Large"). */
    private final String size;
    
    /** The type of cheese used (e.g., "Mozzarella", "Cheddar"). */
    private final String cheese;
    
    /** The sauce type (e.g., "Tomato", "BBQ", "White"). */
    private final String sauce;
    
    /** List of toppings in the order they were added. */
    private final List<String> toppings;

    /**
     * Creates a new Pizza with the specified configuration.
     *
     * <p>Constructor is package-private to enforce Builder pattern usage.
     *
     * @param crustValue the type of crust
     * @param sizeValue the size of the pizza
     * @param cheeseValue the type of cheese
     * @param sauceValue the type of sauce
     * @param toppingsValue the list of toppings
     */
    Pizza(final String crustValue, final String sizeValue, final String cheeseValue,
          final String sauceValue, final List<String> toppingsValue) {
        this.crust = crustValue;
        this.size = sizeValue;
        this.cheese = cheeseValue;
        this.sauce = sauceValue;
        // Create a defensive copy to prevent external modification
        this.toppings = List.copyOf(toppingsValue);
    }

    /**
     * Gets the crust type of this pizza.
     *
     * @return the crust type, or null if not specified
     */
    public String getCrust() {
        return crust;
    }

    /**
     * Gets the size of this pizza.
     *
     * @return the size, or null if not specified
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets the cheese type used on this pizza.
     *
     * @return the cheese type, or null if not specified
     */
    public String getCheese() {
        return cheese;
    }

    /**
     * Gets the sauce type used on this pizza.
     *
     * @return the sauce type, or null if not specified
     */
    public String getSauce() {
        return sauce;
    }

    /**
     * Gets an unmodifiable view of the toppings list.
     *
     * <p>Any attempt to modify the returned list will throw an UnsupportedOperationException.
     *
     * @return an unmodifiable view of the toppings in insertion order
     */
    public List<String> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    /**
     * Returns a string representation of this pizza.
     *
     * @return a string representation of the pizza
     */
    @Override
    public String toString() {
        return "Pizza{"
            + "crust='" + crust + '\''
            + ", size='" + size + '\''
            + ", cheese='" + cheese + '\''
            + ", sauce='" + sauce + '\''
            + ", toppings=" + toppings
            + '}';
    }
}


