package course5;

import java.util.*;

public class L10_Set {

    public static void main(String[] args) {
        System.out.println("\n=== Insertion Order Example - HashSet, LinkedHashSet, and TreeSet ===");
        demonstrateInsertionOrder();

        System.out.println("\n=== Performance Example - HashSet, LinkedHashSet, and TreeSet ===");
        demonstratePerformance();

        System.out.println("\n=== HashSet CustomObjectExample ===");
        CustomObjectExample();

        System.out.println("\n=== TreeSet - sorted by name ===");
        TreeSetCustomExample();


    }

    static void CustomObjectExample() {

        Set<Person> personSet = new HashSet<>();

        // Add persons
        personSet.add(new Person("Zia", 22, "Female"));
        personSet.add(new Person("Zia", 22, "Female")); // Duplicate name
        personSet.add(new Person("Alex", 24, "Male"));
        personSet.add(new Person("Zia", 30, "Female")); // Same name, different age

        System.out.println("Person Set (duplicates by name removed):");
        for (Person p : personSet) {
            System.out.println(p);
        }

        System.out.println("Set size: " + personSet.size()); // Output: 2
    }

    private static void demonstrateInsertionOrder() {
        // Sample data
        String[] fruits = {"Grape", "Apple", "Orange", "Banana", "Apple", "Grape"};

        // HashSet - No order guarantee
        Set<String> hashSet = new HashSet<>();
        Collections.addAll(hashSet, fruits);
        System.out.println("HashSet (no order): " + hashSet);

        // LinkedHashSet - Maintains insertion order
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Collections.addAll(linkedHashSet, fruits);
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);

        // TreeSet - Sorted order
        Set<String> treeSet = new TreeSet<>();
        Collections.addAll(treeSet, fruits);
        System.out.println("TreeSet (sorted): " + treeSet);

    }

    private static void demonstratePerformance() {
        System.out.println("Performance Test (1 million operations):");
        int size = 1000000;

        // HashSet performance
        long start = System.currentTimeMillis();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        long hashSetTime = System.currentTimeMillis() - start;

        // LinkedHashSet performance
        start = System.currentTimeMillis();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        for (int i = 0; i < size; i++) {
            linkedHashSet.add(i);
        }
        long linkedHashSetTime = System.currentTimeMillis() - start;

        // TreeSet performance
        start = System.currentTimeMillis();
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            treeSet.add(i);
        }
        long treeSetTime = System.currentTimeMillis() - start;

        System.out.println("HashSet time: " + hashSetTime + " ms");
        System.out.println("LinkedHashSet time: " + linkedHashSetTime + " ms");
        System.out.println("TreeSet time: " + treeSetTime + " ms");
    }

    private static void TreeSetCustomExample() {

        // TreeSet (automatically sorted by name)
        TreeSet<PersonComparable> personSet = new TreeSet<>();

        // Add persons in random order
        personSet.add(new PersonComparable("Zia", 22, "Female"));
        personSet.add(new PersonComparable("Rome", 23, "Male"));
        personSet.add(new PersonComparable("Alex", 24, "Male"));
        personSet.add(new PersonComparable("Lucy", 21, "Female"));
        personSet.add(new PersonComparable("Bob", 25, "Male"));

        System.out.println("TreeSet (automatically sorted by name):");
        for (PersonComparable p : personSet) {
            System.out.println(p);
        }

        // Navigation methods available in TreeSet
        System.out.println("\nNavigation Methods:");
        System.out.println("First item in the set: " + personSet.first());
        System.out.println("Last item in the set: " + personSet.last());
        System.out.println("Compare by Name higher than 'Lucy': " + personSet.higher(new
                PersonComparable("Lucy", 0, "")));
        System.out.println("Compare by Name lower than 'Lucy': " + personSet.lower(new
                PersonComparable("Lucy", 0, "")));
    }
}


record Person(String name, int age, String gender) {
    // Constructor

    // Override equals() - using name for equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;
        return Objects.equals(name, person.name);
    }

    // Override hashCode() - using same attribute as equals()
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", gender='" + gender + "'}";
    }
}

record PersonComparable(String name, int age, String gender) implements Comparable<PersonComparable> {

    // Required for TreeSet - defines natural ordering
    @Override
    public int compareTo(PersonComparable other) {
        return this.name.compareTo(other.name); // Sort by name
    }

    // Still need equals() and hashCode() for Set contract
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersonComparable person = (PersonComparable) obj;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", gender='" + gender + "'}";
    }
}
