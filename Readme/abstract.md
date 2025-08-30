# Java

## Lesson 2: Polymorphism

### What is Polymorphism?

**Polymorphism = "many forms"**

In Java, polymorphism means a single interface (method signature) can represent different implementations, depending on the object type at runtime.

- A **superclass reference** can point to a **subclass object**.
- At **runtime**, the actual object type determines which method implementation executes (dynamic method dispatch).

### Types of Polymorphism in Java

#### 1. Compile-time Polymorphism (Static Binding / Method Overloading)

- Decided at **compile time**.
- Achieved by **method overloading** (same method name, but different parameter list).

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```
##### Rules

1. Must change **parameter list** (number, type, or order).
2. Return type **can** change, but not alone.
3. Access modifier can change.
4. Exceptions can differ.

#### 2. Runtime Polymorphism (Dynamic Binding / Method Overriding)

- Decided at **runtime**.
- Achieved by **method overriding** (child class redefines parent method).

##### How Runtime Polymorphism Works

- The **reference type** (left side) defines what methods you can call.
- The **object type** (right side, actual object) decides which version of the method executes.

##### Rules

- Method name, parameters, and return type must match the superclass.
- Cannot reduce **access level** (public → protected ❌).
- Cannot throw broader **checked exceptions**.
- Methods marked `final`, `static`, or `private` **cannot be overridden**.
- Use `@Override` annotation to improve readability and avoid mistakes.

```java
class Human {
    public void speak() { 
        // Human speak 
    }
}

class PolymorphismExample {
    @Override
    public void speak(){ 
        // Musician sing 
    }

    public static void main(String[] args){
        Human h1 = new Artist();   // Reference = Human, Object = Artist
        Human h2 = new Musician(); // Reference = Human, Object = Musician
        
        h1.speak(); // Executes Human.speak() if Artist does not override
        h.speak(); // calls Musician's overridden Musician.speak() at runtime
    }
}
```

### Key Differences Between Overriding & Overloading

| Feature                   | Overriding (Runtime) | Overloading (Compile-time)          |
|---------------------------|----------------------|-------------------------------------|
| **Polymorphism Type**     | Runtime              | Compile-time                        |
| **Method Signature**      | Must be same         | Must be different                   |
| **Return Type**           | Same or subtype      | Can be different (if params differ) |
| **Access Modifier**       | Cannot reduce        | Can change                          |
| **Inheritance Required?** | Yes                  | No (same class is enough)           |
| **Final/Static Methods**  | Cannot be overridden | Can be overloaded                   |


### Why Polymorphism is Useful

- **Code Reusability:** Methods can accept parent type, but work with all subclasses.
- **Flexibility:** New subclasses can be added without changing existing code.
- **Cleaner Code:** Avoids duplication — one interface, many behaviors.


