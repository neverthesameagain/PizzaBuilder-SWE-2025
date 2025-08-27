## Pizza Builder (Java)

A small Java project demonstrating the Builder pattern to create immutable pizzas with clear defaults and input validation.

### Requirements
- Java 24+
- Maven 3.6+

### Quick start
```bash
mvn clean test
```

### Why Builder?
Creating a pizza has several optional attributes. A telescoping constructor would be hard to read and maintain. The Builder pattern lets you set only what you need, step by step, and then build a single immutable result.

### How the Builder pattern is implemented
- `PizzaBuilder` (builder):
  - Holds mutable configuration (crust, size, cheese, toppings).
  - Validates inputs (non-null, non-blank, trims whitespace).
  - Produces the final object via `build()`.
- `Pizza` (product):
  - Immutable data class with `final` fields.
  - Constructor is package-private, so only the builder can create instances.
  - Returns an unmodifiable toppings list to preserve immutability.
- `Main` (demo): trivial entry point.

This separation lets you reuse a builder to produce multiple `Pizza` instances and keeps construction logic out of the immutable `Pizza` class.

### Usage
```java
PizzaBuilder builder = new PizzaBuilder();

builder.setCrust("Thick");
builder.setSize("Large");
builder.setCheese("Cheddar");
builder.addTopping("Pepperoni");

Pizza pizza = builder.build();
System.out.println(pizza);
```

### Defaults
- Crust: "Thin"
- Size: "Medium"
- Cheese: "Mozzarella"
- Toppings: none

### Project layout
```
src/
├─ main/java/org/example/
│  ├─ Main.java
│  └─ pizza/
│     ├─ Pizza.java
│     └─ PizzaBuilder.java
└─ test/java/org/example/pizza/
   └─ PizzaBuilderTest.java
```

### Tests and Checkstyle
```bash
# run tests
mvn clean test

# run Checkstyle + tests
mvn clean verify
```

### Notes
- `Pizza` is immutable; toppings are returned as an unmodifiable list.
- Builder validates and trims inputs; null/blank values throw `IllegalArgumentException`.
