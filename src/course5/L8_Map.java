package course5;

import java.util.*;

public class L8_Map {
    public static void main(String[] args) {
        System.out.println("1. HashMap Example");
        demonstrateHashMap();

        System.out.println("2. HashTable Example");
         demonstrateHashtable();

        System.out.println("3. LinkedHashMap Example");
        demonstrateLinkedHashMap();

        System.out.println("4. TreeMap Example");
        demonstrateTreeMap();

        System.out.println("1. Compare All Example");
        compareAllImplementations();
    }

    static void demonstrateHashMap() {
        // Create HashMap with String key and String value
        HashMap<String, String> countryMap = new HashMap<>();

        // Adding elements - order may vary in output
        countryMap.put("IN", "India");
        countryMap.put("US", "United States");
        countryMap.put("UK", "United Kingdom");
        countryMap.put("CN", "China");

        // Accessing elements
        System.out.println("Country with code 'IN': " + countryMap.get("IN"));

        // HashMap allows one null key
        countryMap.put(null, "Unknown Country");
        System.out.println("Null key value: " + countryMap.get(null));

        System.out.println("Size: " + countryMap.size());

        // Duplicate key overwrites previous value
        countryMap.put("CN", "People's Republic of China");

        // Iterating using entrySet
        System.out.println("\nAll countries:");
        for (Map.Entry<String, String> entry : countryMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Remove an element
        countryMap.remove("UK");
        System.out.println("\nAfter removing UK, size: " + countryMap.size());

        // Adding more elements
        countryMap.put("FR", "France");
        countryMap.put("DE", "Germany");

        // Iterating using entrySet
        System.out.println("\nAll countries, Order not maintained:");
        for (Map.Entry<String, String> entry : countryMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Check if key/value exists
        System.out.println("Contains key 'US': " + countryMap.containsKey("US"));
        System.out.println("Contains value 'India': " + countryMap.containsValue("India"));

        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    static void demonstrateHashtable() {
        // Create Hashtable - note the lowercase 't'
        Hashtable<String, String> hashTable = new Hashtable<>();

        // Adding elements
        hashTable.put("IN", "India");
        hashTable.put("US", "United States");
        hashTable.put("UK", "United Kingdom");
        hashTable.put("CN", "China");

        // Hashtable doesn't allow null keys or values
        try {
            hashTable.put(null, "Test");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: Hashtable doesn't allow null keys");
        }

        System.out.println("Size: " + hashTable.size());

        // All methods are synchronized
        System.out.println("Hashtable is synchronized (thread-safe)");

        System.out.println("\nHashtable contents:");
        for (Map.Entry<String, String> entry : hashTable.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    static void demonstrateLinkedHashMap() {
        // Create LinkedHashMap
        LinkedHashMap<String, String> linkedMap = new LinkedHashMap<>();

        // Adding elements - maintains insertion order
        linkedMap.put("IN", "India");
        linkedMap.put("US", "United States");
        linkedMap.put("UK", "United Kingdom");
        linkedMap.put("CN", "China");

        System.out.println("Size: " + linkedMap.size());
        System.out.println("Maintains insertion order:");

        for (Map.Entry<String, String> entry : linkedMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Adding more elements
        linkedMap.put("FR", "France");
        linkedMap.put("DE", "Germany");

        System.out.println("\nAfter adding more countries (order maintained):");
        linkedMap.forEach((key, value) ->
                System.out.println(key + " : " + value));

        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    static void demonstrateTreeMap() {
        // Create TreeMap - automatically sorts by key
        TreeMap<String, String> treeMap = new TreeMap<>();

        // Adding elements - will be automatically sorted
        treeMap.put("US", "United States");
        treeMap.put("IN", "India");
        treeMap.put("CN", "China");
        treeMap.put("UK", "United Kingdom");

        System.out.println("Size: " + treeMap.size());
        System.out.println("Automatically sorted by keys:");

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // TreeMap specific methods
        System.out.println("\nTreeMap specific operations:");
        System.out.println("treeMap.firstKey(): " + treeMap.firstKey());
        System.out.println("treeMap.lastKey(): " + treeMap.lastKey());
        System.out.println("treeMap.headMap('UK') Keys before 'UK': " + treeMap.headMap("UK").keySet());
        System.out.println("treeMap.tailMap('IN') Keys after 'IN': " + treeMap.tailMap("IN").keySet());

        // Custom sorting with TreeMap
        TreeMap<Integer, String> numberMap = new TreeMap<>(Collections.reverseOrder());
        numberMap.put(3, "Three");
        numberMap.put(1, "One");
        numberMap.put(4, "Four");
        numberMap.put(2, "Two");

        System.out.println("\nCreate a New TreeMap with reverse order:");
        numberMap.forEach((key, value) ->
                System.out.println(key + " : " + value));

        System.out.println("\n" + "=".repeat(50) + "\n");
    }

    static void compareAllImplementations() {

        String[] keys = {"US", "IN", "CN", "UK", "FR"};
        String[] values = {"United States", "India", "China", "United Kingdom", "France"};

        // HashMap
        Map<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            hashMap.put(keys[i], values[i]);
        }
        System.out.println("HashMap (no order):");
        hashMap.forEach((k, v) -> System.out.print(k + " "));
        System.out.println();

        // LinkedHashMap
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < keys.length; i++) {
            linkedHashMap.put(keys[i], values[i]);
        }
        System.out.println("\nLinkedHashMap (insertion order):");
        linkedHashMap.forEach((k, v) -> System.out.print(k + " "));
        System.out.println();

        // TreeMap
        Map<String, String> treeMap = new TreeMap<>();
        for (int i = 0; i < keys.length; i++) {
            treeMap.put(keys[i], values[i]);
        }
        System.out.println("\nTreeMap (sorted by key order):");
        treeMap.forEach((k, v) -> System.out.print(k + " "));
        System.out.println();

        // Hashtable
        Map<String, String> hashtable = new Hashtable<>();
        for (int i = 0; i < keys.length; i++) {
            hashtable.put(keys[i], values[i]);
        }
        System.out.println("\nHashtable (no order):");
        hashtable.forEach((k, v) -> System.out.print(k + " "));
        System.out.println();

    }
}
