package course2.L4_Constructors;

public class PersonSuper {
    public PersonSuper() {
        System.out.println("PersonSuper default constructor");
    }

    public PersonSuper(String name, int age) {
        System.out.printf("PersonSuper parameterized: %s, %d, %n", name, age);
    }
}