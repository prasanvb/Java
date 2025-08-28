# Java Objects

## Method Structure & Parameters

- A method in Java is a block of code designed to perform a specific task.

```text
modifier returnType methodName(parameters) {
    // method body (logic)
    return value; // optional
}
```

### Key Points

- Methods cannot be defined inside other methods.
- Only the main method is executed automatically by the JVM. Other methods must be called explicitly.
- To call a non-static method from main, you must create an object of the class.
- Static methods can be called directly without creating objects.
- Method parameters act like local variables (scope is only inside the method).
- Method can accept at most 255 parameters

## Types of Modifiers

### Access Modifiers

- `public`: Accessible from anywhere.
- `private`: Accessible only inside the same class.
- `protected`: Accessible within the same package + subclasses in other packages.
- **default** (no modifier): Accessible only within the same package.

### Non-Access Modifiers

- `final`: Method cannot be overridden in subclasses.
- `static`: Belongs to the class, not an object. Can be called without object creation.
- `abstract`: Method without a body; subclasses must implement it.
  - Abstract Method must be declared inside an abstract class, and the class cannot be instantiated.
- `synchronized`: Only one thread can access this method at a time (multi-threading).
- `strictfp`: Ensures consistent floating-point calculations across platforms.
- `native`: Indicates the method is implemented in another language (like C/C++ via JNI).


## Instanceof (Java 16)

- The instanceof operator is a binary operator in Java.
- It is used to test whether an object is an instance of a specific class, subclass, or implements an interface and returns a boolean value.
- Works with: Classes, Subclasses, Interfaces, Arrays
- `objectReference instanceof ClassName`
  - objectReference → the object being checked.
  - ClassName → the target class or interface.
- If object is null, `instanceof` always returns `false`.
- Compiler checks if the reference type and target type are related. If unrelated, you get a compile-time error.
    ```text
    String s = "hello";
    System.out.println(s instanceof Integer); // ❌ compile error
    ```

## Pattern Matching for instanceof

- Type check + variable binding in a single expression.
- Compiler handles the cast safely.
- Syntax
    ```text
    if (object instanceof Type variableName) {
        // variableName is usable here
    }
    ```
    - `object` → expression being tested.
    - `Type` → target class/interface.
    - `variableName` → casted object (only exists inside block).

## Enhanced Switch Expressions (Java 17)

- No break statements required.
- More concise and readable.
- Returns value directly from the switch.
- Prevents fall-through errors.
- Combines multiple cases into one.
- Block Syntax with yield. Useful for multi-line logic in a case.
- Improves readability and reduces repetition.

## Constructors in-depth

- **Default Constructor**: If **no constructor** is declared in a class, the Java compiler automatically generates a **no-argument default constructor**, which calls `super()` and leaves fields with default values (e.g., `null` for objects, `0` for numbers)
- **Default Constructor Behavior**: Java creates a default no-arg constructor only if none is defined. If you define one (even a no-arg one), the compiler does not generate any additional default constructor.
- **`super()` Invocation**: A constructor always starts by calling its superclass constructor. If not explicitly written, the compiler inserts `super()` automatically (which calls the no-arg constructor of the superclass).
- **Parameterized Constructor**: Allows initializing instance variables at object creation by accepting parameters. Remaining variables retain their default values.- **Constructor Overloading**: A class can have multiple constructors with different parameter lists. The JVM picks the right one at instantiation by matching the arguments.
- **`this()` Constructor Chaining**: You can call another constructor of the same class using `this()`. If used, the compiler does **not** insert `super()` automatically---you must ensure correct chaining.
- **Example with Inheritance and Chaining**: Constructors cascade: child → own constructors → possibly another constructor via `this(...)` → `super()` → up to `Object` class.
- **Access Modifiers**: Constructors can have any access modifier: `public`, `protected`, package-private (default), or `private`.
- **Name and No Return Type**: The constructor name must **match the class name** and it must not declare a return type (not even `void`).
- **Calling Constructors**: Within a class, you can only invoke other constructors using `this(...)` or call superclass constructors via `super(...)`.
- **All Classes Have Constructors**: Abstract classes can have constructors, but **interfaces cannot**.
- **Instance Members Not Accessible Before Super**: You cannot refer to instance methods or variables before the superclass constructor completes.
- **Superclass Default Constructor Requirement**: If the superclass lacks a no-arg constructor, your subclass must explicitly call `super(...)` with appropriate arguments, or compilation fails. 

## Access Modifiers for Constructors

- **`public` Constructor**: Accessible from anywhere (given class is visible).
- **`protected` Constructor**: Accessible within the same package or from subclasses in other packages.
- **Default (package-private)**: Accessible only within the same package.
- **`private` Constructor**: Accessible only within its own class.

## Use Cases for `private` Constructors

### Singleton Design Pattern

**Goal**: Ensure that a class has only **one instance** in the entire JVM.
**Why use private constructor?**
- To prevent external instantiation (`new` keyword).
- Instance creation is controlled internally via a static method.
  **Example**: Database connection manager, logging system.

### **Utility or Helper Classes**

**Goal**: Contain only **static methods or constants**.
**Why use private constructor?**
- Prevents creation of unnecessary objects (no sense in creating an instance for static methods).
**Examples**: `java.lang.Math`, `java.util.Collections`.

```text
public class MathUtils {
    // Prevent instantiation
    private MathUtils() { }

    public static int square(int n) {
        return n * n;
    }
}

// Usage
int result = MathUtils.square(5); // No object needed
```

### **Immutable Classes with Factory Methods**

**Goal**: Provide **controlled instance creation** through static factory methods.
**Why use private constructor?**
- Forces developers to use factory methods (`of`, `valueOf`, `getInstance`, etc.).

```text
public class Color {
    private String name;

    // Private constructor
    private Color(String name) {
        this.name = name;
    }

    // Factory methods
    public static Color red()   { return new Color("Red"); }
    public static Color blue()  { return new Color("Blue"); }
}
```

### **Restricting Inheritance**

**Goal**: Prevent subclassing of a class.
**Why use private constructor?**
- If all constructors are private, no subclass can extend it because the superclass constructor cannot be called.
- Often combined with `final` modifier.
**Example**: Useful in constants-only classes.

```text
public final class Constants {
    public static final String APP_NAME = "MyApp";

    // Prevent inheritance & instantiation
    private Constants() { }
}
```