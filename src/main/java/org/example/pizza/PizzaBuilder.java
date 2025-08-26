package org.example.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating customizable pizzas step by step.
 *
 * <p>Implements the Builder pattern for creating complex objects with many optional parameters.
 *
 * <p>Provides sensible defaults: Thin crust, Medium size, Mozzarella cheese, Tomato sauce.
 *
 * <p>Usage example:
 * <pre>
 * PizzaBuilder builder = new PizzaBuilder();
 * builder.setCrust("Thick");
 * builder.addTopping("Pepperoni");
 * Pizza pizza = builder.build();
 * </pre>
 */
public final class PizzaBuilder {
    /** The type of crust to use for the pizza. */
    private String crust = "Thin";
    
    /** The size of the pizza. */
    private String size = "Medium";
    
    /** The type of cheese to use on the pizza. */
    private String cheese = "Mozzarella";
    
    /** The type of sauce to use on the pizza. */
    private String sauce = "Tomato";
    
    /** The list of toppings to include on the pizza. */
    private final List<String> toppings = new ArrayList<>();

    /**
     * Sets the crust type for the pizza.
     *
     * <p>Common types: "Thin", "Thick", "Stuffed", "Gluten-Free".
     *
     * @param value the crust type to use
     * @throws IllegalArgumentException if the value is null or blank
     */
    public void setCrust(final String value) {
        this.crust = requireText(value, "crust");
    }

    /**
     * Sets the size of the pizza.
     *
     * <p>Common sizes: "Small", "Medium", "Large", "Extra Large".
     *
     * @param value the size to use
     * @throws IllegalArgumentException if the value is null or blank
     */
    public void setSize(final String value) {
        this.size = requireText(value, "size");
    }

    /**
     * Sets the cheese type for the pizza.
     *
     * <p>Common types: "Mozzarella", "Cheddar", "Parmesan", "Provolone".
     * Note: Last call wins if called multiple times.
     *
     * @param value the cheese type to use
     * @throws IllegalArgumentException if the value is null or blank
     */
    public void setCheese(final String value) {
        this.cheese = requireText(value, "cheese");
    }

    /**
     * Sets the sauce type for the pizza.
     *
     * <p>Common types: "Tomato", "BBQ", "White", "Pesto", "Alfredo".
     * Note: Last call wins if called multiple times.
     *
     * @param value the sauce type to use
     * @throws IllegalArgumentException if the value is null or blank
     */
    public void setSauce(final String value) {
        this.sauce = requireText(value, "sauce");
    }

    /**
     * Adds a single topping to the pizza.
     *
     * <p>Toppings are added in order and duplicates are allowed.
     * Common toppings: "Pepperoni", "Mushrooms", "Olives", "Bell Peppers".
     *
     * @param value the topping name to add
     * @throws IllegalArgumentException if the value is null or blank
     */
    public void addTopping(final String value) {
        this.toppings.add(requireText(value, "topping"));
    }

    /**
     * Builds and returns a new, immutable Pizza instance.
     *
     * <p>The builder can be reused to create additional pizzas.
     *
     * @return a new, immutable Pizza instance with the configured attributes
     */
    public Pizza build() {
        return new Pizza(crust, size, cheese, sauce, toppings);
    }

    /**
     * Validates that a text value is non-null and non-blank.
     *
     * <p>Trims whitespace automatically.
     *
     * @param value the input string to validate
     * @param name the parameter name for error messages
     * @return the trimmed, validated string value
     * @throws IllegalArgumentException if the value is null or blank
     */
    private static String requireText(final String value, final String name) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(name + " must not be blank");
        }
        return value.trim();
    }
}


