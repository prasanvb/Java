public class L2_Peps {

    public static void main(String[] args) {
        // Using default constructor + field assignments
        Person alex = new Person();
        alex.name = "Alex";
        alex.age = 8;
        alex.child = true;
        alex.gender = Gender.MALE;
        alex.city = "Vancouver";
        alex.sleep();

        // Using parameterized constructor
        Person mia = new Person("Mia", 62, Gender.FEMALE, false, "Toronto");
        mia.sleep();
    }
}

enum Gender {
    MALE,
    FEMALE
}

class Person {
    String name;
    int age;
    Gender gender;
    boolean child;
    String city;

    // Constructor overloading
    // ✅ No-arg constructor (needed for Alex style)
    public Person() {}

    // ✅ Parameterized constructor (needed for Mia style)
    public Person(String name, int age, Gender gender, boolean child, String city) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.child = child;
        this.city = city;
    }

    void sleep() {
        String msg = String.format(
                "My name is %s, I am %d years old, I live in %s, Gender: %s, Is child: %b",
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
