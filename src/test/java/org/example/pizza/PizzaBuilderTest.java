package org.example.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Comprehensive test suite for the PizzaBuilder class.
 *
 * <p>Tests core functionality: attribute setting, validation, immutability, and edge cases.
 */
@DisplayName("PizzaBuilder Tests")
class PizzaBuilderTest {
    
    /** The PizzaBuilder instance used for testing. */
    private PizzaBuilder builder;
    
    /**
     * Set up a fresh PizzaBuilder instance before each test.
     */
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
        builder.setSauce("BBQ");
        builder.addTopping("Pepperoni");
        builder.addTopping("Olives");
        
        final Pizza pizza = builder.build();

        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Cheddar", pizza.getCheese());
        assertEquals("BBQ", pizza.getSauce());
        assertEquals(List.of("Pepperoni", "Olives"), pizza.getToppings());
    }

    @Test
    @DisplayName("Should create default pizza when no configuration provided")
    void buildsDefaultPizzaWhenNoSpecsProvided() {
        final Pizza pizza = builder.build();
        
        assertEquals("Thin", pizza.getCrust());
        assertEquals("Medium", pizza.getSize());
        assertEquals("Mozzarella", pizza.getCheese());
        assertEquals("Tomato", pizza.getSauce());
        assertTrue(pizza.getToppings().isEmpty());
    }

    @Test
    @DisplayName("Should preserve topping order and allow duplicates")
    void preservesToppingOrderAndAllowsDuplicates() {
        builder.addTopping("Pepperoni");
        builder.addTopping("Mushrooms");
        builder.addTopping("Pepperoni"); // Duplicate
        builder.addTopping("Olives");
        
        final Pizza pizza = builder.build();
        
        assertEquals(List.of("Pepperoni", "Mushrooms", "Pepperoni", "Olives"), 
                    pizza.getToppings());
    }

    @Test
    @DisplayName("Should handle multiple toppings correctly")
    void handlesMultipleToppings() {
        final List<String> expectedToppings = Arrays.asList(
            "Pepperoni", "Mushrooms", "Olives", "Bell Peppers", "Onions"
        );
        
        for (String topping : expectedToppings) {
            builder.addTopping(topping);
        }
        
        final Pizza pizza = builder.build();
        assertEquals(expectedToppings, pizza.getToppings());
        final int expectedToppingCount = expectedToppings.size();
        assertEquals(expectedToppingCount, pizza.getToppings().size());
    }

    @Test
    @DisplayName("Should override previous values when setters called multiple times")
    void overridesPreviousValuesOnMultipleSetterCalls() {
        builder.setCrust("Thin");
        builder.setSize("Small");
        builder.setCheese("Mozzarella");
        builder.setSauce("Tomato");
        
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.setSauce("BBQ");
        
        final Pizza pizza = builder.build();
        
        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Cheddar", pizza.getCheese());
        assertEquals("BBQ", pizza.getSauce());
    }

    @Test
    @DisplayName("Should trim whitespace from input values")
    void trimsWhitespaceFromInputValues() {
        builder.setCrust("  Thick  ");
        builder.setSize("  Large  ");
        builder.setCheese("  Cheddar  ");
        builder.setSauce("  BBQ  ");
        builder.addTopping("  Pepperoni  ");
        
        final Pizza pizza = builder.build();
        
        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Cheddar", pizza.getCheese());
        assertEquals("BBQ", pizza.getSauce());
        assertEquals(List.of("Pepperoni"), pizza.getToppings());
    }

    @Test
    @DisplayName("Should reject null and blank values for all attributes")
    void rejectsNullAndBlankValues() {
        // Test crust
        assertThrows(IllegalArgumentException.class, () -> builder.setCrust(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setCrust(""));
        assertThrows(IllegalArgumentException.class, () -> builder.setCrust("   "));
        
        // Test size
        assertThrows(IllegalArgumentException.class, () -> builder.setSize(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setSize(""));
        
        // Test cheese
        assertThrows(IllegalArgumentException.class, () -> builder.setCheese(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setCheese(""));
        
        // Test sauce
        assertThrows(IllegalArgumentException.class, () -> builder.setSauce(null));
        assertThrows(IllegalArgumentException.class, () -> builder.setSauce(""));
        
        // Test toppings
        assertThrows(IllegalArgumentException.class, () -> builder.addTopping(null));
        assertThrows(IllegalArgumentException.class, () -> builder.addTopping(""));
    }

    @Test
    @DisplayName("Should create immutable pizza object")
    void createsImmutablePizzaObject() {
        builder.addTopping("Pepperoni");
        final Pizza pizza = builder.build();
        
        assertThrows(UnsupportedOperationException.class, () -> {
            pizza.getToppings().add("Mushrooms");
        });
    }

    @Test
    @DisplayName("Should return unmodifiable toppings list")
    void returnsUnmodifiableToppingsList() {
        builder.addTopping("Pepperoni");
        final Pizza pizza = builder.build();
        
        assertThrows(UnsupportedOperationException.class, () -> {
            pizza.getToppings().add("Mushrooms");
        });
        
        assertThrows(UnsupportedOperationException.class, () -> {
            pizza.getToppings().remove(0);
        });
        
        assertThrows(UnsupportedOperationException.class, () -> {
            pizza.getToppings().clear();
        });
    }

    @Test
    @DisplayName("Should build multiple pizzas with same builder")
    void buildsMultiplePizzasWithSameBuilder() {
        builder.setCrust("Thin");
        builder.setSize("Small");
        builder.addTopping("Pepperoni");
        final Pizza pizza1 = builder.build();
        
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.addTopping("Mushrooms");
        final Pizza pizza2 = builder.build();
        
        assertNotNull(pizza1);
        assertNotNull(pizza2);
        assertFalse(pizza1.getCrust().equals(pizza2.getCrust()));
        assertFalse(pizza1.getSize().equals(pizza2.getSize()));
        assertFalse(pizza1.getToppings().equals(pizza2.getToppings()));
    }

    @Test
    @DisplayName("Should handle special characters in topping names")
    void handlesSpecialCharactersInToppingNames() {
        builder.addTopping("Pepperoni & Sausage");
        builder.addTopping("Mushrooms (Portobello)");
        builder.addTopping("Olives - Black");
        
        final Pizza pizza = builder.build();
        
        assertEquals(List.of("Pepperoni & Sausage", "Mushrooms (Portobello)", "Olives - Black"), 
                    pizza.getToppings());
    }

    @Test
    @DisplayName("Should create pizza with only toppings (using all defaults)")
    void createsPizzaWithOnlyToppings() {
        builder.addTopping("Pepperoni");
        builder.addTopping("Mushrooms");
        
        final Pizza pizza = builder.build();
        
        assertEquals("Thin", pizza.getCrust());
        assertEquals("Medium", pizza.getSize());
        assertEquals("Mozzarella", pizza.getCheese());
        assertEquals("Tomato", pizza.getSauce());
        assertEquals(List.of("Pepperoni", "Mushrooms"), pizza.getToppings());
    }

    @Test
    @DisplayName("Should create pizza with only crust and size (using defaults for others)")
    void createsPizzaWithOnlyCrustAndSize() {
        builder.setCrust("Thick");
        builder.setSize("Large");
        
        final Pizza pizza = builder.build();
        
        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Mozzarella", pizza.getCheese()); // Default
        assertEquals("Tomato", pizza.getSauce()); // Default
        assertTrue(pizza.getToppings().isEmpty()); // Default
    }

    @Test
    @DisplayName("Should handle empty toppings list correctly")
    void handlesEmptyToppingsListCorrectly() {
        final Pizza pizza = builder.build();
        
        assertTrue(pizza.getToppings().isEmpty());
        assertEquals(0, pizza.getToppings().size());
    }

    @Test
    @DisplayName("Should return correct pizza string representation")
    void returnsCorrectPizzaStringRepresentation() {
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.setSauce("BBQ");
        builder.addTopping("Pepperoni");
        builder.addTopping("Mushrooms");
        
        final Pizza pizza = builder.build();
        final String pizzaString = pizza.toString();
        
        assertTrue(pizzaString.contains("crust='Thick'"));
        assertTrue(pizzaString.contains("size='Large'"));
        assertTrue(pizzaString.contains("cheese='Cheddar'"));
        assertTrue(pizzaString.contains("sauce='BBQ'"));
        assertTrue(pizzaString.contains("toppings=[Pepperoni, Mushrooms]"));
    }
}


