package course5.collectionSort;

import java.util.ArrayList;
import java.util.Collections;

public class ComparableSort_L3 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();

        // Adding Person objects
        people.add(new Person("Zara", 22, "Female"));
        people.add(new Person("Alex", 21, "Male"));
        people.add(new Person("Morgan", 23, "Male"));

        System.out.println("Before sorting:");
        printPeople(people);
        // Output:
        // Before sorting:
        // Zara(22, Female)
        // Alex(21, Male)
        // Morgan(23, Male)

        // Sort using Collections.sort()
        Collections.sort(people);

        System.out.println("\nAfter sorting by name:");
        printPeople(people);
        // Output:
        // After sorting by name:
        // Alex(21, Male)
        // Morgan(23, Male)
        // Zara(22, Female)
    }

    private static void printPeople(ArrayList<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }
}