package course1;

public class L2_Peps {
    public static void main(String[] args) {

        // Using no-arg constructor + field assignments
        // Order and count of parameters DOES NOT matter
        Person x = new Person();
        x.name="X";
        x.gender = Gender.nonBinary;
        x.sleep();

        // Using parameterized constructor
        // Order and count of parameters matter
        Person alex = new Person("Alex", 8, Gender.MALE);
        alex.sleep();

        Person mia = new Person("Mia", 62, Gender.FEMALE, false, "Toronto");
        mia.sleep();
    }
}

enum Gender {
    MALE,
    FEMALE,
    nonBinary,

}

class Person {
    String name;
    int age;
    Gender gender;
    boolean child;
    String city;

    // Constructor overloading
    // ✅ No-arg constructor
    Person() {}

    // ✅ Parameterized constructor
    public Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    Person(String name, int age, Gender gender, boolean child, String city) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.child = child;
        this.city = city;
    }

    void sleep() {
        String msg = String.format(
                "My name is %s, I am %d years old, I live in %s, course1.Gender: %s, Is child: %b",
                name, age, city, gender, child
        );

        if (age < 10) {
            System.out.println(msg + " → I need more sleep!");
        } else if (age <= 50) {
            System.out.println(msg + " → I’m in my working years.");
        } else {
            System.out.println(msg + " → I enjoy my naps!");
        }
    }
}
