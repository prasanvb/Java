package course5;

import course5.ComparableSort_L3.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Comparator_L4 {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alex", 25, "Male"));
        people.add(new Person("Zara", 22, "Female"));
        people.add(new Person("Morgan", 25, "Male")); // Same age as Alex

        // Sort by age, then by name
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int ageComparison = Integer.compare(p1.getAge(), p2.getAge());
                if (ageComparison == 0) {
                    return p1.getName().compareTo(p2.getName());
                }
                return ageComparison;
            }
        });

        System.out.println("Sorted by age, then by name:");
        for (Person person : people) {
            System.out.println(person);
        }
        // Output:
        // Zara(22, Female)
        // Alex(25, Male)
        // Morgan(25, Male)
    }
}