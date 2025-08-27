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
