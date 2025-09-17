# Sorting Collections

## Parallel Streams

`parallelStream()` method provides a way to process elements of a stream in parallel, 
leveraging multiple CPU cores to potentially improve performance for large datasets or computationally intensive operations.
From a Collection: Call the parallelStream() method directly on a Collection (e.g., List, Set):

```declarative
    List<Integer> myList = new ArrayList<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    Stream<Integer> parallelStream = myList.parallelStream();
```



## L2: Sorting Primitive Types

The `Collections.sort()` method can sort collections of objects such as String, Numbers that have natural ordering

## L3: Sorting Custom Objects

For custom objects, you must implement the `Comparable` interface:


## L4: Advanced Sorting with Comparator

For more complex sorting scenarios, you can use `Comparator`:

### Collection vs Collections vs Collection

**Be careful not to confuse:**

1. **collection** (lowercase): General term for data structure
2. **Collection** (uppercase, singular): Root interface from java.util package
    ```java
    // Collection interface
    Collection<String> myCollection = new ArrayList<>();
    ```
3. **Collections** (uppercase, plural): Utility class from java.util package containing static methods
    ```java
    // Collections utility class
    Collections.sort(myList);          // Static method
    Collections.reverse(myList);       // Static method
    ```

### Key Takeaways

1. **Choose the right collection type** based on your use case:
    - List: For ordered, indexed access
    - Set: For unique elements
    - Map: For key-value pairs with fast access
    - Queue: For FIFO processing
2. **Consider performance implications** of different implementations
3. **Use generics** for type safety and avoiding ClassCastException
4. **Implement Comparable** for custom objects that need natural ordering
5. **Use Collections utility methods** for common operations like sorting
