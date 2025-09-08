package course3.L1_Inheritance;

import java.util.Objects;

class Human {
    public static final String WILL_SPEAK_NORMAL = " will speak normal";
    String name;
    int age;
    String gender;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Human human)) return false;
        return getAge() == human.getAge() && Objects.equals(getName(), human.getName()) && Objects.equals(getGender(), human.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getGender());
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Human() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Common methods for all humans
    public void walk() {
        String sample = getSample();
        if (true) {
            System.out.println(getName() + sample);
        }
    }

    private static String getSample() {
        String sample = " will walk normal";
        return sample;
    }

    public void speak() {
        System.out.println(getName() + WILL_SPEAK_NORMAL);
    }
}

// Subclass 1: Artist
// is-a: Artist is-a Human
class Artist extends Human {
    // inherits speak() without overriding
    // Special method only for artists
    public void Painting() {
        System.out.println(name + " is painting now.");
    }
}

// Subclass 2: Musician
// has-a: Musician has-a Keyboard, and is-a Human
class Musician extends Human {
    // Composition (has-a relationship)
    Keyboard myKeyboard = new Keyboard();

    // Special method only for musicians
    public void playMusicalInstruments() {
        System.out.println(name + " is playing keyboard now.");
    }

    // Overriding the speak method
    @Override
    public void speak() {
        System.out.println(name + " will speak calm and melodious.");
    }
}

class Keyboard {
    // "final" constants (cannot be reassigned)
    final int numberOfKeys = 88;
    final int numberOfSpeakers = 2;

    void playSound() {
        System.out.println("Boom boom");
        // numberOfKeys = 61; // ❌ COMPILATION ERROR: cannot assign a value to final variable
    }
}

class TempHuman {
    static void makeHumanSpeak(Human h) {
        h.name = h.getClass().getSimpleName(); // set name as class type
        h.speak();
    }
}

public class L1_Inheritance {
    public static void main(String[] args) {
        // Reference Type Artist, Object Artist
        Artist a = new Artist();
        a.name = "Lucia";
        a.age = 23;
        a.gender = "Female";
        a.Painting();        // specific to Artist
        a.walk();            // inherited from Human
        a.speak();           // inherited from Human

        System.out.println("-------");

        // Reference Type Musician, Object Musician
        Musician m = new Musician();
        m.name = "Jack";
        m.age = 51;
        m.gender = "Male";
        m.playMusicalInstruments(); // specific to Musician
        m.myKeyboard.playSound();   // uses composed object
        m.walk();                   // inherited from Human
        m.speak();                  // overridden in Musician

        System.out.println("-------");


        // Demonstrating polymorphic method parameter
        // Passing subclass objects to a method expecting Human
        TempHuman.makeHumanSpeak(a);  // Artist is-a Human
        TempHuman.makeHumanSpeak(m);  // Musician is-a Human
        System.out.println();

        // Reference Type Human, Object Artist
        Human h1 = new Artist();
        System.out.println(h1.toString());;
        h1.walk();            // calls Human's walk
        // h1.Painting();     // ❌ Cannot resolve method in 'Human'

        // Reference Type Human, Object Musician
        Human h2 = new Musician();
        h2.speak();
        // h2.myKeyboard.playSound();   // ❌ Cannot resolve method in 'Human'
        // h2.playMusicalInstruments(); // ❌ Cannot resolve method in 'Human'

        System.out.println("------");

        System.out.println("h1.equals(h2): " + h1.equals(h2));
        System.out.println("h1 == h2: " + (h1 == h2));         // true
        System.out.println();

        // is-a checks
        System.out.println("h1 instanceof Human: " + (h1 instanceof Human));
        System.out.println("h1 instanceof Artist: " + (h1 instanceof Artist));
    }
}
