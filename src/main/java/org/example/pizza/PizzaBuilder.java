package org.example.pizza;

import java.util.ArrayList;
import java.util.List;

/** Builder for creating customizable pizzas. */
public final class PizzaBuilder {
    /** Crust type. */ 
    private String crust = "Thin";
    
    /** Size . */
    private String size = "Medium";
    
    /** Cheese type. */
    private String cheese = "Mozzarella";
    
    /** Toppings list. */
    private final List<String> toppings = new ArrayList<>();

    /**
     * Sets the crust type.
     * @param value crust type
     * @throws IllegalArgumentException if null or blank
     */
    public void setCrust(final String value) {
        this.crust = requireText(value, "crust");
    }

    /**
     * Sets the size.
     * @param value size
     * @throws IllegalArgumentException if null or blank
     */
    public void setSize(final String value) {
        this.size = requireText(value, "size");
    }

    /**
     * Sets the cheese type.
     * @param value cheese type
     * @throws IllegalArgumentException if null or blank
     */
    public void setCheese(final String value) {
        this.cheese = requireText(value, "cheese");
    }

    /**
     * Adds a topping.
     * @param value topping
     * @throws IllegalArgumentException if null or blank
     */
    public void addTopping(final String value) {
        this.toppings.add(requireText(value, "topping"));
    }

    /**
     * Builds an immutable pizza.
     * @return new pizza
     */
    public Pizza build() {
        return new Pizza(crust, size, cheese, toppings);
    }

    /**
     * Validates non-null, non-blank text.
     * @param value input
     * @param name parameter name
     * @return trimmed value
     * @throws IllegalArgumentException if invalid
     */
    private static String requireText(final String value, final String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be blank");
        }
        return value.trim();
    }
}


