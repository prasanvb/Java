# Class & Objects

- Only one public class per` .java` file, and filename must match class name.
- The public class of your source file must have the main method. Only one main method is needed per application.
- Class name starts with an uppercase letter - is convention not a rule.

### Constructors
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