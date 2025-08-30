package course3.L2_Polymorphism;

public class OverridingDemo {
    public static void main(String[] args) {
        // Creating a Cat object (DomesticAnimal -> Animal)
        Cat cat = new Cat();
        cat.setMinimumSpeed(24);  // Set the minimum speed using setter from Animal class
        cat.eat();  // Calls overridden eat() in Cat -> calls DomesticAnimal eat() via super
        cat.run();  // Calls run() from DomesticAnimal class

        System.out.println("-----");

        // Creating an Elephant object (WildAnimal -> Animal)
        Elephant elephant = new Elephant();
        elephant.setMinimumSpeed(20);  // Set minimum speed for elephant
        elephant.eat();  // Calls overridden eat() in Elephant class
        elephant.run();  // Calls run() from WildAnimal class

        System.out.println("-----");

        // Creating a Cheetah object (WildAnimal -> Animal)
        Cheetah cheetah = new Cheetah();
        cheetah.setMinimumSpeed(100);  // Set speed for cheetah
        cheetah.eat();  // Calls eat() from WildAnimal (no override in Cheetah)
        cheetah.run();  // Calls run() from WildAnimal class
    }
}

// Abstract base class
abstract class Animal {
    private int minimumSpeed;  // Encapsulated field

    // Getter and Setter for encapsulation
    public int getMinimumSpeed() {
        return minimumSpeed;
    }

    public void setMinimumSpeed(int minimumSpeed) {
        this.minimumSpeed = minimumSpeed;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void eat();
    public abstract void run();
}

// Subclass of Animal representing domestic animals
class DomesticAnimal extends Animal {
    @Override
    public void eat() {
        System.out.println("I am a domestic animal, I eat vegetables");
    }

    @Override
    public void run() {
        System.out.println("I domestic animal run at " + getMinimumSpeed() + " km/h.");
    }
}

// Subclass of Animal representing wild animals
class WildAnimal extends Animal {
    @Override
    public void eat() {
        System.out.println("I am a wild animal, I eat meat.");
    }

    @Override
    public void run() {
        System.out.println("I wild animal run at " + getMinimumSpeed() + " km/h.");
    }
}

// Cat extends DomesticAnimal and overrides eat() method
class Cat extends DomesticAnimal {
    @Override
    public void eat() {
        super.eat();  // Call parent class eat() method (DomesticAnimal)
        System.out.println("I am a cat, will eat meat as well.");  // Additional behavior
    }
}

// Elephant extends WildAnimal and overrides eat() method
class Elephant extends WildAnimal {
    @Override
    public void eat() {
        System.out.println("I am an elephant, will eat fruits as well."); // Custom behavior
    }
}

// Cheetah extends WildAnimal but does not override any method
// Inherits eat() and run() behavior from WildAnimal
class Cheetah extends WildAnimal {
}
