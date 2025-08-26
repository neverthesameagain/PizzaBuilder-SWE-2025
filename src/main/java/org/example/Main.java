package org.example;

/**
 * Main entry point for the Pizza Builder application.
 *
 * <p>Demonstrates the Builder Pattern for creating customizable pizzas.
 * The actual pizza building happens through the PizzaBuilder class.
 */
public class Main {
    /**
     * Application entry point.
     *
     * <p>Prints a ready message and demonstrates how to use the Pizza Builder.
     *
     * @param args command-line arguments (currently unused)
     */
    public static void main(final String[] args) {
        System.out.println("Pizza Builder ready");
        System.out.println("Use PizzaBuilder to create your custom pizza!");
        
        // Example usage (commented out for demo)
        /*
        PizzaBuilder builder = new PizzaBuilder();
        builder.setCrust("Thick");
        builder.setSize("Large");
        builder.addTopping("Pepperoni");
        Pizza myPizza = builder.build();
        System.out.println("Your pizza: " + myPizza);
        */
    }
}