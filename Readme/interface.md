# Java Interfaces

## 1. What is an Interface?

- An **interface** in Java is similar to a class, but instead of defining how things work, it only defines **what must be done**.
- It is a **blueprint for classes**, just like classes are blueprints for objects.
- Interfaces contain:
  - **Abstract methods** (methods without a body).
  - **Constants** (all fields are implicitly `public static final`).
- Purpose: Interfaces define a **contract** for classes to follow. They don’t tell **how**, but **what** needs to be done.

---

## 2. Characteristics of Interfaces

1. **All methods** are `public abstract` by default (unless declared as `default` or `static`).
2. **All variables** are `public static final` (constants).
3. An interface **cannot have constructors** (cannot be instantiated).
4. A class uses the **`implements`** keyword to inherit an interface.
5. An interface can **extend another interface**.
6. A class can **implement multiple interfaces**, supporting multiple inheritance indirectly.

---

## 3. Why Interfaces?

- Interfaces define **capabilities/behaviors** expected from a class.
- Example:
  - `Human` interface defines `think()`.
  - Different classes (`Artist`, `Musician`) implement it in their own ways.

---

## 4. Example: Human Interface

```java
// Human.java (Interface)
interface Human {
    // Constants (implicitly public, static, final)
    String BLOOD_COLOR = "Red";
    int EYES = 2;
    int NOSE = 1;

    // Abstract methods (implicitly public and abstract)
    void work();
    void speak();

    // Default method (Java 8+ feature)
    default void introduce() {
        System.out.println("Hello! I am from the Human interface.");
    }
}
```

---

## 5. Implementing Classes

```java
// Artist.java
class Artist implements Human {
    @Override
    public void work() {
        System.out.println("This is how an Artist works: painting...");
    }

    @Override
    public void speak() {
        System.out.println("This is how an Artist speaks: inspired and thoughtful.");
    }

    // Extra behavior not in interface
    public void preparePainting() {
        System.out.println("Artist is preparing the painting.");
    }
}

// Musician.java
class Musician implements Human {
    @Override
    public void work() {
        System.out.println("This is how a Musician works: composing music...");
    }

    @Override
    public void speak() {
        System.out.println("This is how a Musician speaks: rhythm in words.");
    }

    // Extra behavior not in interface
    public void playInstrument() {
        System.out.println("Musician is playing the guitar.");
    }
}
```

---

## 6. Testing Interfaces

```java
// TestInterface.java
public class TestInterface {
    public static void main(String[] args) {
        // Interface reference to Artist object
        Human h1 = new Artist();
        h1.work();
        h1.speak();
        h1.introduce(); // Default method

        System.out.println("---");

        // Interface reference to Musician object
        Human h2 = new Musician();
        h2.work();
        h2.speak();
        h2.introduce();

        System.out.println("---");

        // Using concrete class reference to call extra methods
        Artist a = new Artist();
        a.preparePainting();

        Musician m = new Musician();
        m.playInstrument();
    }
}
```

---

## 7. ✅ Executed Output

```
This is how an Artist works: painting...
This is how an Artist speaks: inspired and thoughtful.
Hello! I am from the Human interface.
---
This is how a Musician works: composing music...
This is how a Musician speaks: rhythm in words.
Hello! I am from the Human interface.
---
Artist is preparing the painting.
Musician is playing the guitar.
```

---

## 8. Multiple Inheritance via Interfaces

Java does **not allow multiple inheritance** with classes but **does with interfaces**.

```java
interface HumanTemp {
    default void speak() {
        System.out.println("Hello! I am from HumanTemp interface.");
    }
}

class ArtistMulti implements Human, HumanTemp {
    @Override
    public void work() {
        System.out.println("ArtistMulti is working...");
    }

    // Conflict resolution: must override when both interfaces have same default method
    @Override
    public void speak() {
        Human.super.speak(); // Explicitly choosing Human interface method
    }
}
```

**Output when tested:**

```
ArtistMulti is working...
This is how an Artist speaks: inspired and thoughtful.
```

---

## 9. Key Takeaways

- **Interfaces provide contracts** for classes.
- All variables are **constants** (`public static final`).
- All methods are **abstract** (`public abstract`) unless `default` or `static`.
- Cannot create objects of an interface.
- A class can **implement multiple interfaces**.
- **Default methods** (Java 8+) allow interfaces to evolve without breaking existing code.
- If multiple interfaces have the same `default` method → must override to resolve ambiguity.
