# Java

## Exception Handling

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

#### Example File: `ExceptionSample.java`

```java
package exceptionpg;

public class ExceptionSample {
    public static void main(String[] args) {
        String[] fruits = {"Banana", "Watermelon", "Apple", "Orange"};

        try {
            // Simulating invalid user input
            int userInput = Integer.parseInt("ABC"); // will cause NumberFormatException
            System.out.println("User picked: " + fruits[userInput - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numbers only.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please choose a number between 1 and 4.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
```

### Exception Hierarchy & Multiple Catch

- Exceptions follow a **class hierarchy**:

  - `Object` → `Throwable` → `Exception` / `Error`
  - `RuntimeException` → subclasses like `NumberFormatException`, `ArrayIndexOutOfBoundsException`

- Catch blocks must follow **order**:

  - **Specific to general**.
  - Otherwise, compiler error: “exception has already been caught.”

### Finally Block

- **`finally` block** executes whether an exception occurs or not.
- Used for cleanup tasks (closing files, releasing resources).
- Execution order:

  - Try → Catch (if exception) → Finally → Continue program.

#### Example File: `FinallyDemo.java`

```java
public class FinallyDemo {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // causes ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
        } finally {
            System.out.println("Finally block always executes.");
        }
    }
}
```

### Exception Propagation

- Exceptions propagate through the **call stack** until caught.
- If not handled in the current method, it is passed to the **caller method**.

#### Example File: `PropagationDemo.java`

```java
public class PropagationDemo {
    static void methodC() {
        int x = 5 / 0; // ArithmeticException
    }

    static void methodB() {
        methodC();
    }

    static void methodA() {
        try {
            methodB();
        } catch (ArithmeticException e) {
            System.out.println("Exception handled in methodA");
        }
    }

    public static void main(String[] args) {
        methodA();
    }
}
```

### Throw vs Throws

- **`throw`** → used to explicitly throw an exception.
- **`throws`** → declares exceptions a method can throw, shifting responsibility to the caller.

#### Example File: `ThrowThrowsDemo.java`

```java
public class ThrowThrowsDemo {

    static void validateAge(int age) throws IllegalArgumentException {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or above.");
        }
        System.out.println("Valid age!");
    }

    public static void main(String[] args) {
        try {
            validateAge(15); // will throw exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### Custom Exceptions

- Java allows creating **user-defined exceptions**.
- Steps:

  1. Extend the `Exception` class.
  2. Provide a constructor for custom messages.
  3. Throw and catch it like built-in exceptions.

#### Example File: `CustomExceptionDemo.java`

```java
// Custom exception class
class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class CustomExceptionDemo {
    static void checkValue(int num) throws MyException {
        if (num == 0) {
            throw new MyException("Zero is not allowed!");
        }
        System.out.println("Valid number: " + num);
    }

    public static void main(String[] args) {
        try {
            checkValue(0);
        } catch (MyException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        }
    }
}
```
