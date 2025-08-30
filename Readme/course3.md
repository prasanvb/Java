# OOPS - Inheritance, Interface, Abstract Class

## Inheritance

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

-  Inheritance is an Object-Oriented Programming (OOP) concept where one class (child/subclass) derives properties and behaviors (fields and methods) from another class (parent/superclass).
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

## Polymorphism

### What is Polymorphism?

**Polymorphism = "many forms"**
In Java, polymorphism means a single interface (method signature) can represent different implementations, depending on the object type at runtime.
- A superclass reference can point to a subclass object.
- At runtime, the actual object type determines which method implementation executes (dynamic method dispatch).

### Types of Polymorphism in Java

#### Compile-time polymorphism (Static Binding / Method Overloading)

- Decided at compile time. Achieved by method overloading (same method name, different parameters).

```ignorelang
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```

#### Runtime polymorphism (Dynamic Binding / Method Overriding)

- Decided at runtime. Achieved by method overriding (child class redefines parent method).

```ignorelang
Human h = new Musician();  // reference type is Human
h.speak(); // calls Musician's overridden speak() at runtime
```

#### How Runtime Polymorphism Works

- The **reference type** (left side) defines what methods you can call.
- The **object type** (right side, actual object) decides which version of the method executes.

```ignorelang
Human h1 = new Artist();   // Reference type = Human, Object type = Artist
Human h2 = new Musician(); // Reference type = Human, Object type = Musician

h1.speak(); // Executes Human.speak() because Artist did not override speak()

h2.speak(); // Executes Musician.speak() because Musician overrides speak()
h2.playMusicalInstruments(); // ❌ Cannot resolve method 'playMusicalInstruments' in 'Human'        
```

#### Why Polymorphism is Useful

- **Code Reusability:** You can write methods that accept the parent type, but they work with any subclass.
- **Flexibility:** Easy to extend systems by adding new subclasses without changing existing logic.
- **Cleaner Code:** You don't need separate methods for each subclass — you rely on the parent type.

## final and inheritance

- **final class**: cannot be subclassed. A compile-time error occurs if you try to extends a final class.
- **final method**: cannot be overridden in a subclass.
- **final variable**: once initialized, it cannot be reassigned (you can set it in the declaration or constructor, but not modify afterward).

## Interfaces

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

## Difference Between Class, Interface, and Abstract Class

| Feature                      | Normal Class | Interface                                | Abstract Class |
|------------------------------|--------------|------------------------------------------|----------------|
| Can have variables           | ✅            | ❌ (only constants unless `static final`) | ✅              |
| Can have implemented methods | ✅            | ✅ (since Java 8 with `default`)          | ✅              |
| Can have abstract methods    | ❌            | ✅                                        | ✅              |
| Object creation allowed      | ✅            | ❌                                        | ❌              |

## Interface vs Abstract

| Feature              | **Interface**                                                                                                                                                                       | **Abstract Class**                                                                                                                                  |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Method Types**     | - Abstract methods (always `public abstract` by default)<br>- `default` methods (Java 8+)<br>- `static` methods (Java 8+)<br>- `private` methods (Java 9+ for internal helper code) | - Abstract methods<br>- Concrete methods (normal methods with body)<br>- Can also define `static` and `final` methods                               |
| **Variables**        | - All variables are `public static final` (constants).<br>- Must be initialized at declaration.                                                                                     | - Can have instance variables (with different access modifiers: `private`, `protected`, etc.).<br>- Can have mutable state (not necessarily final). |
| **Constructors**     | ❌ Interfaces cannot have constructors.                                                                                                                                              | ✅ Abstract classes can have constructors (called when subclass objects are created).                                                                |
| **Inheritance**      | - A class can **implement multiple interfaces**.<br>- An interface can **extend multiple interfaces**.                                                                              | - A class can **extend only one abstract class** (single inheritance).                                                                              |
| **Access Modifiers** | - Methods are implicitly `public`.<br>- Cannot use `protected` or `private` (except for Java 9+ private helper methods).                                                            | - Methods and variables can have any access modifier (`public`, `protected`, `private`).                                                            |
| **When to Use**      | - To define a **contract/ability** for unrelated classes (e.g., `Comparable`, `Serializable`).<br>- When you want **multiple inheritance of type**.                                 | - To share **common state + behavior** among related classes.<br>- When you want **code reuse** with partial implementation.                        |


## Abstract class

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

