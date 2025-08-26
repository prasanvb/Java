# Class & Objects

- Only one public class per` .java` file, and filename must match class name.
- The public class of your source file must have the main method. Only one main method is needed per application.
- Class name starts with an uppercase letter - is convention not a rule.

## Constructors
A constructor in Java is a special method that:
- Has the exact same name as the class
- Does not have any return type (not even void)
- Is used to create objects, e.g., `new course1.Person(...)`
- Can be overloaded: a class can have multiple constructors with different parameter lists, but each constructor must have the same name as the class
- Syntax: To write a constructor, define a method in your class with no return type and the same name as the class.
- To create an object (i.e., a new instance of a class), use the 'new' keyword: new course1.Person("Alex");
    ```ignorelang
    <ClassType> objectName = new <ClassName>
    ```
examples:
- `src/course1/L2_Peps.java`

## Package, imports and Access Modifiers

- A package is a namespace that organizes related classes and interfaces
- Rule #4: package statement must be the first line in the source file.
- Access Modifiers & Package Scope
    - Default access (no modifier/package private): Class, interface, var, etc. are accessible only within the same package.
    - Public access: Class is accessible from any package.
    - Note: Even if a class is public, its fields and methods need to be explicitly public to be accessed outside its package. Otherwise, they remain package-private (default).
- Rule #5: import statements must come after the package statement and before the class declaration.
    - uses `.` notation for imports
    - `import course.myPackageB.*;` - import all classes from a package
    - `import course.myPackageB.course1.Person;` - import a specific class from a package

    
Java has **four access levels**:
1. public: Accessible **from anywhere**: same class, same package, different package. No restrictions.
2. protected: Within the same package. In subclasses (child classes), even if they are in different packages. Useful with inheritance.
3. default: When no modifier is specified. Accessible only within the same package. Not accessible outside the package.
4. private: Accessible only within the same class. Not visible to other classes, even in the same package.

Access modifiers control the **visibility** of:
- Classes
- Class members (variables, methods)
- Constructors
- Interfaces

examples:
- `src/course1/package1`
- `src/course1/package2`

| Modifier      | Same Class | Same Package | Subclass (diff package) | Other Package |
| ------------- | ---------- | ------------ | ----------------------- | ------------- |
| **public**    | ✅         | ✅            | ✅                      | ✅            |
| **protected** | ✅         | ✅            | ✅                      | ❌            |
| **default**   | ✅         | ✅            | ❌                      | ❌            |
| **private**   | ✅         | ❌            | ❌                      | ❌            |

## Java Variables

example: `src/course1/L3_Variables.java`

### Two Categories of Variables
- **Primitive variables** → store simple values (boolean, char, byte, short, int, long, float, double)
- **Reference variables** → store the reference (address) to an object, not the object itself

### Primitive Sizes and Ranges

| Type | Size | Range |
|------|------|-------|
| `byte` | 8 bits | -128 to 127 |
| `short` | 16 bits | -32,768 to 32,767 |
| `int` | 32 bits | -2³¹ to (2³¹ - 1) |
| `long` | 64 bits | -2⁶³ to (2⁶³ - 1) |
| `float` | 32 bits | decimal values (approx. 7 digits precision) |
| `double` | 64 bits | decimal values (approx. 15 digits precision) |
| `char` | 16 bits | single character / Unicode value |
| `boolean` | - | true or false |

### Variable Types by Scope

**Instance variable:**
- Declared in class but outside methods
- Belong to objects, get default values

**Local variable:**
- Declared inside methods
- Must be initialized before use

**Reference variable:**
- Type must match the object type (e.g., `Person p = new Person();`)
- Superclass references can point to subclass objects (e.g., `Animal a = new Cat();`)

### Naming Rules

- Case-sensitive
- Must start with a letter or `_` or `$`
- Cannot use Java keywords
- Use camelCase convention

## Default Values for Variables
- **Local variables** → must be explicitly initialized, otherwise compilation error
- **Instance variables** → automatically get default values depending on type

### Default Values
| Type | Default Value |
|------|---------------|
| `byte`/`short`/`int`/`long` | `0` |
| `float`/`double` | `0.0` |
| `boolean` | `false` |
| `char` | `\u0000` |
| Reference types | `null` |

## Variable Assignment (Literals)

### Boolean Literals
- `true` or `false` (no numbers `1` or `0` like C or javascript)

### Char Literals
- Single character inside `' '`
- Can also use Unicode escape (e.g., `'\u0061'` → `a`)

### String Literals
- Object type, enclosed in `" "`

### Integer Literals
- **Decimal** (base 10)
- **Octal** (base 8, prefix `0`)
- **Hexadecimal** (base 16, prefix `0x`)

### Long Literals
- Add `L` or `l` at end

### Float Literals
- Add `F` or `f` at end

### Double Literals
- Add `D` or `d` (optional)

## Type Casting

- Implicit casting (widening conversion): Smaller type automatically converted to larger type.
    - Example: `int → long`

- Explicit casting (narrowing conversion): Must be done manually using (type).
  - Example: `float → int (fractions lost)`

- Special case with byte: Compile-time constant expressions can fit without error, but runtime expressions require explicit casting.

## Scope of Variables

In Java, the **scope** of a variable defines *where* in the code it can be accessed, and its **lifetime** defines *how long* it exists in memory.

---

### 1. Class (Static) Variables — Longest Lifetime
- Declared with `static` in a class.
- One copy per class (not per object).
- Shared across all instances.
- **Lifetime**: from when the class is loaded into the JVM until it is unloaded.
- **Scope**: accessible anywhere the class is visible (depends on access modifier).

---

### 2. Instance Variables
- Declared in a class, outside methods/constructors, **without `static`**.
- One copy per object.
- **Lifetime**: as long as the object is alive (until garbage collection).
- **Scope**: accessible via a reference to the object (depends on access modifier).

---

### 3. Local Variables
- Declared inside a method/constructor or as a parameter.
- **Lifetime**: while the method is executing (on the call stack).
- **Scope**: only within that method.
- Must be **initialized before use** (no default values like fields).
- Can only use `final` modifier.

---

### 4. Block Variables — Shortest Lifetime
- Declared inside a block `{ ... }` (e.g., `if`, `for`, `while`).
- **Lifetime**: only while the block executes.
- **Scope**: not visible outside the block.
- Inner variables can shadow outer ones with the same name.  


## Modifiers for Variables

### Additional  Modifiers (instance or static)
- final: must be assigned once; cannot be changed later
- static: shared across all instances of a class
- transient: excluded from serialization
- volatile: ensures visibility across threads; prevents caching/reordering

👉 final and volatile cannot be used together.

### Local Variables (inside methods)

- Can use only final (or be effectively final).
- Cannot use: public, private, protected, static, transient, volatile.