package course5.L2L3L4_collectionSort;

// Person class implementing Comparable with record

// public record Person(String name, int age, String gender) implements Comparable<Person> {

public class Person implements Comparable<Person> {
    private final String name;
    private final int age;
    private final String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getter methods
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    // Override compareTo method for natural ordering
    @Override
    public int compareTo(Person other) {
        // Sort by name alphabetically
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + "(" + age + ", " + gender + ")";
    }


}