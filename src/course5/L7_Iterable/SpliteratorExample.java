package course5.L7_Iterable;

import java.util.*;
import java.util.Spliterator;

public class SpliteratorExample {
    public static void main(String[] args) {
        Iterable<String> fruits = Arrays.asList("Apple", "Mango", "Banana", "Grapes", "Orange");

        // Get a Spliterator from the ArrayList
        Spliterator<String> mainSpliterator = fruits.spliterator();

        // Check characteristics
        System.out.println("ORDERED: " + mainSpliterator.hasCharacteristics(Spliterator.ORDERED));
        System.out.println("SIZED: " + mainSpliterator.hasCharacteristics(Spliterator.SIZED));
        System.out.println("Estimated size: " + mainSpliterator.estimateSize());

        // Try to split the main Spliterator
        Spliterator<String> splitSpliterator = mainSpliterator.trySplit();

        System.out.println("Elements processed by the main Spliterator:");
        // Process elements with the main Spliterator
        mainSpliterator.forEachRemaining(System.out::println);

        System.out.println("\nElements processed by the split Spliterator:");
        // Process elements with the split Spliterator (if a split occurred)
        if (splitSpliterator != null) {
            splitSpliterator.forEachRemaining(System.out::println);
        } else {
            System.out.println("No split occurred or split Spliterator is empty.");
        }
    }
}