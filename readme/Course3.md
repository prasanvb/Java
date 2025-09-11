# OOPS - Inheritance, Polymorphism, Interface, Abstract Class, Encapsulation, Records

## Lesson 1: Inheritance

                ┌──────────────┐
                │    Human     │
                ├──────────────┤
                │ - name:String│
                │ - age:int    │
                │ - gender:String │
                ├──────────────┤
                │ + walk()     │
                │ + speak()    │
                └───────▲──────┘
                        │
        ┌───────────────┼─────────────────┐
        │                                   │
┌──────────────┐                   ┌───────────────┐
│   Artist     │                   │   Musician    │
├──────────────┤                   ├───────────────┤
│              │                   │               │
├──────────────┤                   ├───────────────┤
│ + preparePainting() │             │ + playKeyboard() │
│ (inherited walk())  │             │ (inherited walk())│
│ (inherited speak()) │             │ + speak() [overridden] │
└──────────────┘                   └───────────────┘

### What is Inheritance?

- Inheritance is an Object-Oriented Programming (OOP) concept where one class (child/subclass) derives properties and behaviors (fields and methods) from another class (parent/superclass).
- `extends` keyword is used for inheritance.
- Java only supports single inheritance (a class can extend only one direct parent). Java does not allow multiple inheritance of classes to avoid ambiguity
- In Java, every class ultimately inherits from `java.lang.Object`.
    - If you don’t write an extends clause, the compiler treats your class as implicitly extending Object. That’s why all classes have methods like `equals`, `hashCode`, and `toString` available.

**Method Overriding**: If a subclass defines a method with the same signature as the superclass method, it overrides the parent's version. This allows the child class to provide its own implementation.

- `@Override` Indicates that a method declaration is intended to override a method declaration in a supertype

**Purpose:**

- **Reusability:** Avoids code duplication by reusing common attributes/methods from a superclass. Instead of redefining the same behavior (e.g., walk, speak) in multiple classes, we define them once in the parent class (Human) and inherit them in subclasses.
- **Polymorphism:** Allows treating different subclasses in a uniform way through their superclass reference.

**Example analogy:**

- Human → superclass
- Artist, Musician → subclasses (all humans, but with extra talents)

## final and inheritance

- **final class**: cannot be subclassed. A compile-time error occurs if you try to extends a final class.
- **final method**: cannot be overridden in a subclass.
- **final variable**: once initialized, it cannot be reassigned (you can set it in the declaration or constructor, but not modify afterward).

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

        static void main(String[] args){
            Human h1 = new Artist();   // Reference = Human, Object = Artist
            Human h2 = new Musician(); // Reference = Human, Object = Musician

            h1.speak(); // Executes Human.speak() if Artist does not override
            h.speak(); // calls Musician's overridden Musician.speak() at runtime
        }
    }
```

### Key Differences Between Overriding & Overloading

| Feature                   | Overriding (Runtime) | Overloading (Compile-time)          |
| ------------------------- | -------------------- | ----------------------------------- |
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

## Lesson 3: Interfaces

### What is an Interface?

- An **interface** in Java is similar to a class, but instead of defining how things work, it only defines **what must be done**.
- It is a **blueprint for classes**, just like classes are blueprints for objects.
- Interfaces contain:
    - **Abstract methods** (methods without a body).
    - **Constants** (all fields are implicitly `public static final`).
- Purpose: Interfaces define a **contract** for classes to follow. They don’t tell **how**, but **what** needs to be done.

### Characteristics of Interfaces

- **All methods** are `public abstract` by default (unless declared as `default` or `static`).
- **All variables** are `public static final` (constants).
- An interface **cannot have constructors** (so they cannot be instantiated).
- A class uses the **`implements`** keyword to inherit an interface.
- An interface can **extend another interface**.
- A class can **implement multiple interfaces**, supporting multiple inheritance indirectly.
- **Default methods** (Java 8+) allow interfaces to evolve without breaking existing code.
    - **NOTE:** If multiple interfaces have the same `default` method → must override to resolve ambiguity.

## Lesson 4: Abstract class

### What is an Abstract Class?

- It is declared using the `abstract` keyword.
- It acts as a **partial blueprint** for other classes:
    - Can contain **abstract methods** (methods without implementation).
    - Can contain **concrete methods** (methods with implementation).
- An **abstract class** in Java is a class that **cannot be instantiated** (i.e., you cannot create objects of it directly).
    - You have to inherit (i.e. extend) an abstract class and then you can instantiate the subclass
- Abstract means “incomplete”
    - An abstract class may have abstract methods (methods without a body). Because these methods don’t have implementations, Java cannot create a complete object from the abstract class — it doesn’t know how those methods should behave.
- Constructors in Abstract Classes
    - Abstract classes can have constructors. These constructors are not for creating abstract objects, but for initializing common state when a concrete subclass is instantiated.

### Key Properties of Abstract Classes

- If a class has **at least one abstract method**, it must be declared as `abstract`.
- Abstract classes can contain:
    - **Fields** (variables).
    - **Constructors** (though you can’t instantiate it, constructors can be used by subclasses).
    - **Concrete methods** (fully implemented).
    - **Abstract methods** (must be implemented by subclasses, unless the subclass is also abstract).
- A subclass must:
    - **Implement all abstract methods** of its abstract superclass, OR
    - Be declared `abstract` itself.
- Abstract classes are widely used in frameworks and base class hierarchies to enforce a **common contract + shared functionality**.

## Difference Between Class, Interface, and Abstract Class

| Feature                      | Normal Class | Interface                                 | Abstract Class |
| ---------------------------- | ------------ | ----------------------------------------- | -------------- |
| Can have variables           | ✅           | ❌ (only constants unless `static final`) | ✅             |
| Can have implemented methods | ✅           | ✅ (since Java 8 with `default`)          | ✅             |
| Can have abstract methods    | ❌           | ✅                                        | ✅             |
| Object creation allowed      | ✅           | ❌                                        | ❌             |

## Interface vs Abstract

| Feature              | **Interface**                                                                                                                                                                       | **Abstract Class**                                                                                                                                  |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Method Types**     | - Abstract methods (always `public abstract` by default)<br>- `default` methods (Java 8+)<br>- `static` methods (Java 8+)<br>- `private` methods (Java 9+ for internal helper code) | - Abstract methods<br>- Concrete methods (normal methods with body)<br>- Can also define `static` and `final` methods                               |
| **Variables**        | - All variables are `public static final` (constants).<br>- Must be initialized at declaration.                                                                                     | - Can have instance variables (with different access modifiers: `private`, `protected`, etc.).<br>- Can have mutable state (not necessarily final). |
| **Constructors**     | ❌ Interfaces cannot have constructors.                                                                                                                                             | ✅ Abstract classes can have constructors (called when subclass objects are created).                                                               |
| **Inheritance**      | - A class can **implement multiple interfaces**.<br>- An interface can **extend multiple interfaces**.                                                                              | - A class can **extend only one abstract class** (single inheritance).                                                                              |
| **Access Modifiers** | - Methods are implicitly `public`.<br>- Cannot use `protected` or `private` (except for Java 9+ private helper methods).                                                            | - Methods and variables can have any access modifier (`public`, `protected`, `private`).                                                            |
| **When to Use**      | - To define a **contract/ability** for unrelated classes (e.g., `Comparable`, `Serializable`).<br>- When you want **multiple inheritance of type**.                                 | - To share **common state + behavior** among related classes.<br>- When you want **code reuse** with partial implementation.                        |

## Lesson 5: Encapsulation

### What is Encapsulation?

👉 Definition:
Encapsulation is the practice of **hiding the internal details (data/variables)** of a class and providing controlled access to them using **methods (getters & setters)**.

- **Hide unnecessary details** → Keep variables private.
- **Expose only required details** → Provide public methods to
  interact.

### Why Encapsulation?

- Protects data from unintended modification and provides controlled access.
- Adds **flexibility** and improves **maintainability** (future-proof code).
- Allows **read-only / write-only** access.
- Enables **validation logic** inside setters.

### Key Takeaways

1. Declare **variables as `private`** and provide **public methods** to access them.
2. Provide **public getter and setter methods** to enforce rules (validation, read-only/write-only) and access them.

## Lesson 6: Records

### POJOs

- Stands for Plain Old Java Object.
- Any simple Java object not bound by any special restriction.
- Usually contains:
    - Fields (private variables) mutable or immutable
    - Getters and setters
    - Maybe toString(), equals(), hashCode()

👉 Purpose:
Used to represent a simple object with properties. They are flexible and can contain business logic too

### DTOs

- Stands for Data Transfer Object.
- A specialized type of POJO used to transfer data between layers (e.g., from API to service, or DB to frontend).
- Main difference from a POJO → DTOs are intended to be simple carriers of data only, with no business logic.
- Usually fields immutable (fields are final + NO setters).

👉 Purpose:
Reduce number of method calls, optimize data transfer, and act as a structured data container.

```java
    public class StudentDTO {
        private final String name;
        private final int age;

        public StudentDTO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }
```

### Records

A record in Java is a special kind of class introduced in Java 14 (preview) and made permanent in Java 16.
It is designed to be a concise way of creating immutable data carriers — classes that are mainly used to hold data without needing boilerplate code.

A record is a transparent, immutable data carrier class in Java that automatically provides:

- A constructor
- Accessor methods for its fields
- Implementations of equals(), hashCode(), and toString()

```java
    public record ClassName(type field1, type field2) {}
```

### Key Properties of Records

- Immutable

    - All fields are implicitly private final.
    - Values cannot be changed after creation.

- Concise

    - Removes boilerplate code for simple data classes.

- Transparent

    - Auto-generated methods (toString, equals, hashCode) reflect the fields directly.

- Accessors (not Getters)
    - Access methods use the field name itself, not getFieldName().
    - Example: student.name() instead of student.getName().

### Limitations of Records

- Fields are always private final → immutable only
- Cannot extend other classes
- Cannot declare additional instance variables outside the record header
- Not suitable for:
    - Mutable entities
    - Complex lifecycle management
    - Entities needing inheritance

### When to Use Records

- ✅ Use when:

    - Modeling simple, immutable data (DTOs, configs, API responses).
    - You want cleaner, boilerplate-free code.

- ❌ Avoid when:
    - You need mutable fields.
    - You require inheritance.
    - You need complex logic tied to the object’s lifecycle.

### Comparison

| Feature     | POJO                           | DTO                                            | Record                             |
| ----------- | ------------------------------ | ---------------------------------------------- | ---------------------------------- |
| Definition  | Any simple Java object         | A POJO used specifically for transferring data | Java 16+ feature to simplify DTOs  |
| Fields      | mutable or immutable           | usually immutable                              | always immutable (`final`)         |
| Logic       | may contain business logic     | should contain **no business logic**           | minimal logic (validation allowed) |
| Boilerplate | requires getters/setters, etc. | requires getters                               | auto-generated                     |

## Sealed Classes

### What Are Sealed Classes?

- **Sealed classes** and **sealed interfaces** restrict which classes or interfaces can extend or implement them.
- Purpose: Give the class designer **explicit control** over the class hierarchy.
- Benefits:
    - **Safer and more predictable inheritance**
    - **Better code maintainability**
    - **Works well with pattern matching and switch expressions**

### Syntax Overview

#### Declaring a Sealed Class

```java
    public sealed class Vehicle permits Car, Bike {
        // common code
    }
```

- `sealed` keyword marks the class as restricted.
- `permits` lists allowed subclasses.

#### Subclass Types

- `final` → cannot be subclassed further
- `sealed` → further restrict subclassing with own permits
- `non-sealed` → open for unrestricted subclassing

```java
    public final class Car extends Vehicle {
        // Car cannot be extended
    }
    
    public non-sealed class Bike extends Vehicle {
        // Bike can be freely extended
    }
```

### Uses of Sealed Classes

- Prevent **uncontrolled sub-classing**
- Enforce **security and design constraints**
- Ensure **consistent modeling** (useful for domain-specific hierarchies like shapes, payment types)

### Key Points

- Permitted subclasses **must** be in the same package (or module).
- Subclasses **must** explicitly extend/implement the sealed type.
- Subclasses **must** be `final`, `sealed`, or `non-sealed`.
- Helps in **exhaustive switch**, **pattern matching**, and **domain-specific modeling**.
