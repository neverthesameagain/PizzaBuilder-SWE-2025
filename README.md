# Pizza Builder - Builder Pattern Implementation

A Java project demonstrating the **Builder Pattern** for creating customizable pizzas. This project shows how to implement clean, maintainable code using object-oriented design principles.

## What This Project Does

This project implements a **Pizza Builder** that allows you to create custom pizzas step by step. Instead of having a complex constructor with many parameters, you can configure your pizza piece by piece using a fluent, easy-to-understand API.

## How It Works

### The Builder Pattern Explained

The Builder Pattern is a **creational design pattern** that helps you create complex objects step by step. Think of it like ordering a pizza:

1. **Start with a base** (crust type)
2. **Choose the size** (small, medium, large)
3. **Pick your cheese** (mozzarella, cheddar, etc.)
4. **Select your sauce** (tomato, BBQ, white, etc.)
5. **Add toppings** (pepperoni, mushrooms, olives, etc.)
6. **Finalize** your order

Each step builds on the previous one, and you can customize exactly what you want without worrying about the complexity behind the scenes.

### Project Structure

```
src/
├── main/java/org/example/
│   ├── Main.java              # Application entry point
│   └── pizza/
│       ├── Pizza.java         # The final pizza object (immutable)
│       └── PizzaBuilder.java  # The builder that creates pizzas
└── test/java/org/example/pizza/
    └── PizzaBuilderTest.java  # Comprehensive test suite
```

## Prerequisites

- Java 24 or higher
- Maven 3.6 or higher

### Running the Project

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd PizzaBuilder_Software_122201017_IndividualProject
   ```
2. **Build the project:**

   ```bash
   mvn clean compile
   ```
3. **Run the tests:**

   ```bash
   mvn test
   ```
4. **Run the application:**

   ```bash
   mvn exec:java -Dexec.mainClass="org.example.Main"
   ```

## How to Use the Pizza Builder

### Basic Usage

```java
// Create a new pizza builder
PizzaBuilder builder = new PizzaBuilder();

// Configure your pizza
builder.setCrust("Thick");
builder.setSize("Large");
builder.setCheese("Cheddar");
builder.setSauce("BBQ");
builder.addTopping("Pepperoni");
builder.addTopping("Mushrooms");
builder.addTopping("Olives");

// Build your pizza
Pizza myPizza = builder.build();

// Enjoy your pizza! 🍕
System.out.println("My pizza: " + myPizza);
```

### Using Defaults

If you don't specify certain attributes, the builder uses sensible defaults:

```java
// This creates a pizza with all defaults
Pizza defaultPizza = new PizzaBuilder().build();

// Defaults are:
// - Crust: "Thin"
// - Size: "Medium" 
// - Cheese: "Mozzarella"
// - Sauce: "Tomato"
// - Toppings: None
```

### Partial Customization

You can customize only what you want and use defaults for the rest:

```java
PizzaBuilder builder = new PizzaBuilder();

// Only change the crust and add toppings
builder.setCrust("Stuffed");
builder.addTopping("Pepperoni");
builder.addTopping("Extra Cheese");

Pizza pizza = builder.build();
// Result: Stuffed crust, Medium size, Mozzarella cheese, 
//         Tomato sauce, with Pepperoni and Extra Cheese
```

## Key Features

### **Fluent API**

Easy to read and chain method calls:

```java
PizzaBuilder builder = new PizzaBuilder();
builder.setCrust("Thin")
       .setSize("Large")
       .addTopping("Pepperoni");
```

### **Input Validation**

The builder validates all inputs to prevent errors:

```java
// This will throw an exception
builder.setCrust("");        // Empty string
builder.setCrust(null);      // Null value
builder.setCrust("   ");     // Just whitespace
```

### **Immutability**

Once a pizza is built, it cannot be changed:

```java
Pizza pizza = builder.build();
// pizza.setCrust("Thick"); // This won't work - Pizza is immutable!
```

### **Order Preservation**

Toppings are added in the exact order you specify:

```java
builder.addTopping("Pepperoni");  // First
builder.addTopping("Mushrooms");  // Second
builder.addTopping("Olives");     // Third
// Result: [Pepperoni, Mushrooms, Olives]
```

### **Reusability**

The same builder can create multiple pizzas:

```java
PizzaBuilder builder = new PizzaBuilder();

// First pizza
builder.setCrust("Thin");
Pizza pizza1 = builder.build();

// Second pizza (reusing the builder)
builder.setCrust("Thick");
Pizza pizza2 = builder.build();
```

## Testing

The project includes a comprehensive test suite that covers:

- ✅ **Basic functionality** - Setting and getting pizza attributes
- ✅ **Default values** - Ensuring defaults work correctly
- ✅ **Input validation** - Testing null and blank value handling
- ✅ **Immutability** - Verifying pizzas cannot be modified after creation
- ✅ **Edge cases** - Special characters, whitespace trimming, etc.
- ✅ **Multiple pizzas** - Building several pizzas with the same builder

### Running Tests

```bash
# Run all tests
mvn test

# Run tests with detailed output
mvn test -Dtest=PizzaBuilderTest

# Run specific test method
mvn test -Dtest=PizzaBuilderTest#buildsPizzaWithBuilderSetters
```

## Architecture & Design Principles

### **Separation of Concerns**

- **PizzaBuilder**: Handles configuration and validation
- **Pizza**: Represents the final, immutable pizza object
- **Main**: Simple entry point for demonstration

### **Encapsulation**

- Pizza constructor is package-private (only accessible by PizzaBuilder)
- Internal data is protected from external modification
- Public methods provide controlled access to pizza attributes

### **Immutability**

- All Pizza fields are final
- Collections are unmodifiable
- Defensive copying prevents external modification

### **Extensibility**

- Easy to add new pizza attributes
- Builder pattern makes adding new features straightforward
- Test suite ensures changes don't break existing functionality

## Code Quality

The project follows strict coding standards:

- **Checkstyle**: Enforces consistent code formatting and style
- **JUnit 5**: Modern testing framework with comprehensive assertions
- **Maven**: Standard build tool with dependency management
- **Java 24**: Latest LTS version with modern language features

### Code Quality Checks

```bash
# Run checkstyle validation
mvn validate

# Run tests and checkstyle
mvn clean verify
```

## Learning Objectives

This project demonstrates several important software engineering concepts:

1. **Design Patterns**: Builder Pattern implementation
2. **Object-Oriented Design**: Encapsulation, immutability, separation of concerns
3. **Testing**: Comprehensive unit testing with JUnit 5
4. **Code Quality**: Checkstyle enforcement and clean code principles
5. **Build Tools**: Maven project structure and dependency management

## Future Enhancements

Potential improvements that could be added:

- **Fluent Interface**: Method chaining for better readability
- **Validation Rules**: More sophisticated input validation
- **Pizza Types**: Predefined pizza configurations (Margherita, Pepperoni, etc.)
- **Price Calculation**: Cost calculation based on size and toppings
- **Persistence**: Save and load pizza configurations
- **GUI Interface**: Visual pizza builder application

## Contributing

This is an individual project, but the code demonstrates best practices that could be extended or improved. Feel free to:

- Study the code structure and design patterns
- Run the tests to understand the functionality
- Experiment with adding new features
- Use as a reference for implementing the Builder pattern

## Resources referred to

- [Builder Pattern - Wikipedia](https://en.wikipedia.org/wiki/Builder_pattern)
- [Java Design Patterns](https://refactoring.guru/design-patterns/java)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)

## License

This project is created for educational purposes to demonstrate software engineering principles and design patterns.
