package course5;

import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Queue_L9 {
    public static void main(String[] args) {
        System.out.println("=== LinkedList Queue Example (FIFO) ===");
        linkedListQueueExample();

        System.out.println("\n=== PriorityQueue Example (Sorted Order) ===");
        priorityQueueExample();

        System.out.println("\n=== Method Comparison Example ===");
        methodComparisonExample();
    }

    // LinkedList implementation - maintains insertion order
    public static void linkedListQueueExample() {
        // Create a queue using LinkedList (FIFO order)
        Queue<String> queue = new LinkedList<>();

        // Add elements (enqueue)
        queue.add("Apple");    // Head element
        queue.add("Orange");
        queue.add("Grape");    // Tail element

        System.out.println("Initial Queue: " + queue);
        System.out.println("Head element: " + queue.element()); // Should be "Apple"

        // Remove head element (dequeue)
        System.out.println("Removed element: " + queue.remove()); // Removes "Apple"
        System.out.println("Queue after removal: " + queue);
    }



    // PriorityQueue implementation - natural ordering
    public static void priorityQueueExample() {
        // PriorityQueue (sorted order)
        Queue<String> priorityQueue = new PriorityQueue<>();

        // Adds elements in specific order
        priorityQueue.add("Orange");   // Added first
        priorityQueue.add("Apple");
        priorityQueue.add("Grape");    // Added last

        System.out.println("Priority Queue (sorted): " + priorityQueue);
        System.out.println("Peak element: " + priorityQueue.peek()); // Should be "Apple" (alphabetically first)

        // Remove elements - will be removed in sorted order, not insertion order
        String removed = priorityQueue.poll(); // Removes "Apple"
        System.out.println("Removed element: " + removed);
        System.out.println("Queue after removal: " + priorityQueue);
        System.out.println("New peak element: " + priorityQueue.peek()); // Should be "Grape"

        // Remove another element
        priorityQueue.poll(); // Removes "Grape"
        System.out.println("Final queue: " + priorityQueue); // Should contain only "Orange"
    }

    // Demonstrate difference between remove() and poll() methods
    public static void methodComparisonExample() {
        Queue<String> queue = new LinkedList<>();

        // Test with empty queue
        System.out.println("Testing with empty queue:");

        // poll() returns null for empty queue
        System.out.println("poll() result: " + queue.poll()); // null

        // remove() throws exception for empty queue
        try {
            System.out.println("remove() result: " + queue.remove());
        } catch (NoSuchElementException e) {
            System.out.println("remove() threw exception: " + e.getClass().getSimpleName());
        }

        // peek() returns null for empty queue
        System.out.println("peek() result: " + queue.peek()); // null

        // element() throws exception for empty queue
        try {
            System.out.println("element() result: " + queue.element());
        } catch (NoSuchElementException e) {
            System.out.println("element() threw exception: " + e.getClass().getSimpleName());
        }
    }
}