package course5;

import java.util.Vector;

public class L6_Vector {
    public static void main(String[] args) {
        // 1. Creating Vector
        Vector<String> myList = new Vector<>();

        // 2. Adding elements (same as ArrayList)
        myList.add("A");
        myList.add("B");
        myList.add("C");

        // 3. Vector-specific method
        myList.addElement("D");    // Legacy method, same as add()

        System.out.println("Vector elements:");
        for (String element : myList) {
            System.out.print(element + " ");
        }
        System.out.println();
        // Output: A B C D

        // 4. Accessing elements (same as ArrayList)
        System.out.println("Element at index 1: " + myList.get(1));
        // Output: Element at index 1: B

        // 5. Vector size and capacity
        System.out.println("Size: " + myList.size());
        System.out.println("Capacity: " + myList.capacity());
        // Output: Size: 4, Capacity: 10

        // 6. Removing elements
        myList.remove("B");
        System.out.println("After removing B: " + myList);
        // Output: After removing B: [A, C, D]
    }
}