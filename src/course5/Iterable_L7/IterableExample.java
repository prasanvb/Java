package course5.Iterable_L7;

import java.util.*;

public class IterableExample {
    public static void main(String[] args) {
        Iterable<String> items = Arrays.asList("apple", "banana", "cherry");

        // Lambda
        items.forEach(item -> System.out.println(item.toUpperCase()));

        // Method reference
        items.forEach(System.out::println);

        // For each loop
        for (String name : items) {
            System.out.println("--"+name+"--");
        }
    }
}
