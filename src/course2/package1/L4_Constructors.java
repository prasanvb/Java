package course2.package1;

import course2.package2.L4_PersonSuper;

public class L4_Constructors extends L4_PersonSuper {
    private String name;
    private int age;
    private String gender;

    // Default constructor
    public L4_Constructors() {
        super(); // optional, inserted by compiler if omitted
        System.out.println("Person default constructor");
    }

    //  Parameterized constructor with parameterized super
    public L4_Constructors(String name, int age) {
        super(name, age);
        System.out.println("Person parameterized constructor with 2 params");
        this.name = name;
        this.age = age;

    }

    // Parameterized constructor with this
    public L4_Constructors(String name, int age, String gender) {
        this(); // calls default constructor of Person
        System.out.println("Person parameterized constructor with 3 params");
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static void main(String[] args) {
        System.out.println("PSVM - Creating objects from parameterized constructor");
        L4_Constructors alex = new L4_Constructors("Alex", 55, "Male");
        L4_Constructors mia = new L4_Constructors("Mia", 25);
    }
}
