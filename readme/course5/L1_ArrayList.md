# ArrayList

**Table of Contents**

- [Overview](#overview)
- [Properties](#properties)
- [L1: Basic ArrayList Operations](#l1-basic-arraylist-operations)
    - [Generic Type Safety](#generic-type-safety)
    - [ArrayList Constructors](#arraylist-constructors)

**ArrayList** is similar to arrays but with dynamic sizing capabilities:

**Differences from Arrays:**

- **Arrays**: Fixed length after declaration
- **ArrayList**:
    - Dynamic growth and shrinkage
    - Provides predefined utility methods
    - Fast iteration and random access

**Properties:**
  - Ordered collection (by index)
  - Not sorted by default
  - Allows duplicate elements
  - Constructs an empty list with an initial capacity of ten.
  - Need fast random access to elements
  - Perform more read operations than write operations

## L1:  Basic ArrayList Operations

### Generic Type Safety

```java
// Without generics - allows any type (not recommended)
ArrayList mixedList = new ArrayList();
mixedList.add("String");
mixedList.add(123);
mixedList.add(new Person("Alex", 25, "Male"));

// With generics - type safe (recommended)
ArrayList<String> stringList = new ArrayList<>();
stringList.add("Apple");
stringList.add("Orange");
// stringList.add(123); // Compilation error - type safety


// Good practice - programming to interface
List<String> list = new ArrayList<>();  // Easy to change implementation later

// Avoid - tied to specific implementation
ArrayList<String> list = new ArrayList<>();
```

### ArrayList Constructors

```java
// 1. Default constructor (initial capacity 10)
ArrayList<String> list1 = new ArrayList<>();

// 2. Constructor with initial capacity
ArrayList<String> list2 = new ArrayList<>(5);

// 3. Constructor with existing collection
ArrayList<String> existingList = Arrays.asList("A", "B", "C");
ArrayList<String> list3 = new ArrayList<>(existingList);
```
