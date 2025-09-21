# Java Predicate Complete Study Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Basic Predicate Interface](#basic-predicate-interface)
3. [Instance Methods](#instance-methods)
4. [Static Methods](#static-methods)
5. [Practical Examples](#practical-examples)
6. [Advanced Usage](#advanced-usage)
7. [Best Practices](#best-practices)
8. [Common Pitfalls](#common-pitfalls)
9. [Practice Exercises](#practice-exercises)

## Introduction

A **Predicate** in Java is a functional interface that represents a boolean-valued function of one argument. It's part of the `java.util.function` package introduced in Java 8 and is fundamental to functional programming and stream operations.

### Key Concepts to Remember:
- **Functional Interface**: Has exactly one abstract method (`test`)
- **Lambda Compatible**: Can be implemented using lambda expressions
- **Chainable**: Multiple predicates can be combined using logical operations
- **Reusable**: Same predicate can be used across different contexts

## Basic Predicate Interface

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);                    // Abstract method
    
    // Default methods
    default Predicate<T> and(Predicate<? super T> other);
    default Predicate<T> or(Predicate<? super T> other);
    default Predicate<T> negate();
    
    // Static methods
    static <T> Predicate<T> isEqual(Object targetRef);
    static <T> Predicate<T> not(Predicate<? super T> target); // Java 11+
}
```

### Study Notes:
- **Type Parameter `<T>`**: Represents the type of input to the predicate
- **Return Type**: Always `boolean`
- **Null Safety**: Your implementation should handle null values appropriately

## Instance Methods

### 1. test(T t) - The Core Method

```java
import java.util.function.Predicate;

public class TestMethodExample {
    public static void main(String[] args) {
        // Basic usage
        Predicate<Integer> isPositive = n -> n > 0;
        System.out.println(isPositive.test(5));    // true
        System.out.println(isPositive.test(-3));   // false
        System.out.println(isPositive.test(0));    // false
        
        // String predicate
        Predicate<String> isLongString = s -> s.length() > 5;
        System.out.println(isLongString.test("Hello"));      // false
        System.out.println(isLongString.test("Hello World")); // true
    }
}
```

**Study Notes for test():**
- This is the only abstract method you must implement
- Should return `true` if input matches the condition, `false` otherwise
- Handle edge cases like null inputs in your implementation

### 2. and(Predicate other) - Logical AND

```java
public class AndMethodExample {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;
        
        // Combine with AND
        Predicate<Integer> isPositiveEven = isEven.and(isPositive);
        
        System.out.println(isPositiveEven.test(4));   // true (even AND positive)
        System.out.println(isPositiveEven.test(-4));  // false (even but NOT positive)
        System.out.println(isPositiveEven.test(3));   // false (positive but NOT even)
        System.out.println(isPositiveEven.test(-3));  // false (neither even nor positive)
        
        // Chain multiple ANDs
        Predicate<Integer> lessThan100 = n -> n < 100;
        Predicate<Integer> complex = isEven.and(isPositive).and(lessThan100);
        System.out.println(complex.test(50)); // true
    }
}
```

**Study Notes for and():**
- Returns a new predicate that represents logical AND
- **Short-circuit evaluation**: If first predicate returns false, second is not evaluated
- Can be chained multiple times
- Equivalent to `&&` operator in traditional if statements

### 3. or(Predicate other) - Logical OR

```java
public class OrMethodExample {
    public static void main(String[] args) {
        Predicate<String> startsWithA = s -> s.startsWith("A");
        Predicate<String> endsWithZ = s -> s.endsWith("Z");
        
        // Combine with OR
        Predicate<String> startsWithA_OR_endsWithZ = startsWithA.or(endsWithZ);
        
        System.out.println(startsWithA_OR_endsWithZ.test("Apple"));    // true (starts with A)
        System.out.println(startsWithA_OR_endsWithZ.test("Buzz"));     // true (ends with Z)
        System.out.println(startsWithA_OR_endsWithZ.test("AmaZ"));     // true (both conditions)
        System.out.println(startsWithA_OR_endsWithZ.test("Hello"));    // false (neither condition)
        
        // Complex OR chains
        Predicate<Integer> isOne = n -> n == 1;
        Predicate<Integer> isTwo = n -> n == 2;
        Predicate<Integer> isThree = n -> n == 3;
        
        Predicate<Integer> isOneOrTwoOrThree = isOne.or(isTwo).or(isThree);
        System.out.println(isOneOrTwoOrThree.test(2)); // true
        System.out.println(isOneOrTwoOrThree.test(5)); // false
    }
}
```

**Study Notes for or():**
- Returns a new predicate that represents logical OR
- **Short-circuit evaluation**: If first predicate returns true, second is not evaluated
- Can be chained multiple times
- Equivalent to `||` operator in traditional if statements

### 4. negate() - Logical NOT

```java
public class NegateMethodExample {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isOdd = isEven.negate();
        
        System.out.println(isEven.test(4));  // true
        System.out.println(isOdd.test(4));   // false
        System.out.println(isOdd.test(5));   // true
        
        // Negate complex predicates
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        
        System.out.println(isEmpty.test(""));      // true
        System.out.println(isNotEmpty.test(""));   // false
        System.out.println(isNotEmpty.test("Hi")); // true
        
        // Double negation
        Predicate<Boolean> isTrue = b -> b;
        Predicate<Boolean> doubleNegated = isTrue.negate().negate();
        System.out.println(doubleNegated.test(true)); // true (same as original)
    }
}
```

**Study Notes for negate():**
- Returns a new predicate that represents logical NOT
- Equivalent to `!` operator
- Can be applied to any predicate, including complex ones
- Double negation returns to original logic

## Static Methods

### 1. isEqual(Object targetRef) - Equality Testing

```java
import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;

public class IsEqualExample {
    public static void main(String[] args) {
        // Basic equality
        Predicate<String> equalsHello = Predicate.isEqual("Hello");
        System.out.println(equalsHello.test("Hello"));  // true
        System.out.println(equalsHello.test("World"));  // false
        
        // Null handling
        Predicate<String> equalsNull = Predicate.isEqual(null);
        System.out.println(equalsNull.test(null));    // true
        System.out.println(equalsNull.test("Hello")); // false
        
        // Using with collections
        List<String> words = Arrays.asList("Apple", "Banana", "Apple", "Cherry");
        long appleCount = words.stream()
                              .filter(Predicate.isEqual("Apple"))
                              .count();
        System.out.println("Apple count: " + appleCount); // 2
        
        // Object equality
        Person person1 = new Person("John", 25);
        Person person2 = new Person("John", 25);
        Person person3 = person1;
        
        Predicate<Person> equalsToPerson1 = Predicate.isEqual(person1);
        System.out.println(equalsToPerson1.test(person2)); // false (different objects)
        System.out.println(equalsToPerson1.test(person3)); // true (same reference)
    }
    
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
```

**Study Notes for isEqual():**
- Uses `Objects.equals()` internally for null-safe comparison
- Compares object references, not content (unless `equals()` is overridden)
- Handles null values correctly
- Useful for filtering collections by specific values

### 2. not(Predicate target) - Static Negation (Java 11+)

```java
import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StaticNotExample {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("", "hello", "", "world", "java");
        
        // Traditional way
        List<String> nonEmpty1 = strings.stream()
                                       .filter(s -> !s.isEmpty())
                                       .collect(Collectors.toList());
        
        // Using instance negate()
        List<String> nonEmpty2 = strings.stream()
                                       .filter(String::isEmpty)
                                       .filter(Predicate.not(String::isEmpty))
                                       .collect(Collectors.toList());
        
        // Using static not() - Java 11+
        List<String> nonEmpty3 = strings.stream()
                                       .filter(Predicate.not(String::isEmpty))
                                       .collect(Collectors.toList());
        
        System.out.println(nonEmpty3); // [hello, world, java]
        
        // More readable with method references
        List<Integer> numbers = Arrays.asList(-2, -1, 0, 1, 2);
        
        // Instead of: filter(n -> !(n > 0))
        List<Integer> nonPositive = numbers.stream()
                                          .filter(Predicate.not(n -> n > 0))
                                          .collect(Collectors.toList());
        System.out.println(nonPositive); // [-2, -1, 0]
    }
}
```

**Study Notes for not():**
- Introduced in Java 11
- More readable than instance `negate()` when used with method references
- Particularly useful in stream operations
- Static method, called on the Predicate class

## Practical Examples

### Complete Example with All Methods

```java
import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CompletePredicateExample {
    public static void main(String[] args) {
        // Sample data
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 28, "Engineering", 75000),
            new Employee("Bob", 35, "Marketing", 65000),
            new Employee("Charlie", 42, "Engineering", 95000),
            new Employee("Diana", 29, "HR", 55000),
            new Employee("Eve", 31, "Engineering", 80000),
            new Employee("Frank", 38, "Marketing", 70000)
        );
        
        // Define individual predicates
        Predicate<Employee> isEngineer = emp -> "Engineering".equals(emp.getDepartment());
        Predicate<Employee> isHighPaid = emp -> emp.getSalary() > 70000;
        Predicate<Employee> isYoung = emp -> emp.getAge() < 35;
        Predicate<Employee> isOld = isYoung.negate();
        
        System.out.println("=== All Employees ===");
        employees.forEach(System.out::println);
        
        // Using test() method
        System.out.println("\n=== Testing Individual Employee ===");
        Employee alice = employees.get(0);
        System.out.println("Is Alice an engineer? " + isEngineer.test(alice));
        System.out.println("Is Alice high paid? " + isHighPaid.test(alice));
        
        // Using and() method
        System.out.println("\n=== High-paid Engineers (AND) ===");
        employees.stream()
                .filter(isEngineer.and(isHighPaid))
                .forEach(System.out::println);
        
        // Using or() method  
        System.out.println("\n=== Young OR High-paid (OR) ===");
        employees.stream()
                .filter(isYoung.or(isHighPaid))
                .forEach(System.out::println);
        
        // Using negate() method
        System.out.println("\n=== Non-engineers (NEGATE) ===");
        employees.stream()
                .filter(isEngineer.negate())
                .forEach(System.out::println);
        
        // Using isEqual() static method
        System.out.println("\n=== Employees in Engineering Department (isEqual) ===");
        employees.stream()
                .filter(emp -> Predicate.isEqual("Engineering").test(emp.getDepartment()))
                .forEach(System.out::println);
        
        // Complex combinations
        System.out.println("\n=== Complex: (Engineer AND High-paid) OR (Old AND Marketing) ===");
        Predicate<Employee> isMarketing = emp -> "Marketing".equals(emp.getDepartment());
        Predicate<Employee> complexPredicate = (isEngineer.and(isHighPaid))
                                             .or(isOld.and(isMarketing));
        
        employees.stream()
                .filter(complexPredicate)
                .forEach(System.out::println);
        
        // Method reference predicates
        System.out.println("\n=== Method Reference Examples ===");
        
        // Names starting with 'A'
        List<String> names = employees.stream()
                                    .map(Employee::getName)
                                    .collect(Collectors.toList());
        
        Predicate<String> startsWithA = name -> name.startsWith("A");
        names.stream()
             .filter(startsWithA)
             .forEach(name -> System.out.println("Name starting with A: " + name));
        
        // Salary statistics using predicates
        System.out.println("\n=== Salary Statistics ===");
        long highPaidCount = employees.stream().filter(isHighPaid).count();
        double avgSalaryEngineers = employees.stream()
                                           .filter(isEngineer)
                                           .mapToDouble(Employee::getSalary)
                                           .average()
                                           .orElse(0.0);
        
        System.out.println("High-paid employees: " + highPaidCount);
        System.out.println("Average salary of engineers: $" + avgSalaryEngineers);
    }
}

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;
    
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, dept='%s', salary=%.0f}", 
                           name, age, department, salary);
    }
}
```

### Predicate with Custom Validation

```java
import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;

public class ValidationExample {
    // Email validation predicates
    public static final Predicate<String> hasAtSymbol = email -> email.contains("@");
    public static final Predicate<String> hasDot = email -> email.contains(".");
    public static final Predicate<String> isNotEmpty = email -> email != null && !email.trim().isEmpty();
    public static final Predicate<String> hasValidLength = email -> email.length() >= 5 && email.length() <= 50;
    
    // Combined email validator
    public static final Predicate<String> isValidEmail = isNotEmpty
                                                        .and(hasAtSymbol)
                                                        .and(hasDot)
                                                        .and(hasValidLength);
    
    // Password validation predicates
    public static final Predicate<String> hasMinLength = pwd -> pwd.length() >= 8;
    public static final Predicate<String> hasUppercase = pwd -> pwd.matches(".*[A-Z].*");
    public static final Predicate<String> hasLowercase = pwd -> pwd.matches(".*[a-z].*");
    public static final Predicate<String> hasDigit = pwd -> pwd.matches(".*\\d.*");
    public static final Predicate<String> hasSpecialChar = pwd -> pwd.matches(".*[!@#$%^&*()].*");
    
    // Strong password validator
    public static final Predicate<String> isStrongPassword = hasMinLength
                                                           .and(hasUppercase)
                                                           .and(hasLowercase)
                                                           .and(hasDigit)
                                                           .and(hasSpecialChar);
    
    public static void main(String[] args) {
        // Test emails
        List<String> emails = Arrays.asList(
            "user@example.com",
            "invalid.email",
            "@example.com",
            "user@",
            "",
            "a@b.c",
            "very.long.email.address.that.exceeds.fifty.characters@domain.com"
        );
        
        System.out.println("=== Email Validation ===");
        emails.forEach(email -> 
            System.out.printf("%-50s -> %s%n", email, 
                            isValidEmail.test(email) ? "VALID" : "INVALID"));
        
        // Test passwords
        List<String> passwords = Arrays.asList(
            "Password123!",
            "password",
            "PASSWORD",
            "12345678",
            "Pass1!",
            "MySecureP@ssw0rd"
        );
        
        System.out.println("\n=== Password Validation ===");
        passwords.forEach(pwd -> {
            System.out.printf("%-20s -> %s%n", pwd, 
                            isStrongPassword.test(pwd) ? "STRONG" : "WEAK");
            
            // Detailed validation
            if (!isStrongPassword.test(pwd)) {
                System.out.print("  Missing: ");
                if (!hasMinLength.test(pwd)) System.out.print("Length ");
                if (!hasUppercase.test(pwd)) System.out.print("Uppercase ");
                if (!hasLowercase.test(pwd)) System.out.print("Lowercase ");
                if (!hasDigit.test(pwd)) System.out.print("Digit ");
                if (!hasSpecialChar.test(pwd)) System.out.print("SpecialChar ");
                System.out.println();
            }
        });
    }
}
```

## Advanced Usage

### Predicate Factory Methods

```java
import java.util.function.Predicate;
import java.util.List;
import java.util.Arrays;

public class PredicateFactory {
    
    // Factory method for range checking
    public static Predicate<Integer> inRange(int min, int max) {
        return n -> n >= min && n <= max;
    }
    
    // Factory method for string length checking
    public static Predicate<String> hasLength(int length) {
        return s -> s != null && s.length() == length;
    }
    
    // Factory method for collection membership
    public static <T> Predicate<T> in(List<T> collection) {
        return collection::contains;
    }
    
    // Factory method for regex matching
    public static Predicate<String> matches(String regex) {
        return s -> s != null && s.matches(regex);
    }
    
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 5, 10, 15, 20, 25, 30);
        List<String> words = Arrays.asList("cat", "dog", "elephant", "ant");
        
        // Using factory methods
        System.out.println("Numbers in range 10-20:");
        numbers.stream()
               .filter(inRange(10, 20))
               .forEach(System.out::println);
        
        System.out.println("\nWords with length 3:");
        words.stream()
             .filter(hasLength(3))
             .forEach(System.out::println);
        
        List<String> validAnimals = Arrays.asList("cat", "dog", "bird");
        System.out.println("\nValid animals:");
        words.stream()
             .filter(in(validAnimals))
             .forEach(System.out::println);
        
        System.out.println("\nWords matching pattern (3 letters):");
        words.stream()
             .filter(matches("^[a-z]{3}$"))
             .forEach(System.out::println);
    }
}
```

### Predicate Composition Utility

```java
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.List;

public class PredicateComposer {
    
    @SafeVarargs
    public static <T> Predicate<T> allOf(Predicate<T>... predicates) {
        return Arrays.stream(predicates)
                    .reduce(x -> true, Predicate::and);
    }
    
    @SafeVarargs
    public static <T> Predicate<T> anyOf(Predicate<T>... predicates) {
        return Arrays.stream(predicates)
                    .reduce(x -> false, Predicate::or);
    }
    
    @SafeVarargs
    public static <T> Predicate<T> noneOf(Predicate<T>... predicates) {
        return anyOf(predicates).negate();
    }
    
    public static void main(String[] args) {
        List<String> testStrings = Arrays.asList(
            "Hello123!",
            "hello",
            "HELLO",
            "Hello",
            "123",
            "",
            "Hello World 456!"
        );
        
        // Define individual predicates
        Predicate<String> hasUppercase = s -> s.matches(".*[A-Z].*");
        Predicate<String> hasLowercase = s -> s.matches(".*[a-z].*");
        Predicate<String> hasDigit = s -> s.matches(".*\\d.*");
        Predicate<String> hasSpecialChar = s -> s.matches(".*[!@#$%^&*()].*");
        Predicate<String> isNotEmpty = s -> s != null && !s.isEmpty();
        
        // Compose predicates
        Predicate<String> isComplex = allOf(hasUppercase, hasLowercase, hasDigit, hasSpecialChar);
        Predicate<String> hasAnyCharType = anyOf(hasUppercase, hasLowercase, hasDigit);
        Predicate<String> isSimple = noneOf(hasDigit, hasSpecialChar);
        
        System.out.println("=== Complex Strings (all character types) ===");
        testStrings.stream()
                  .filter(isComplex)
                  .forEach(System.out::println);
        
        System.out.println("\n=== Has Any Character Type ===");
        testStrings.stream()
                  .filter(hasAnyCharType)
                  .forEach(System.out::println);
        
        System.out.println("\n=== Simple Strings (letters only) ===");
        testStrings.stream()
                  .filter(isSimple.and(isNotEmpty))
                  .forEach(System.out::println);
    }
}
```

## Best Practices

### 1. Naming Conventions
```java
// Good: Use descriptive names that read like boolean conditions
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<String> isEmpty = String::isEmpty;
Predicate<Person> isAdult = person -> person.getAge() >= 18;

// Avoid: Generic or unclear names
Predicate<Integer> check = n -> n % 2 == 0;  // What does it check?
Predicate<String> predicate = String::isEmpty;  // Too generic
```

### 2. Null Safety
```java
// Always consider null inputs
Predicate<String> isLongString = s -> s != null && s.length() > 10;

// Or use Optional
Predicate<String> isLongStringSafe = s -> 
    Optional.ofNullable(s)
            .map(String::length)
            .map(len -> len > 10)
            .orElse(false);
```

### 3. Reusability
```java
public class CommonPredicates {
    // Create reusable predicates as constants
    public static final Predicate<String> IS_NOT_EMPTY = 
        s -> s != null && !s.trim().isEmpty();
    
    public static final Predicate<Number> IS_POSITIVE = 
        n -> n.doubleValue() > 0;
    
    // Parameterized predicates as static methods
    public static Predicate<String> startsWith(String prefix) {
        return s -> s != null && s.startsWith(prefix);
    }
}
```

### 4. Performance Considerations
```java
// Order predicates by computational cost (cheapest first)
Predicate<String> isNotEmpty = s -> s != null && !s.isEmpty();
Predicate<String> matchesComplexRegex = s -> s.matches("complex regex pattern");

// Good: Check simple condition first
Predicate<String> combined = isNotEmpty.and(matchesComplexRegex);

// Avoid: Expensive operation first
Predicate<String> inefficient = matchesComplexRegex.and(isNotEmpty);
```

## Common Pitfalls

### 1. Null Pointer Exceptions
```java
// BAD: Will throw NPE if string is null
Predicate<String> badPredicate = s -> s.length() > 5;

// GOOD: Null-safe version
Predicate<String> goodPredicate = s -> s != null && s.length() > 5;
```

### 2. Side Effects in Predicates
```java
List<String> processedItems = new ArrayList<>();

// BAD: Predicate with side effects
Predicate<String> badPredicate = s -> {
    processedItems.add(s);  // Side effect!
    return s.length() > 5;
};

// GOOD: Keep predicates pure
Predicate<String> goodPredicate = s -> s.length() > 5;
// Handle side effects separately in forEach or peek
```

### 3. Complex Logic in Lambda
```java
// BAD: Too complex for a lambda
Predicate<Person> complex = person -> {
    if (person.getAge() > 18) {
        if (person.getDepartment().equals("Engineering")) {
            if (person.getSalary() > 50000) {
                return person.getYearsOfExperience() > 2;
            }
        }
    }
    return false;
};

// GOOD: Break into smaller predicates
Predicate<Person> isAdult = person -> person.getAge() > 18;
Predicate<Person> isEngineer = person -> "Engineering".equals(person.getDepartment());
Predicate<Person> isWellPaid = person -> person.getSalary() > 50000;
Predicate<Person> isExperienced = person -> person.getYearsOfExperience() > 2;

Predicate<Person> qualifiedEngineer = isAdult
    .and(isEngineer)
    .and(isWellPaid)
    .and(isExperienced);
```

## Practice Exercises

### Exercise 1: Basic Predicates
Create predicates for the following conditions and test them:
1. Check if a number is prime
2. Check if a string is a palindrome
3. Check if a list contains more than 5 elements
4. Check if a person's name starts with a vowel

### Exercise 2: Predicate Combinations
Using the Employee class from examples:
1. Find employees who are either in HR with salary > 50000 OR in Engineering with age < 30
2. Find employees who are NOT (young AND low-paid)
3. Create a predicate for "senior employee" (age > 35 AND salary > 80000)

### Exercise 3: Custom Predicate Factory
Create a utility class with these factory methods:
1. `betweenDates(LocalDate start, LocalDate end)` - for date range checking
2. `containsWord(String word)` - for text search
3. `hasSize(int size)` - for collection size checking

### Exercise 4: Validation Framework
Build a validation framework using predicates:
1. Create predicates for validating user registration data
2. Combine them to create comprehensive validation rules
3. Provide detailed error messages for failed validations

### Solutions
```java
// Exercise 1 Solutions
public class Exercise1Solutions {
    // 1. Prime number check
    public static final Predicate<Integer> isPrime = n -> {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    };
    
    // 2. Palindrome check
    public static final Predicate<String> isPalindrome = s -> {
        if (s == null) return false;
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    };
    
    // 3. List size check
    public static final Predicate<List<?>> hasMoreThanFive = list -> 
        list != null && list.size() > 5;
    
    // 4. Name starts with vowel
    public static final Predicate<String> startsWithVowel = name ->
        name != null && "AEIOUaeiou".indexOf(name.charAt(0)) != -1;
}
```

## Summary

###