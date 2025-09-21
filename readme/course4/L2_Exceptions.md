# Java - Strings, Exceptions

## Table of Contents
- [Lesson 2: Exception Handling](#lesson-2-exception-handling)
    - [Introduction to Exceptions](#introduction-to-exceptions)
    - [Types of Exceptions](#types-of-exceptions)
    - [Exception Hierarchy](#-exception-hierarchy)
    - [Handling Exceptions with try-catch](#handling-exceptions-with-try-catch)
    - [Finally Block](#finally-block)
    - [Exception Hierarchy & Multiple Catch](#exception-hierarchy--multiple-catch)
    - [Exception Propagation](#exception-propagation)
    - [Throw vs Throws](#throw-vs-throws)
    - [Custom Exceptions](#custom-exceptions)

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

### 🖼️ [Exception Hierarchy](readme/diagrams/ExceptionHierarchy.png)

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
