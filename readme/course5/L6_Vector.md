
# L6:Vector Implementation

**Table of Contents**
- [Historical Context and Characteristics](#historical-context-and-characteristics)
- [Key Differences from ArrayList](#key-differences-from-arraylist)
- [Capacity Growth Comparison](#capacity-growth-comparison)
- [Thread Safety Example](#thread-safety-example)
- [Alternative for Vector](#alternative-for-vector)
- [Comparison Table](#comparison-table)


### Historical Context and Characteristics

**Vector** is one of the original collection classes in Java, along with Hashtable. Most other collections were added in Java 1.2 and 1.4.

### Key Differences from ArrayList

| Feature             | ArrayList          | Vector                          |
|---------------------|--------------------|---------------------------------|
| **Thread Safety**   | Not synchronized   | Synchronized (thread-safe)      |
| **Performance**     | Faster             | Slower (due to synchronization) |
| **Capacity Growth** | Increases by 50%   | Increases by 100% (doubles)     |
| **Legacy**          | Modern (Java 1.2+) | Legacy (Java 1.0)               |

### Capacity Growth Comparison

```java
// ArrayList capacity growth
// Initial: 10 -> When full -> 15 (50% increase)

// Vector capacity growth
// Initial: 10 -> When full -> 20 (100% increase)
```

### Thread Safety Example

```java
// Vector methods are synchronized
Vector<String> vector = new Vector<>();

// This is thread-safe but slower
vector.add("Thread Safe");

// ArrayList equivalent (not thread-safe but faster)
ArrayList<String> arrayList = new ArrayList<>();
arrayList.add("Not Thread Safe");

// For thread-safety with ArrayList, use:
List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
```

### Alternative for Vector

```java
// Better approach for thread safety
List<String> threadSafeList = Collections.synchronizedList(new ArrayList<>());

// Or use concurrent collections
List<String> concurrentList = new CopyOnWriteArrayList<>();
```

### Comparison Table

| Feature                          | ArrayList                | LinkedList                  | Vector                     |
|----------------------------------|--------------------------|-----------------------------|----------------------------|
| **Internal Structure**           | Dynamic Array            | Doubly Linked Nodes         | Dynamic Array              |
| **Random Access**                | O(1)                     | O(n)                        | O(1)                       |
| **Insertion/Deletion at middle** | O(n)                     | O(1)                        | O(n)                       |
| **Memory Overhead**              | Low                      | High                        | Low                        |
| **Thread Safety**                | No                       | No                          | Yes                        |
| **Performance**                  | High                     | Medium                      | Low                        |
| **Best Use Case**                | Random access, searching | Frequent insertion/deletion | Legacy code, thread safety |

