package course1;

// Example to demonstrate primitive, reference, instance, and local variables in Java
class Pupil {
    // Instance variables (default values if not initialized)
    String name;   // default = null
    int age;       // default = 0
    boolean active; // default = false
}

public class L3_Variables {
    public static void main(String[] args) {
        // Local variable: must be initialized before use
        int localVar = 25;
        System.out.println("Local variable value: " + localVar);

        // Primitive variable
        byte smallNum = 100; // within range (-128 to 127)
        System.out.println("Primitive byte value: " + smallNum);

        // Reference variable: points to an object
        Pupil p = new Pupil();
        p.name = "Alex";
        p.age = 30;
        p.active = true;
        System.out.println("Pupil details: " + p.name + ", " + p.age + ", active: " + p.active);

        // Superclass reference pointing to subclass object
        Animal a = new Cat(); // valid (Cat is an Animal)
        a.speak();

        // Cat reference cannot hold Animal object
        // Cat c = new Animal(); // ❌ compilation error
    }
}

class Animal {
    void speak() { System.out.println("Animal speaking..."); }
}

class Cat extends Animal {
    void speak() { System.out.println("Cat meows!"); }
}

