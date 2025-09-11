package course5.CollectionSort;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionSort_L2 {
    public static void main(String[] args) {
        // Sorting Strings
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Orange");
        fruits.add("Apple");
        fruits.add("Grape");
        fruits.add("Avocado");

        System.out.println("Before sorting: " + fruits);

        Collections.sort(fruits);

        System.out.println("After sorting: " + fruits);


        // Sorting Integers
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(10);
        numbers.add(1);

        System.out.println("Before sorting: " + numbers);

        Collections.sort(numbers);

        System.out.println("After sorting: " + numbers);

        Collections.reverse(numbers);

        System.out.println("After reverse: " + numbers);

        Collections.shuffle(numbers);

        System.out.println("After shuffle: " + numbers);
    }
}