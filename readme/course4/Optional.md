# Java Optional Class

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. Core Characteristics](#2-core-characteristics)
- [3. Creating Optional Instances](#3-creating-optional-instances)
  - [3.1 Basic Creation Methods](#31-basic-creation-methods)
  - [3.2 When to Use Each Method](#32-when-to-use-each-method)
- [4. Core Methods for Handling Presence/Absence](#4-core-methods-for-handling-presenceabsence)
  - [4.1 Checking for Presence](#41-checking-for-presence)
  - [4.2 Retrieving Values Safely](#42-retrieving-values-safely)
  - [4.3 Conditional Execution](#43-conditional-execution)
- [5. Transformation and Filtering Methods](#5-transformation-and-filtering-methods)
  - [5.1 Map Method](#51-map-method)
  - [5.2 FlatMap Method](#52-flatmap-method)
  - [5.3 Filter Method](#53-filter-method)
- [6. Advanced Features](#6-advanced-features)
  - [6.1 Stream Integration (Java 9+)](#61-stream-integration-java-9)
  - [6.2 Primitive Optional Types](#62-primitive-optional-types)
- [7. Best Practices](#7-best-practices)
  - [7.1 When to Use Optional](#71-when-to-use-optional)
  - [7.2 When NOT to Use Optional](#72-when-not-to-use-optional)
  - [7.3 Method Usage Best Practices](#73-method-usage-best-practices)
    - [Avoid Optional.get()](#avoid-optionalget)
  - [7.4 Performance Considerations](#74-performance-considerations)
- [9. Common Pitfalls to Avoid](#9-common-pitfalls-to-avoid)
  - [9.1 Overusing Optional](#91-overusing-optional)
  - [9.2 Using Optional in Fields](#92-using-optional-in-fields)

## 1. Introduction

The `Optional` class was introduced in **Java 8** as a container object designed to solve one of Java's most common runtime errors: the `NullPointerException`. By encapsulating potential null values in a container object, Optional encourages developers to handle null checks more systematically, promoting cleaner and safer code.

### Key Concept
An `Optional<T>` is a wrapper class that can either:
- Contain a **non-null value** of type `T`
- Be **empty** (representing the absence of a value)

## 2. Core Characteristics

- An `Optional` instance either holds a non-null value or represents the absence of a value (an "empty" `Optional`)
- When a method returns an `Optional`, it clearly communicates to the caller that the returned value might not be present
- By forcing explicit handling of potential absence, `Optional` helps prevent common `NullPointerExceptions`
  - Eliminates direct access to potentially `null` references
- Makes code more readable and easier to understand

## 3. Creating Optional Instances

### 3.1 Basic Creation Methods

```java
// Creates an Optional with a non-null value
Optional<String> optional1 = Optional.of("Hello");

// Creates an empty Optional
Optional<String> optional2 = Optional.empty();

// Creates an Optional that may contain null (null-safe)
Optional<String> optional3 = Optional.ofNullable(getValue()); // getValue() might return null
```

### 3.2 When to Use Each Method
- **`Optional.of(value)`**: Use when you are certain that the value is non-null
- **`Optional.empty()`**: Use to explicitly create an empty Optional
- **`Optional.ofNullable(value)`**: Use when the value might be null

## 4. Core Methods for Handling Presence/Absence

### 4.1 Checking for Presence

```java
Optional<String> optional = Optional.of("Hello");

// Check if value is present (Java 8+)
if (optional.isPresent()) {
    System.out.println("Value: " + optional.get());
}

// Check if value is empty (Java 11+)
if (optional.isEmpty()) {
    System.out.println("No value present");
}
```

### 4.2 Retrieving Values Safely

```java
Optional<String> optional = Optional.ofNullable(getName());

// Provide default value if empty
String name = optional.orElse("Unknown");

// Provide default value using supplier (lazy evaluation)
String name2 = optional.orElseGet(() -> getDefaultName());

// Throw exception if empty
String name3 = optional.orElseThrow(() -> new IllegalArgumentException("Name not found"));

// Throw NoSuchElementException if empty (Java 10+)
String name4 = optional.orElseThrow();
```

### 4.3 Conditional Execution

```java
Optional<String> optional = Optional.of("Hello");

// Execute action if value is present
optional.ifPresent(value -> System.out.println("Found: " + value));

// Execute different actions based on presence (Java 9+)
optional.ifPresentOrElse(
    value -> System.out.println("Found: " + value),
    () -> System.out.println("Not found")
);
```

## 5. Transformation and Filtering Methods

### 5.1 Map Method
The Optional.map() method allows you to apply a transformation function to an Optional object only if it contains a value. If the Optional is empty, map() simply returns an empty Optional, skipping the function entirely.

```java
Optional<String> optional = Optional.of("hello");

// Transform the value if present
Optional<String> upperCase = optional.map(String::toUpperCase);
// Result: Optional["HELLO"]

// Chain transformations
Optional<Integer> length = optional
    .map(String::toUpperCase)
    .map(String::length);
// Result: Optional[5]
```

### 5.2 FlatMap Method
flatMap() method is useful if and only Optional has nested Optional objects

```java
public class Person {
    private Optional<Address> address;
    // constructors, getters
}

public class Address {
    private String city;
    // constructors, getters
    
    public Optional<String> getCity() {
        return Optional.ofNullable(city);
    }
}

// Using flatMap to avoid Optional<Optional<String>>
Optional<Person> person = Optional.of(new Person());
Optional<String> city = person
    .flatMap(Person::getAddress)
    .flatMap(Address::getCity);
```

### 5.3 Filter Method
```java
Optional<String> optional = Optional.of("Hello");

// Filter based on condition
Optional<String> filtered = optional.filter(s -> s.length() > 3);
// Result: Optional["Hello"] (condition met)

Optional<String> filtered2 = optional.filter(s -> s.length() > 10);
// Result: Optional.empty() (condition not met)
```

## 6. Advanced Features

### 6.1 Stream Integration (Java 9+)
The newest Java version introduces the stream() method on the Optional

```java
List<Optional<String>> listOfOptionals = Arrays.asList(
    Optional.of("foo"),
    Optional.empty(),
    Optional.of("bar")
);

// Convert Optional to Stream and filter out empty values
List<String> result = listOfOptionals.stream()
    .flatMap(Optional::stream)
    .collect(Collectors.toList());
// Result: ["foo", "bar"]
```

### 6.2 Primitive Optional Types
To avoid boxing and unboxing specialized Optional classes are available like OptionalInt, OptionalLong, and OptionalDouble. Use these classes when applicable for better performance.

```java
OptionalInt optionalInt = OptionalInt.of(42);
OptionalLong optionalLong = OptionalLong.of(123L);
OptionalDouble optionalDouble = OptionalDouble.of(3.14);
```

## 7. Best Practices

### 7.1 When to Use Optional
- **✅ DO**: Optional is primarily designed for method return types
- **✅ DO**: Use for return values that might be absent
- **✅ DO**: Use in functional-style programming with streams

### 7.2 When NOT to Use Optional
- **❌ DON'T**: We should not use in instance fields or method parameters. Using Optional in these places can introduce unnecessary complexity and performance overhead
- **❌ DON'T**: Use for collections (use empty collections instead)
- **❌ DON'T**: Overuse Optional for simple null checks

### 7.3 Method Usage Best Practices

#### Avoid Optional.get()
Never use Optional.get() method unless we're sure that the Optional is not empty

```java
// ❌ BAD - Can throw NoSuchElementException
String value = optional.get();

// ✅ GOOD - Safe alternatives
String value1 = optional.orElse("default");
String value2 = optional.orElseThrow(() -> new CustomException("Value not found"));
```

### 7.4 Performance Considerations
- Optional should be used moderately. If we know that a value will always be present, don't wrap it in Optional
- Use `orElseGet()` instead of `orElse()` when the default value computation is expensive

```java
// ✅ GOOD - Lazy evaluation
String result = optional.orElseGet(() -> expensiveComputation());

// ❌ LESS EFFICIENT - Always executes
String result = optional.orElse(expensiveComputation());
```


## 9. Common Pitfalls to Avoid

### 9.1 Overusing Optional
```java
// ❌ BAD - Unnecessary complexity
public Optional<String> processString(Optional<String> input) {
    return input.map(s -> s.toUpperCase());
}

// ✅ GOOD - Simple and direct
public String processString(String input) {
    return input != null ? input.toUpperCase() : null;
}
```

### 9.2 Using Optional in Fields
```java
// ❌ BAD - Optional in fields
public class Person {
    private Optional<String> name; // Don't do this
}

// ✅ GOOD - Regular field with null checks
public class Person {
    private String name; // This is fine
    
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
```
