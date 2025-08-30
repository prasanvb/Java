package course3.L4_Abstract;

abstract class Animal {
    String name;

    // Constructor
    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called. Name = " + name);
    }

    // Abstract method (incomplete)
    abstract void sound();

    // Concrete method
    void eat() {
        System.out.println(name + " is eating.");
    }
}
