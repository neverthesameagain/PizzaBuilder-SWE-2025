package org.example;

/**
 * Main entry point for the Pizza Builder application.
 *
 * <p>This is a simple demonstration of the Builder Pattern implementation
 * for creating customizable pizzas. The actual pizza building happens
 * through the PizzaBuilder class, not here.
 *
 * <p>To see the Pizza Builder in action, you would typically:
 * 1. Create a PizzaBuilder instance
 * 2. Configure your pizza (crust, size, cheese, sauce, toppings)
 * 3. Call build() to get your immutable Pizza object
 */
public class Main {
    /**
     * Application entry point.
     *
     * <p>Currently just prints a ready message. In a real application,
     * this might start a GUI, web server, or command-line interface
     * for building pizzas interactively.
     *
     * @param args command-line arguments (currently unused)
     */
    public static void main(final String[] args) {
        System.out.println("Pizza Builder ready");
        System.out.println("Use PizzaBuilder to create your custom pizza!");
        
        // Example of how to use the Pizza Builder (commented out since this is just demo)
        /*
        PizzaBuilder builder = new PizzaBuilder();
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.setCheese("Cheddar");
        builder.setSauce("BBQ");
        builder.addTopping("Chicken");
        builder.addTopping("Bacon");
        
        Pizza myPizza = builder.build();
        System.out.println("Your pizza: " + myPizza);
        */
    }
}