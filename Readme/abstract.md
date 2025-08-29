# Abstract Classes in Java

## 1. What is an Abstract Class?

- An **abstract class** in Java is a class that **cannot be instantiated** (i.e., you cannot create objects of it directly).
- It is declared using the `abstract` keyword.
- It acts as a **partial blueprint** for other classes:
    - Can contain **abstract methods** (methods without implementation).
    - Can contain **concrete methods** (methods with implementation).

---

## 2. Key Properties of Abstract Classes

1. If a class has **at least one abstract method**, it must be declared as `abstract`.
2. Abstract classes can contain:
    - **Fields** (variables).
    - **Constructors** (though you can’t instantiate it, constructors can be used by subclasses).
    - **Concrete methods** (fully implemented).
    - **Abstract methods** (must be implemented by subclasses, unless the subclass is also abstract).
3. You **cannot create objects** of abstract classes, but you can create references of their type.
   ```java
   Human h = new Artist(); // ✅ valid (reference is abstract class, object is subclass)
   Human h = new Human();  // ❌ invalid (cannot instantiate abstract class)
   ```
4. A subclass must:
    - **Implement all abstract methods** of its abstract superclass, OR
    - Be declared `abstract` itself.

---

## 3. Difference Between Class, Interface, and Abstract Class

| Feature                      | Normal Class | Interface                                 | Abstract Class |
| ---------------------------- | ------------ | ----------------------------------------- | -------------- |
| Can have variables           | ✅           | ❌ (only constants unless `static final`) | ✅             |
| Can have implemented methods | ✅           | ✅ (since Java 8 with `default`)          | ✅             |
| Can have abstract methods    | ❌           | ✅                                        | ✅             |
| Object creation allowed      | ✅           | ❌                                        | ❌             |

---

## 4. Example Based on Transcript

We’ll use the **Human → Artist / Musician** inheritance structure.

```java
// Human.java
// Abstract class with one abstract method and one concrete method
abstract class Human {
    // Abstract method (no body)
    public abstract void work();

    // Concrete method (with body)
    public void speak() {
        System.out.println("Speaking from Human class...");
    }
}

// Artist.java
// Artist must implement the abstract method work()
class Artist extends Human {
    @Override
    public void work() {
        System.out.println("This is from Artist.");
    }
}

// Musician.java
// Musician must also implement work()
class Musician extends Human {
    @Override
    public void work() {
        System.out.println("This is from Musician.");
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        // Create objects of subclasses
        Artist artist = new Artist();
        Musician musician = new Musician();

        // Call methods on Artist object
        System.out.println("Artist Object Calls:");
        artist.work();   // Calls overridden method in Artist
        artist.speak();  // Inherited from Human

        // Call methods on Musician object
        System.out.println("\nMusician Object Calls:");
        musician.work();   // Calls overridden method in Musician
        musician.speak();  // Inherited from Human

        // Abstract class reference pointing to subclass object
        Human h = new Artist();
        System.out.println("\nAbstract Reference Calls:");
        h.work();
        h.speak();
    }
}
```

---

## 5. ✅ Executed Results

When running `Main.java`, the output will be:

```
Artist Object Calls:
This is from Artist.
Speaking from Human class...

Musician Object Calls:
This is from Musician.
Speaking from Human class...

Abstract Reference Calls:
This is from Artist.
Speaking from Human class...
```

---

# 🔑 Key Takeaways

- Abstract classes provide a mix of **interface-like behavior** (unimplemented methods) and **class-like behavior** (implemented methods).
- You **cannot instantiate** an abstract class directly.
- Subclasses must either **implement all abstract methods** or remain abstract themselves.
- Abstract classes are widely used in frameworks and base class hierarchies to enforce a **common contract + shared functionality**.
