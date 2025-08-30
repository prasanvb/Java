package course3.L4_Abstract;

public class L4_Abstract {
    public static void main(String[] args) {
        // 🚧 Refence type is abstract class, object is of subclass
        // Animal a = new Animal("Generic"); // ❌ ERROR: cannot instantiate abstract class
        Animal d = new Dog("Rocky");         // ✅ Works
        d.eat();
        d.sound();
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name); // calls abstract class constructor
    }

    @Override
    void sound() {
        System.out.println(name + " barks.");
    }
}
