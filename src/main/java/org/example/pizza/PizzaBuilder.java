package org.example.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating customizable pizzas step by step.
 *
 * <p>This class implements the Builder pattern, which is useful when you need to
 * create complex objects with many optional parameters. Instead of having a
 * constructor with many parameters, you can build the object piece by piece.
 *
 * <p>The builder provides sensible defaults for common pizza configurations:
 * - Thin crust (light and crispy)
 * - Medium size (good for 2-3 people)
 * - Mozzarella cheese (classic pizza cheese)
 * - Tomato sauce (traditional pizza sauce)
 * - No toppings (customizable as needed)
 *
 * <p>Usage example:
 * <pre>
 * PizzaBuilder builder = new PizzaBuilder();
 * builder.setCrust("Thick");
 * builder.setSize("Large");
 * builder.addTopping("Pepperoni");
 * builder.addTopping("Mushrooms");
 * Pizza pizza = builder.build();
 * </pre>
 *
 * <p>Key features:
 * - Fluent API: Easy to chain method calls
 * - Input validation: Prevents invalid configurations
 * - Default values: Sensible defaults for quick pizza creation
 * - Order preservation: Toppings are added in the order specified
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
    
    /** 
     * The list of toppings to include on the pizza.
     * Using ArrayList to allow dynamic addition and preserve insertion order.
     */
    private final List<String> toppings = new ArrayList<>();

    /**
     * Sets the crust type for the pizza.
     *
     * <p>Common crust types include: "Thin", "Thick", "Stuffed", "Gluten-Free".
     * The value is validated to ensure it's not null or blank.
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
     * <p>Common sizes include: "Small", "Medium", "Large", "Extra Large".
     * The value is validated to ensure it's not null or blank.
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
     * <p>Common cheese types include: "Mozzarella", "Cheddar", "Parmesan", "Provolone".
     * Note: This is a "last call wins" approach - if you call setCheese multiple
     * times, only the last value will be used. This is intentional for simplicity.
     *
     * <p>The value is validated to ensure it's not null or blank.
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
     * <p>Common sauce types include: "Tomato", "BBQ", "White", "Pesto", "Alfredo".
     * Note: This is a "last call wins" approach - if you call setSauce multiple
     * times, only the last value will be used. This is intentional for simplicity.
     *
     * <p>The value is validated to ensure it's not null or blank.
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
     * <p>Toppings are added in the order they're called, and duplicates are allowed.
     * This gives you flexibility to create pizzas with multiple layers of the
     * same topping if desired (e.g., double pepperoni).
     *
     * <p>Common toppings include: "Pepperoni", "Mushrooms", "Olives", "Bell Peppers",
     * "Onions", "Sausage", "Bacon", "Chicken", "Pineapple".
     *
     * <p>The value is validated to ensure it's not null or blank.
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
     * <p>This method creates a Pizza object with all the configured attributes.
     * Once built, the Pizza cannot be modified, ensuring data integrity.
     *
     * <p>The builder can be reused after calling build() to create additional
     * pizzas with the same or different configurations.
     *
     * @return a new, immutable Pizza instance with the configured attributes
     */
    public Pizza build() {
        return new Pizza(crust, size, cheese, sauce, toppings);
    }

    /**
     * Validates that a text value is non-null and non-blank.
     *
     * <p>This helper method ensures that all string inputs are valid before
     * they're used to configure the pizza. It prevents common issues like
     * null pointer exceptions and empty strings.
     *
     * <p>The method trims whitespace from the input, so "  Pepperoni  " becomes
     * "Pepperoni" automatically.
     *
     * @param value the input string to validate
     * @param name the parameter name for error messages (e.g., "crust", "topping")
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


