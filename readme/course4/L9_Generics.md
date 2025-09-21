# Generics

**Table of Contents**

1. [Introduction to Generics](#introduction-to-generics)
2. [Generic Classes](#generic-classes)
3. [Generic Methods](#generic-methods)
4. [Bounded Type Parameters](#bounded-type-parameters)
5. [Wildcards](#wildcards)
6. [Type Erasure](#type-erasure)
7. [Best Practices](#best-practices)

## Introduction to Generics

Generics were introduced in Java 5 to provide compile-time type safety and eliminate the need for casting. They allow you to write code that can work with different types while maintaining type safety.

### Benefits of Generics:

- **Type Safety**: Compile-time checking prevents `ClassCastException`
- **Elimination of Casting**: No need for explicit type casting
- **Code Reusability**: Write generic algorithms that work with different types
- **Performance**: No boxing/unboxing overhead for primitive wrapper types

## Generic Classes

Generic classes are classes that can work with different types specified at instantiation time.

### Basic Generic Class Example

```java
// Generic class definition
public class Box<T> {
    private T content;

    public void set(T content) {
        this.content = content;
    }

    public T get() {
        return content;
    }

    @Override
    public String toString() {
        return "Box contains: " + content;
    }
}

// Usage example
public class GenericClassDemo {
    public static void main(String[] args) {
        // Integer Box
        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        System.out.println(intBox);
        System.out.println("Integer value: " + intBox.get());

        // String Box
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Generics!");
        System.out.println(stringBox);
        System.out.println("String value: " + stringBox.get());

        // Custom object Box
        Box<Person> personBox = new Box<>();
        personBox.set(new Person("Alice", 25));
        System.out.println(personBox);
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " (age: " + age + ")";
    }
}
```

**Output:**

```
Box contains: 42
Integer value: 42
Box contains: Hello Generics!
String value: Hello Generics!
Box contains: Alice (age: 25)
```

### Multiple Type Parameters

```java
public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(U second) { this.second = second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

// Usage
public class PairDemo {
    public static void main(String[] args) {
        Pair<String, Integer> nameAge = new Pair<>("John", 30);
        System.out.println("Name-Age pair: " + nameAge);

        Pair<Double, String> scoreGrade = new Pair<>(95.5, "A");
        System.out.println("Score-Grade pair: " + scoreGrade);

        // Nested generics
        Pair<String, Pair<Integer, String>> complex =
            new Pair<>("Employee", new Pair<>(101, "Developer"));
        System.out.println("Complex pair: " + complex);
    }
}
```

**Output:**

```
Name-Age pair: (John, 30)
Score-Grade pair: (95.5, A)
Complex pair: (Employee, (101, Developer))
```

## Generic Methods

Generic methods can be defined within both generic and non-generic classes. They have their own type parameters.

### Basic Generic Methods

```java
public class GenericMethods {

    // Generic method with single type parameter
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Generic method with return type
    public static <T> T getMiddleElement(T[] array) {
        return array[array.length / 2];
    }

    // Generic method with multiple type parameters
    public static <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }

    // Generic method that returns generic type
    public static <T> Box<T> createBox(T content) {
        Box<T> box = new Box<>();
        box.set(content);
        return box;
    }

    public static void main(String[] args) {
        // Swap integers
        Integer[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Before swap: " + Arrays.toString(numbers));
        swap(numbers, 1, 3);
        System.out.println("After swap: " + Arrays.toString(numbers));

        // Swap strings
        String[] words = {"apple", "banana", "cherry"};
        System.out.println("Before swap: " + Arrays.toString(words));
        swap(words, 0, 2);
        System.out.println("After swap: " + Arrays.toString(words));

        // Get middle element
        String middle = getMiddleElement(words);
        System.out.println("Middle element: " + middle);

        // Print pairs
        printPair("Hello", 42);
        printPair(3.14, true);

        // Create boxes
        Box<String> stringBox = createBox("Generic Method Created");
        System.out.println(stringBox);
    }
}
```

**Output:**

```
Before swap: [1, 2, 3, 4, 5]
After swap: [1, 4, 3, 2, 5]
Before swap: [apple, banana, cherry]
After swap: [cherry, banana, apple]
Middle element: banana
First: Hello, Second: 42
First: 3.14, Second: true
Box contains: Generic Method Created
```

### Generic Method with Constraints

```java
public class ConstrainedGenericMethods {

    // Method that works with Comparable types
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array.length == 0) return null;

        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] numbers = {5, 2, 8, 1, 9, 3};
        Integer maxNumber = findMax(numbers);
        System.out.println("Max number: " + maxNumber);

        String[] words = {"zebra", "apple", "banana", "cherry"};
        String maxWord = findMax(words);
        System.out.println("Max word (alphabetically): " + maxWord);
    }
}
```

**Output:**

```
Max number: 9
Max word (alphabetically): zebra
```

## Bounded Type Parameters

Bounded type parameters restrict the types that can be used as type arguments.

### Upper Bounds (extends)

```java
// Upper bound example
class NumberContainer<T extends Number> {
    private T value;

    public NumberContainer(T value) {
        this.value = value;
    }

    public double getDoubleValue() {
        return value.doubleValue(); // Can call Number methods
    }

    public T getValue() {
        return value;
    }
}

// Multiple bounds example
interface Drawable {
    void draw();
}

class Shape implements Drawable {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println("Drawing " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}

class Circle extends Shape {
    public Circle() {
        super("Circle");
    }
}

class DrawableContainer<T extends Shape & Drawable> {
    private T item;

    public DrawableContainer(T item) {
        this.item = item;
    }

    public void displayAndDraw() {
        System.out.println("Container holds: " + item);
        item.draw(); // Can call both Shape and Drawable methods
    }
}

public class BoundedTypeDemo {
    public static void main(String[] args) {
        // Number container examples
        NumberContainer<Integer> intContainer = new NumberContainer<>(42);
        System.out.println("Integer value: " + intContainer.getValue());
        System.out.println("As double: " + intContainer.getDoubleValue());

        NumberContainer<Double> doubleContainer = new NumberContainer<>(3.14);
        System.out.println("Double value: " + doubleContainer.getValue());
        System.out.println("As double: " + doubleContainer.getDoubleValue());

        // This would cause compilation error:
        // NumberContainer<String> stringContainer = new NumberContainer<>("text");

        // Drawable container example
        DrawableContainer<Circle> circleContainer = new DrawableContainer<>(new Circle());
        circleContainer.displayAndDraw();
    }
}
```

**Output:**

```
Integer value: 42
As double: 42.0
Double value: 3.14
As double: 3.14
Container holds: Circle
Drawing Circle
```

### Method with Bounded Type Parameters

```java
public class BoundedMethods {

    // Method that works only with Number subtypes
    public static <T extends Number> double calculateAverage(T[] numbers) {
        double sum = 0.0;
        for (T number : numbers) {
            sum += number.doubleValue();
        }
        return sum / numbers.length;
    }

    // Method with multiple bounds
    public static <T extends Comparable<T> & Cloneable> T findMaxAndClone(T[] array)
            throws CloneNotSupportedException {
        if (array.length == 0) return null;

        T max = array[0];
        for (T item : array) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        // This cast is necessary due to type erasure
        return (T) max.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        // Calculate average
        Integer[] integers = {1, 2, 3, 4, 5};
        double avg1 = calculateAverage(integers);
        System.out.println("Average of integers: " + avg1);

        Double[] doubles = {1.5, 2.5, 3.5, 4.5};
        double avg2 = calculateAverage(doubles);
        System.out.println("Average of doubles: " + avg2);

        // Find max and clone
        String[] words = {"apple", "zebra", "banana"};
        String maxWord = findMaxAndClone(words);
        System.out.println("Cloned max word: " + maxWord);
    }
}
```

**Output:**

```
Average of integers: 3.0
Average of doubles: 3.0
Cloned max word: zebra
```

## Wildcards

Wildcards provide flexibility when working with generic types, especially in method parameters.

### Unbounded Wildcards (?)

```java
import java.util.*;

public class WildcardDemo {

    // Method that accepts list of any type
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }

    // Method that returns size of any list
    public static int getListSize(List<?> list) {
        return list.size();
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> stringList = Arrays.asList("a", "b", "c");
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("Integer list:");
        printList(intList);
        System.out.println("Size: " + getListSize(intList));

        System.out.println("\nString list:");
        printList(stringList);
        System.out.println("Size: " + getListSize(stringList));

        System.out.println("\nDouble list:");
        printList(doubleList);
        System.out.println("Size: " + getListSize(doubleList));
    }
}
```

**Output:**

```
Integer list:
1
2
3
Size: 3

String list:
a
b
c
Size: 3

Double list:
1.1
2.2
3.3
Size: 3
```

### Upper Bounded Wildcards (? extends)

```java
import java.util.*;

public class UpperBoundedWildcards {

    // Method that works with lists of Number or its subtypes
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    // Method that finds max in a list of Comparable objects
    public static <T extends Comparable<T>> T findMax(List<? extends T> list) {
        if (list.isEmpty()) return null;

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 5, 3, 9, 2);
        List<Double> doubles = Arrays.asList(1.5, 3.7, 2.1, 4.8);
        List<Float> floats = Arrays.asList(2.5f, 1.2f, 3.8f);

        // Sum different types of numbers
        System.out.println("Sum of integers: " + sumNumbers(integers));
        System.out.println("Sum of doubles: " + sumNumbers(doubles));
        System.out.println("Sum of floats: " + sumNumbers(floats));

        // Find max in different lists
        System.out.println("Max integer: " + findMax(integers));
        System.out.println("Max double: " + findMax(doubles));

        List<String> strings = Arrays.asList("apple", "zebra", "banana");
        System.out.println("Max string: " + findMax(strings));
    }
}
```

**Output:**

```
Sum of integers: 20.0
Sum of doubles: 12.1
Sum of floats: 7.5
Max integer: 9
Max double: 4.8
Max string: zebra
```

### Lower Bounded Wildcards (? super)

```java
import java.util.*;

public class LowerBoundedWildcards {

    // Method that adds integers to a list that can hold Integer or its supertypes
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    // Generic method that copies from source to destination
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static void main(String[] args) {
        // Lower bounded wildcard example
        List<Integer> intList = new ArrayList<>();
        List<Number> numberList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        addNumbers(intList);    // Integer can accept Integer
        addNumbers(numberList); // Number can accept Integer
        addNumbers(objectList); // Object can accept Integer

        System.out.println("Integer list: " + intList);
        System.out.println("Number list: " + numberList);
        System.out.println("Object list: " + objectList);

        // Copy example
        List<Integer> source = Arrays.asList(10, 20, 30);
        List<Number> destination = new ArrayList<>();

        copy(source, destination);
        System.out.println("Copied to Number list: " + destination);

        // Another copy example
        List<String> stringSource = Arrays.asList("hello", "world");
        List<Object> objectDestination = new ArrayList<>();

        copy(stringSource, objectDestination);
        System.out.println("Copied to Object list: " + objectDestination);
    }
}
```

**Output:**

```
Integer list: [1, 2, 3]
Number list: [1, 2, 3]
Object list: [1, 2, 3]
Copied to Number list: [10, 20, 30]
Copied to Object list: [hello, world]
```

### PECS Principle (Producer Extends, Consumer Super)

```java
import java.util.*;

public class PECSDemo {

    // Producer - use extends (we're reading from the list)
    public static void processProducer(List<? extends Number> producer) {
        System.out.println("Processing producer list:");
        for (Number num : producer) {
            System.out.println("Processing: " + num + " (double value: " + num.doubleValue() + ")");
        }
        // producer.add(5); // Compilation error - can't add to producer
    }

    // Consumer - use super (we're writing to the list)
    public static void processConsumer(List<? super Integer> consumer) {
        System.out.println("Adding to consumer list:");
        consumer.add(10);
        consumer.add(20);
        consumer.add(30);
        System.out.println("Added integers to consumer");
        // Integer value = consumer.get(0); // Compilation error - can't guarantee return type
    }

    // Utility method demonstrating PECS
    public static <T> void transfer(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static void main(String[] args) {
        // Producer example
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        processProducer(intList);
        processProducer(doubleList);

        // Consumer example
        List<Integer> intConsumer = new ArrayList<>();
        List<Number> numberConsumer = new ArrayList<>();
        List<Object> objectConsumer = new ArrayList<>();

        processConsumer(intConsumer);
        processConsumer(numberConsumer);
        processConsumer(objectConsumer);

        System.out.println("Integer consumer: " + intConsumer);
        System.out.println("Number consumer: " + numberConsumer);
        System.out.println("Object consumer: " + objectConsumer);

        // Transfer example
        List<String> sourceStrings = Arrays.asList("a", "b", "c");
        List<Object> destinationObjects = new ArrayList<>();

        transfer(sourceStrings, destinationObjects);
        System.out.println("Transferred strings: " + destinationObjects);
    }
}
```

**Output:**

```
Processing producer list:
Processing: 1 (double value: 1.0)
Processing: 2 (double value: 2.0)
Processing: 3 (double value: 3.0)
Processing: 4 (double value: 4.0)
Processing: 5 (double value: 5.0)
Processing producer list:
Processing: 1.1 (double value: 1.1)
Processing: 2.2 (double value: 2.2)
Processing: 3.3 (double value: 3.3)
Adding to consumer list:
Added integers to consumer
Adding to consumer list:
Added integers to consumer
Adding to consumer list:
Added integers to consumer
Integer consumer: [10, 20, 30]
Number consumer: [10, 20, 30]
Object consumer: [10, 20, 30]
Transferred strings: [a, b, c]
```

## Type Erasure

Java generics are implemented using type erasure, meaning generic type information is removed at runtime.

```java
import java.util.*;
import java.lang.reflect.*;

public class TypeErasureDemo {

    public static void demonstrateTypeErasure() {
        List<String> stringList = new ArrayList<String>();
        List<Integer> intList = new ArrayList<Integer>();

        // At runtime, both lists have the same class
        System.out.println("String list class: " + stringList.getClass());
        System.out.println("Integer list class: " + intList.getClass());
        System.out.println("Are classes equal? " + (stringList.getClass() == intList.getClass()));
    }

    public static void demonstrateRawTypes() {
        // Raw type usage (not recommended)
        @SuppressWarnings("rawtypes")
        List rawList = new ArrayList();
        rawList.add("String");
        rawList.add(42);
        rawList.add(true);

        System.out.println("Raw list contents: " + rawList);

        // This would cause ClassCastException at runtime
        try {
            @SuppressWarnings("unchecked")
            List<String> stringList = rawList;
            for (String s : stringList) {
                System.out.println("String: " + s);
            }
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught: " + e.getMessage());
        }
    }

    // Generic method to show type erasure effects
    public static <T> void printTypeInfo(T item, List<T> list) {
        System.out.println("Item: " + item);
        System.out.println("Item class: " + item.getClass());
        System.out.println("List class: " + list.getClass());

        // Can't get generic type at runtime
        // System.out.println("T type: " + T.class); // Compilation error
    }

    public static void main(String[] args) {
        demonstrateTypeErasure();
        System.out.println();

        demonstrateRawTypes();
        System.out.println();

        List<String> stringList = Arrays.asList("Hello", "World");
        printTypeInfo("Test", stringList);
    }
}
```

**Output:**

```
String list class: class java.util.ArrayList
Integer list class: class java.util.ArrayList
Are classes equal? true

Raw list contents: [String, 42, true]
ClassCastException caught: java.lang.Integer cannot be cast to java.lang.String

Item: Test
Item class: class java.lang.String
List class: class java.util.Arrays$ArrayList
```

## Best Practices

### 1. Use Generic Types Instead of Raw Types

```java
// Bad - raw types
List list = new ArrayList();
Map map = new HashMap();

// Good - parameterized types
List<String> list = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();
```

### 2. Use Bounded Wildcards for API Flexibility

```java
public class BestPracticesDemo {

    // Good - flexible API using wildcards
    public static double calculateSum(List<? extends Number> numbers) {
        return numbers.stream()
                     .mapToDouble(Number::doubleValue)
                     .sum();
    }

    // Less flexible - only works with exact Number type
    public static double calculateSumBad(List<Number> numbers) {
        return numbers.stream()
                     .mapToDouble(Number::doubleValue)
                     .sum();
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);

        // This works with wildcards
        System.out.println("Sum of integers: " + calculateSum(integers));
        System.out.println("Sum of doubles: " + calculateSum(doubles));

        // This would require explicit casting for calculateSumBad
        // calculateSumBad(integers); // Compilation error
    }
}
```

### 3. Prefer Generic Methods Over Raw Types

```java
public class GenericMethodBestPractices {

    // Good - generic method
    public static <T> List<T> createSafeList(T... items) {
        List<T> list = new ArrayList<>();
        for (T item : items) {
            list.add(item);
        }
        return list;
    }

    // Bad - raw types
    @SuppressWarnings("unchecked")
    public static List createUnsafeList(Object... items) {
        List list = new ArrayList();
        for (Object item : items) {
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) {
        // Type-safe creation
        List<String> safeStrings = createSafeList("a", "b", "c");
        List<Integer> safeIntegers = createSafeList(1, 2, 3);

        System.out.println("Safe strings: " + safeStrings);
        System.out.println("Safe integers: " + safeIntegers);

        // Unsafe creation
        @SuppressWarnings("unchecked")
        List<String> unsafeList = createUnsafeList("a", 2, true); // No compile-time check
        System.out.println("Unsafe list: " + unsafeList);
    }
}
```

**Output:**

```
Sum of integers: 15.0
Sum of doubles: 7.5
Safe strings: [a, b, c]
Safe integers: [1, 2, 3]
Unsafe list: [a, 2, true]
```

### 4. Use Meaningful Type Parameter Names

```java
// Good - descriptive names
public class Repository<E extends Entity, K extends Serializable> {
    // E = Entity type, K = Key type
}

public interface Converter<S, T> {
    // S = Source type, T = Target type
    T convert(S source);
}

// Acceptable for simple cases
public class Box<T> {
    // T is commonly understood as "Type"
}
```

This guide covers the essential concepts of Java Generics with practical examples and their outputs. Generics provide type safety and code reusability while maintaining performance through type erasure at runtime.
