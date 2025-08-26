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
 * <p>These tests verify that the PizzaBuilder correctly:
 * - Sets and retrieves all pizza attributes
 * - Handles default values properly
 * - Validates input data
 * - Creates immutable Pizza objects
 * - Preserves topping order and allows duplicates
 * - Handles edge cases and error conditions
 */
@DisplayName("PizzaBuilder Tests")
class PizzaBuilderTest {
    
    /** The PizzaBuilder instance used for testing. */
    private PizzaBuilder builder;
    
    /**
     * Set up a fresh PizzaBuilder instance before each test.
     * This ensures that each test starts with a clean, default configuration.
     */
    @BeforeEach
    void setUp() {
        builder = new PizzaBuilder();
    }

    @Test
    @DisplayName("Should build pizza with all custom attributes")
    void buildsPizzaWithBuilderSetters() {
        // Configure a custom pizza
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.setSauce("BBQ");
        builder.addTopping("Pepperoni");
        builder.addTopping("Olives");
        
        // Build the pizza
        final Pizza pizza = builder.build();

        // Verify all attributes are set correctly
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
        
        // Verify default values
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
        
        // Verify order is preserved and duplicates are allowed
        assertEquals(List.of("Pepperoni", "Mushrooms", "Pepperoni", "Olives"), 
                    pizza.getToppings());
    }

    @Test
    @DisplayName("Should handle multiple toppings correctly")
    void handlesMultipleToppings() {
        final List<String> expectedToppings = Arrays.asList(
            "Pepperoni", "Mushrooms", "Olives", "Bell Peppers", 
            "Onions", "Sausage", "Bacon"
        );
        
        // Add all toppings
        for (String topping : expectedToppings) {
            builder.addTopping(topping);
        }
        
        final Pizza pizza = builder.build();
        assertEquals(expectedToppings, pizza.getToppings());
        final int expectedToppingCount = 7;
        assertEquals(expectedToppingCount, pizza.getToppings().size());
    }

    @Test
    @DisplayName("Should override previous values when setters called multiple times")
    void overridesPreviousValuesOnMultipleSetterCalls() {
        // Set initial values
        builder.setCrust("Thin");
        builder.setSize("Small");
        builder.setCheese("Mozzarella");
        builder.setSauce("Tomato");
        
        // Override with new values
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.setSauce("BBQ");
        
        final Pizza pizza = builder.build();
        
        // Verify only the last values are used
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
        
        // Verify whitespace is trimmed
        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Cheddar", pizza.getCheese());
        assertEquals("BBQ", pizza.getSauce());
        assertEquals(List.of("Pepperoni"), pizza.getToppings());
    }

    @Test
    @DisplayName("Should reject null crust values")
    void rejectsNullCrustValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setCrust(null);
        });
    }

    @Test
    @DisplayName("Should reject blank crust values")
    void rejectsBlankCrustValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setCrust("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setCrust("   ");
        });
    }

    @Test
    @DisplayName("Should reject null size values")
    void rejectsNullSizeValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setSize(null);
        });
    }

    @Test
    @DisplayName("Should reject blank size values")
    void rejectsBlankSizeValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setSize("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setSize("   ");
        });
    }

    @Test
    @DisplayName("Should reject null cheese values")
    void rejectsNullCheeseValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setCheese(null);
        });
    }

    @Test
    @DisplayName("Should reject blank cheese values")
    void rejectsBlankCheeseValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setCheese("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setCheese("   ");
        });
    }

    @Test
    @DisplayName("Should reject null sauce values")
    void rejectsNullSauceValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setSauce(null);
        });
    }

    @Test
    @DisplayName("Should reject blank sauce values")
    void rejectsBlankSauceValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setSauce("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.setSauce("   ");
        });
    }

    @Test
    @DisplayName("Should reject null topping values")
    void rejectsNullToppingValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.addTopping(null);
        });
    }

    @Test
    @DisplayName("Should reject blank topping values")
    void rejectsBlankToppingValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.addTopping("");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.addTopping("   ");
        });
    }

    @Test
    @DisplayName("Should create immutable pizza object")
    void createsImmutablePizzaObject() {
        builder.addTopping("Pepperoni");
        final Pizza pizza = builder.build();
        
        // Verify the pizza is immutable
        assertThrows(UnsupportedOperationException.class, () -> {
            pizza.getToppings().add("Mushrooms");
        });
    }

    @Test
    @DisplayName("Should return unmodifiable toppings list")
    void returnsUnmodifiableToppingsList() {
        builder.addTopping("Pepperoni");
        final Pizza pizza = builder.build();
        
        // Verify the list is unmodifiable
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
        // Build first pizza
        builder.setCrust("Thin");
        builder.setSize("Small");
        builder.addTopping("Pepperoni");
        final Pizza pizza1 = builder.build();
        
        // Build second pizza with different configuration
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.addTopping("Mushrooms");
        final Pizza pizza2 = builder.build();
        
        // Verify both pizzas are different
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
        
        // Verify defaults are used for other attributes
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
        
        // Verify specified values and defaults
        assertEquals("Thick", pizza.getCrust());
        assertEquals("Large", pizza.getSize());
        assertEquals("Mozzarella", pizza.getCheese()); // Default
        assertEquals("Tomato", pizza.getSauce()); // Default
        assertTrue(pizza.getToppings().isEmpty()); // Default
    }

    @Test
    @DisplayName("Should handle empty toppings list correctly")
    void handlesEmptyToppingsListCorrectly() {
        // Don't add any toppings
        final Pizza pizza = builder.build();
        
        assertTrue(pizza.getToppings().isEmpty());
        assertEquals(0, pizza.getToppings().size());
    }

    @Test
    @DisplayName("Should create pizza with single topping")
    void createsPizzaWithSingleTopping() {
        builder.addTopping("Pepperoni");
        
        final Pizza pizza = builder.build();
        
        assertEquals(1, pizza.getToppings().size());
        assertEquals("Pepperoni", pizza.getToppings().get(0));
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
        
        // Verify the string contains all pizza attributes
        assertTrue(pizzaString.contains("crust='Thick'"));
        assertTrue(pizzaString.contains("size='Large'"));
        assertTrue(pizzaString.contains("cheese='Cheddar'"));
        assertTrue(pizzaString.contains("sauce='BBQ'"));
        assertTrue(pizzaString.contains("toppings=[Pepperoni, Mushrooms]"));
    }
}


