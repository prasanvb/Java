package course5;

import java.util.ArrayList;

public class L1_ArrayList {
    public static void main(String[] args) {
        // 1. Creating ArrayList
        ArrayList<String> fruits;
        fruits = new ArrayList<>();

        // 2. Adding elements
        fruits.add("Apple");        // Adds at end
        fruits.add("Orange");       // Adds at end
        fruits.add(1, "Grape");     // Adds at index 1, previous item moved to index 2

        System.out.println("After adding elements:");
        printList(fruits);

        // 3. Accessing elements
        String firstFruit0 = fruits.getFirst();
        String firstFruit1 = fruits.get(1);
        System.out.println("fruits: " + firstFruit0 + firstFruit1);


        // 4. Removing elements
        fruits.remove("Apple");
        System.out.println("After removing Apple:");
        printList(fruits);
        // No error on trying to remove unknown element from array
        fruits.remove("Banana");

        // 5. Checking size
        System.out.println("List size: " + fruits.size());

        // 6. Checking if element exists
        if (fruits.contains("Grape")) {
            System.out.println("Grape found in the list");
        }
    }

    // Helper method to print all elements
    private static void printList(ArrayList<String> list) {
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}