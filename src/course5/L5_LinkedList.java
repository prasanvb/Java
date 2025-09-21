package course5;

import java.util.LinkedList;

public class L5_LinkedList {
    public static void main(String[] args) {
        // 1. Creating LinkedList
        LinkedList<String> myList = new LinkedList<>();

        // 2. Adding elements (same methods as ArrayList)
        myList.add("C");           // Adds at end
        myList.add("A");           // Adds at end
        myList.add("Z");           // Adds at end
        myList.addFirst("I");      // Adds at beginning (LinkedList specific)

        System.out.println("After adding elements:");
        printList(myList);
        // Output: I C A Z

        // 3. Accessing first and last elements (LinkedList specific)
        System.out.println("First element: " + myList.getFirst());
        // Output: First element: I

        System.out.println("Last element: " + myList.getLast());
        // Output: Last element: Z

        // 4. Removing elements
        myList.remove();           // Removes first element
        System.out.println("After removing first element:");
        printList(myList);
        // Output: C A Z

        myList.remove("A");        // Removes specific element
        System.out.println("After removing 'A':");
        printList(myList);
        // Output: C Z

        // 5. LinkedList specific removal methods
        myList.addFirst("X");
        myList.addLast("Y");
        System.out.println("After adding X at first and Y at last:");
        printList(myList);
        // Output: X C Z Y

        myList.removeFirst();      // Remove first element
        myList.removeLast();       // Remove last element
        System.out.println("After removing first and last:");
        printList(myList);
        // Output: C Z
    }

    private static void printList(LinkedList<String> list) {
        for (String element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
