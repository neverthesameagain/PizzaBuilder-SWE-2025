package org.example.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/** Minimal test suite for the PizzaBuilder class. */
@DisplayName("PizzaBuilder Tests")
class PizzaBuilderTest {
    /** Builder used for testing. */
    private PizzaBuilder builder;

    /** Sets up a fresh builder before each test. */
    @BeforeEach
    void setUp() {
        builder = new PizzaBuilder();
    }

    @Test
    @DisplayName("Should build pizza with all custom attributes")
    void buildsPizzaWithBuilderSetters() {
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.addTopping("Pepperoni");
        builder.addTopping("Olives");

        final Pizza pizza = builder.build();

        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Cheddar", pizza.getCheese());
        assertEquals(List.of("Pepperoni", "Olives"), pizza.getToppings());
    }

    @Test
    @DisplayName("Should create default pizza when no configuration provided")
    void buildsDefaultPizzaWhenNoSpecsProvided() {
        final Pizza pizza = builder.build();

        assertEquals("Thin", pizza.getCrust());
        assertEquals("Medium", pizza.getSize());
        assertEquals("Mozzarella", pizza.getCheese());
        assertTrue(pizza.getToppings().isEmpty());
    }

    @Test
    @DisplayName("Should preserve topping order and allow duplicates")
    void preservesToppingOrderAndAllowsDuplicates() {
        builder.addTopping("Pepperoni");
        builder.addTopping("Mushrooms");
        builder.addTopping("Pepperoni");
        builder.addTopping("Olives");

        final Pizza pizza = builder.build();

        assertEquals(List.of("Pepperoni", "Mushrooms", "Pepperoni", "Olives"),
            pizza.getToppings());
    }

    @Test
    @DisplayName("Should override previous values when setters called multiple times")
    void overridesPreviousValuesOnMultipleSetterCalls() {
        builder.setCrust("Thin");
        builder.setSize("Small");
        builder.setCheese("Mozzarella");

        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");

        final Pizza pizza = builder.build();

        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Cheddar", pizza.getCheese());
    }

    @Test
    @DisplayName("Should reject null and blank values for all attributes")
    void rejectsNullAndBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> builder.setCrust(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setCrust(""));
        assertThrows(IllegalArgumentException.class, () -> builder.setCrust("   "));

        assertThrows(IllegalArgumentException.class, () -> builder.setSize(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setSize(""));

        assertThrows(IllegalArgumentException.class, () -> builder.setCheese(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setCheese(""));

        assertThrows(IllegalArgumentException.class, () -> builder.addTopping(null));
        assertThrows(IllegalArgumentException.class, () -> builder.addTopping(""));
    }

    @Test
    @DisplayName("Should return unmodifiable toppings list")
    void returnsUnmodifiableToppingsList() {
        builder.addTopping("Pepperoni");
        final Pizza pizza = builder.build();

        assertThrows(UnsupportedOperationException.class, () -> {
            pizza.getToppings().add("Mushrooms");
        });
    }

    @Test
    @DisplayName("Should return correct pizza string representation")
    void returnsCorrectPizzaStringRepresentation() {
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.addTopping("Pepperoni");
        builder.addTopping("Mushrooms");

        final Pizza pizza = builder.build();
        final String pizzaString = pizza.toString();

        assertTrue(pizzaString.contains("crust='Thick'"));
        assertTrue(pizzaString.contains("size='Large'"));
        assertTrue(pizzaString.contains("cheese='Cheddar'"));
        assertTrue(pizzaString.contains("toppings=[Pepperoni, Mushrooms]"));
    }
}


