public class L2_Peps {

    public static void main(String[] args) {
        // <ClassType> objectName = new <ClassName>
        Person alex = new Person();
        alex.name = "Alex";
        alex.age = 8;
        alex.child = true;
        alex.gender = Gender.MALE;
        alex.city = "Vancouver";
        alex.sleep();

        Person mia = new Person();
        mia.name = "Mia";
        mia.age = 72;
        mia.child = false;
        mia.city = "Toronto";
        mia.gender = Gender.FEMALE;
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

    void sleep() {
        // String interpolation in Java
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
