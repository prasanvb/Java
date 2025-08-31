# Java - Strings, Exceptions

## Lesson 1: String in Java

- **String**: Immutable, stored in String Pool (when literals). Use `.equals()` for value comparison.
- **StringBuffer**: Mutable, thread-safe, slower.
- **StringBuilder**: Mutable, non-thread-safe, faster.
- **Text Blocks**: Introduced in Java 13+, simplify multi-line string handling.

### 1. String Class

- `String` is a **final** class in `java.lang` package.
- Strings are **immutable**: once created, they cannot be modified.
- String objects can be created in **two ways**:

    1. **Using `new` keyword** → Always creates a new object in heap.
    2. **Using String literals** → Stored in the **String Pool**. If the value already exists, the same reference is reused.

- `==` checks **reference equality** (same object in heap memory).
- `.equals()` checks **content equality**.
- Common String methods:

    - `charAt(index)` → returns character at index.
    - `concat(String)` / `+` → concatenates two strings.
    - `equals()` & `equalsIgnoreCase()` → compares values.
    - `length()` → returns string length.
    - `replace(oldChar, newChar)` → replaces characters.
    - `substring(beginIndex, endIndex)` → extracts part of a string.
    - `toLowerCase()` / `toUpperCase()` → converts case.
    - `trim()` → removes leading and trailing spaces.

### 2. StringBuffer and StringBuilder

- Both are in `java.lang` package and is **Mutable** (unlike String Class).
- **StringBuffer** → Thread-safe (synchronized), but slower.
- **StringBuilder** → Not thread-safe, but faster.
- Common methods:

    - `append(String)` → adds to the end.
    - `charAt(index)` → returns char.
    - `delete(start, end)` → deletes part of string.
    - `insert(index, str)` → inserts string.
    - `toString()` → converts back to String.

### 3. Multi-line Strings (Text Blocks)

- Introduced as preview in Java 13, finalized in Java 15, stable in Java 17.
- Defined using **triple quotes (`"""`)**.
- Benefits:

    - No need for `+` or `\n`.
    - Maintains formatting & indentation.
    - Easier for JSON, SQL, XML, HTML.

- Rules:

    - Opening `"""` must be on its own line.
    - Content starts next line.
    - Ending `"""` must be on its own line.

- Supports `.formatted()` for dynamic values.

### 🔑 Quick Comparison Table

| Feature            | String                         | StringBuffer                                             | StringBuilder                                        |
|--------------------|--------------------------------|----------------------------------------------------------|------------------------------------------------------|
| Mutability         | Immutable                      | Mutable                                                  | Mutable                                              |
| Thread Safety      | N/A                            | Thread-safe                                              | Not thread-safe                                      |
| Performance        | Slower (due to immutability)   | Slower (synchronization overhead)                        | Faster (no synchronization)                          |
| Package            | java.lang                      | java.lang                                                | java.lang                                            |
| Introduced in Java | 1.0                            | 1.0                                                      | 1.5                                                  |
| Common Use Case    | Fixed text / less modification | Multi-threaded applications requiring safe modifications | Single-threaded apps with heavy string modifications |

## Lesson 2: Exception Handling

### Introduction to Exceptions

- Errors can occur at **compile-time** or **runtime**.
- **Compile-time errors** are caught by the compiler (e.g., missing methods, wrong syntax).
- **Runtime errors (Exceptions)** occur during program execution.
- An **exception** is an unwanted event that disrupts normal program flow.
- Java provides **exception handling** to gracefully manage these runtime errors.

### Types of Exceptions

1. **Checked Exceptions** (compile-time) → must be handled explicitly (e.g., `IOException`).
2. **Unchecked Exceptions** (runtime) → occur due to logical/programming errors (e.g., `NullPointerException`, `ArrayIndexOutOfBoundsException`).

### Handling Exceptions with try-catch

- Risky code should be wrapped in a **try-catch block**.
- `try { ... }` → code that might throw an exception.
- `catch(ExceptionType e) { ... }` → handles the exception.
- Multiple `catch` blocks can be used for different exception types.

### Finally Block

- **`finally` block** executes whether an exception occurs or not.
- Used for cleanup tasks (closing files, releasing resources).
- Execution order:
    - Try → Catch (if exception) → Finally → Continue program.

### Exception Hierarchy & Multiple Catch

- Exceptions follow a **class hierarchy**:

    - `Object` → `Throwable` → `Exception` / `Error`
    - `RuntimeException` → subclasses like `NumberFormatException`, `ArrayIndexOutOfBoundsException`

- Catch blocks must follow **order**:

    - **Specific to general**.
    - Otherwise, compiler error: “exception has already been caught.”

### Exception Propagation

- Exceptions propagate through the **call stack** until caught.
- If not handled in the current method, it is passed to the **caller method**.

### Throw vs Throws

- **`throw`** → used to explicitly throw an exception.
- **`throws`** → declares exceptions a method can throw, shifting responsibility to the caller.

### Custom Exceptions

- Java allows creating **user-defined exceptions**.
- Steps:

    1. Extend the `Exception` class.
    2. Provide a constructor for custom messages.
    3. Throw and catch it like built-in exceptions.
