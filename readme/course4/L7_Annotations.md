# Annotations

**Table of Contents**

1. [Introduction to Annotations](#introduction-to-annotations)
2. [Built-in Annotations](#built-in-annotations)
3. [Creating Custom Annotations](#creating-custom-annotations)
4. [Meta-Annotations](#meta-annotations)
5. [Annotation Processing at Runtime](#annotation-processing-at-runtime)
6. [Practical Examples](#practical-examples)

## Introduction to Annotations

Java Annotations are metadata that provide information about the program but are not part of the program itself. They have no direct effect on the operation of the code they annotate, but they can be processed by the compiler, development tools, or at runtime using reflection.

### Syntax

```java
@AnnotationName(element1=value1, element2=value2)
```

## Built-in Annotations

### 1. @Override

```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }

    // This will cause a compilation error if uncommented
    // @Override
    // public void makeNoise() { // Method doesn't exist in parent
    //     System.out.println("Dog makes noise");
    // }
}

public class OverrideExample {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();
    }
}
```

**Output:**

```
Dog barks
```

### 2. @Deprecated

```java
class Calculator {
    @Deprecated
    public int add(int a, int b) {
        System.out.println("Using deprecated add method");
        return a + b;
    }

    public int addNumbers(int a, int b) {
        System.out.println("Using new addNumbers method");
        return a + b;
    }
}

public class DeprecatedExample {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // This will generate a warning during compilation
        int result1 = calc.add(5, 3);
        System.out.println("Deprecated result: " + result1);

        int result2 = calc.addNumbers(5, 3);
        System.out.println("New method result: " + result2);
    }
}
```

**Output:**

```
Using deprecated add method
Deprecated result: 8
Using new addNumbers method
New method result: 8
```

### 3. @SuppressWarnings

```java
import java.util.ArrayList;
import java.util.List;

public class SuppressWarningsExample {
    @SuppressWarnings("unchecked")
    public static void uncheckedExample() {
        List rawList = new ArrayList();
        rawList.add("Hello");
        rawList.add(42);

        List<String> stringList = (List<String>) rawList;
        System.out.println("Raw list contents: " + stringList);
    }

    @SuppressWarnings({"unused", "deprecation"})
    public static void multipleWarningsExample() {
        String unusedVariable = "This variable is never used";

        Calculator calc = new Calculator();
        calc.add(1, 2); // Deprecated method call
    }

    public static void main(String[] args) {
        uncheckedExample();
        multipleWarningsExample();
    }
}
```

**Output:**

```
Raw list contents: [Hello, 42]
```

## Creating Custom Annotations

### Simple Custom Annotation

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Define the annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Author {
    String name();
    String date() default "Unknown";
    int version() default 1;
}

// Use the annotation
class MyClass {
    @Author(name = "John Doe", date = "2024-01-15", version = 2)
    public void method1() {
        System.out.println("Method 1 executed");
    }

    @Author(name = "Jane Smith")
    public void method2() {
        System.out.println("Method 2 executed");
    }
}

// Process the annotation
import java.lang.reflect.Method;

public class CustomAnnotationExample {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        Class<?> clazz = obj.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Author.class)) {
                Author author = method.getAnnotation(Author.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Author: " + author.name());
                System.out.println("Date: " + author.date());
                System.out.println("Version: " + author.version());
                System.out.println("---");
            }
        }
    }
}
```

**Output:**

```
Method: method1
Author: John Doe
Date: 2024-01-15
Version: 2
---
Method: method2
Author: Jane Smith
Date: Unknown
Version: 1
---
```

### Validation Annotation Example

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// Define validation annotations
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotNull {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Min {
    int value();
}

// User class with validation annotations
class User {
    @NotNull
    private String name;

    @NotNull
    @MaxLength(50)
    private String email;

    @Min(18)
    private int age;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}

// Validator class
class Validator {
    public static boolean validate(Object obj) {
        boolean isValid = true;
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);

                // Check @NotNull
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null) {
                        System.out.println("Validation failed: " + field.getName() + " cannot be null");
                        isValid = false;
                    }
                }

                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class) && value != null) {
                    MaxLength maxLength = field.getAnnotation(MaxLength.class);
                    if (value.toString().length() > maxLength.value()) {
                        System.out.println("Validation failed: " + field.getName() +
                                         " exceeds maximum length of " + maxLength.value());
                        isValid = false;
                    }
                }

                // Check @Min
                if (field.isAnnotationPresent(Min.class) && value != null) {
                    Min min = field.getAnnotation(Min.class);
                    if (value instanceof Integer && (Integer) value < min.value()) {
                        System.out.println("Validation failed: " + field.getName() +
                                         " must be at least " + min.value());
                        isValid = false;
                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
}

public class ValidationExample {
    public static void main(String[] args) {
        // Valid user
        User validUser = new User("John Doe", "john@example.com", 25);
        System.out.println("Validating valid user:");
        boolean result1 = Validator.validate(validUser);
        System.out.println("Is valid: " + result1);
        System.out.println();

        // Invalid user
        User invalidUser = new User(null, "this-email-is-way-too-long-to-be-valid@example.com", 15);
        System.out.println("Validating invalid user:");
        boolean result2 = Validator.validate(invalidUser);
        System.out.println("Is valid: " + result2);
    }
}
```

**Output:**

```
Validating valid user:
Is valid: true

Validating invalid user:
Validation failed: name cannot be null
Validation failed: email exceeds maximum length of 50
Validation failed: age must be at least 18
Is valid: false
```

## Meta-Annotations

Meta-annotations are annotations that apply to other annotations.

### @Retention

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@interface SourceAnnotation {
    String value();
}

@Retention(RetentionPolicy.CLASS)
@interface ClassAnnotation {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeAnnotation {
    String value();
}

public class RetentionExample {
    @SourceAnnotation("This annotation is discarded by compiler")
    @ClassAnnotation("This annotation is in bytecode but not at runtime")
    @RuntimeAnnotation("This annotation is available at runtime")
    public void testMethod() {
        System.out.println("Method executed");
    }

    public static void main(String[] args) throws Exception {
        RetentionExample obj = new RetentionExample();
        obj.testMethod();

        // Only RuntimeAnnotation will be visible
        java.lang.reflect.Method method = obj.getClass().getMethod("testMethod");
        java.lang.annotation.Annotation[] annotations = method.getAnnotations();

        System.out.println("Annotations visible at runtime:");
        for (java.lang.annotation.Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
```

**Output:**

```
Method executed
Annotations visible at runtime:
@RuntimeAnnotation(value="This annotation is available at runtime")
```

### @Target

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@interface MethodOnly {
}

@Target({ElementType.FIELD, ElementType.METHOD})
@interface FieldAndMethod {
}

@Target(ElementType.TYPE)
@interface TypeOnly {
}

@TypeOnly
public class TargetExample {
    @FieldAndMethod
    private String field;

    @MethodOnly
    @FieldAndMethod
    public void method() {
        System.out.println("Method with multiple annotations");
    }

    // This would cause compilation error if uncommented:
    // @MethodOnly
    // private String invalidField;

    public static void main(String[] args) {
        TargetExample obj = new TargetExample();
        obj.method();
        System.out.println("Target annotations demonstration completed");
    }
}
```

**Output:**

```
Method with multiple annotations
Target annotations demonstration completed
```

## Annotation Processing at Runtime

### Complete Reflection Example

```java
import java.lang.annotation.*;
import java.lang.reflect.*;

// Define multiple annotations
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Entity {
    String tableName() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name() default "";
    boolean primaryKey() default false;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeInsert {
}

// Annotated class
@Entity(tableName = "users")
class Person {
    @Column(name = "id", primaryKey = true)
    private Long id;

    @Column(name = "full_name")
    private String name;

    @Column
    private String email;

    public Person(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @BeforeInsert
    public void validate() {
        System.out.println("Validating person before insert: " + name);
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Annotation processor
class AnnotationProcessor {
    public static void processEntity(Object obj) {
        Class<?> clazz = obj.getClass();

        // Process class-level annotations
        if (clazz.isAnnotationPresent(Entity.class)) {
            Entity entity = clazz.getAnnotation(Entity.class);
            String tableName = entity.tableName().isEmpty() ?
                              clazz.getSimpleName().toLowerCase() : entity.tableName();
            System.out.println("Entity: " + clazz.getSimpleName());
            System.out.println("Table: " + tableName);
            System.out.println();
        }

        // Process field annotations
        System.out.println("Columns:");
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name().isEmpty() ? field.getName() : column.name();
                System.out.println("  - " + columnName +
                                 (column.primaryKey() ? " (PRIMARY KEY)" : "") +
                                 " -> " + field.getType().getSimpleName());
            }
        }
        System.out.println();

        // Process method annotations
        System.out.println("Lifecycle methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeInsert.class)) {
                System.out.println("  - Before Insert: " + method.getName());
                try {
                    method.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class ReflectionExample {
    public static void main(String[] args) {
        Person person = new Person(1L, "Alice Johnson", "alice@example.com");
        AnnotationProcessor.processEntity(person);
    }
}
```

**Output:**

```
Entity: Person
Table: users

Columns:
  - id (PRIMARY KEY) -> Long
  - full_name -> String
  - email -> String

Lifecycle methods:
  - Before Insert: validate
Validating person before insert: Alice Johnson
```

## Practical Examples

### JSON Serialization Annotation

```java
import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonProperty {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonIgnore {
}

class Product {
    @JsonProperty("product_id")
    private Long id;

    @JsonProperty("product_name")
    private String name;

    private double price; // Will use field name

    @JsonIgnore
    private String internalCode;

    public Product(Long id, String name, double price, String internalCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.internalCode = internalCode;
    }
}

class SimpleJsonSerializer {
    public static String toJson(Object obj) {
        Class<?> clazz = obj.getClass();
        StringBuilder json = new StringBuilder("{");
        boolean first = true;

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            // Skip ignored fields
            if (field.isAnnotationPresent(JsonIgnore.class)) {
                continue;
            }

            try {
                Object value = field.get(obj);
                if (value != null) {
                    if (!first) {
                        json.append(", ");
                    }

                    // Get property name
                    String propertyName = field.getName();
                    if (field.isAnnotationPresent(JsonProperty.class)) {
                        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                        if (!jsonProperty.value().isEmpty()) {
                            propertyName = jsonProperty.value();
                        }
                    }

                    json.append("\"").append(propertyName).append("\": ");
                    if (value instanceof String) {
                        json.append("\"").append(value).append("\"");
                    } else {
                        json.append(value);
                    }
                    first = false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        json.append("}");
        return json.toString();
    }
}

public class JsonSerializationExample {
    public static void main(String[] args) {
        Product product = new Product(123L, "Laptop", 999.99, "INTERNAL_LP_001");
        String json = SimpleJsonSerializer.toJson(product);
        System.out.println("JSON representation:");
        System.out.println(json);
    }
}
```

**Output:**

```
JSON representation:
{"product_id": 123, "product_name": "Laptop", "price": 999.99}
```

## Best Practices

1. **Use appropriate retention policy**: Choose SOURCE for compile-time checks, CLASS for bytecode processing, and RUNTIME for reflection-based processing.

2. **Specify target elements**: Always use @Target to clearly define where your annotation can be applied.

3. **Provide meaningful default values**: Make annotations easier to use by providing sensible defaults.

4. **Document your annotations**: Use JavaDoc to explain the purpose and usage of custom annotations.

5. **Keep annotations simple**: Avoid complex logic in annotation processing; keep the annotation itself lightweight.

6. **Consider performance**: Runtime annotation processing using reflection can be expensive; cache results when possible.

## Conclusion

Java Annotations are a powerful feature that enables metadata-driven programming, compile-time checks, and runtime processing. They're extensively used in frameworks like Spring, Hibernate, and JAX-RS to reduce boilerplate code and provide declarative configuration. Understanding how to create and process annotations is essential for modern Java development.
