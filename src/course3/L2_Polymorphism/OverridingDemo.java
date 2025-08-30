package course3.L2_Polymorphism;

abstract class Animal {
    private int minimumSpeed;

    public int getMinimumSpeed() { return minimumSpeed; }
    public void setMinimumSpeed(int minimumSpeed) { this.minimumSpeed = minimumSpeed; }

    public abstract void eat();
    public abstract void run();
}

class DomesticAnimal extends Animal {
    @Override
    public void eat() { System.out.println("I will eat milk."); }

    @Override
    public void run() { System.out.println("I am running at " + getMinimumSpeed() + " km/h."); }
}

class WildAnimal extends Animal {
    @Override
    public void eat() { System.out.println("I will eat meat."); }

    @Override
    public void run() { System.out.println("I am running at " + getMinimumSpeed() + " km/h."); }
}

class Cat extends DomesticAnimal {
    @Override
    public void eat() {
        super.eat();
        System.out.println("I will eat meat as well.");
    }
}

class Elephant extends WildAnimal {
    @Override
    public void eat() { System.out.println("I will eat fruits."); }
}

class Cheetah extends WildAnimal { }

public class OverridingDemo {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setMinimumSpeed(24);
        cat.eat();
        cat.run();

        System.out.println("-----");

        Elephant elephant = new Elephant();
        elephant.setMinimumSpeed(20);
        elephant.eat();
        elephant.run();

        System.out.println("-----");

        Cheetah cheetah = new Cheetah();
        cheetah.setMinimumSpeed(100);
        cheetah.eat();
        cheetah.run();
    }
}