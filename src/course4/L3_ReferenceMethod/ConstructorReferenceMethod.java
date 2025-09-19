package course4.L3_ReferenceMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

record Person(String name) { }

public class ConstructorReferenceMethod {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using lambda expression
        List<Person> people1 = new ArrayList<>();
        for (String name : names) {
            Person person = new Person(name);
            people1.add(person);
        }

        System.out.println(people1);

        // Using constructor reference
        List<Person> people2 = names.stream()
                .map(Person::new)  // Constructor reference
                .toList();

        people2.forEach(System.out::println);
    }
}