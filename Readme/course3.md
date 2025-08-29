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
- Java only supports single inheritance (a class can extend only one direct parent).

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
h2.playKeyboard(); // ❌ Cannot resolve method 'playKeyboard' in 'Human'        
```

#### Why Polymorphism is Useful

- **Code Reusability:** You can write methods that accept the parent type, but they work with any subclass.
- **Flexibility:** Easy to extend systems by adding new subclasses without changing existing logic.
- **Cleaner Code:** You don't need separate methods for each subclass — you rely on the parent type.