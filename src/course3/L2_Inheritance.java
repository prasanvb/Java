package course3;

class Human {
    String name;
    int age;
    String gender;

    // Common methods for all humans
    public void walk() {
        System.out.println(name + " will walk normal");
    }

    public void speak() {
        System.out.println(name + " will speak normal");
    }
}

// Subclass 1: Artist
class Artist extends Human {
    // inherits speak() without overriding
    // Special method only for artists
    public void Painting() {
        System.out.println(name + " is painting now.");
    }
}

// Subclass 2: Musician
class Musician extends Human {
    // Special method only for musicians
    public void playKeyboard() {
        System.out.println(name + " is playing keyboard now.");
    }

    // Overriding the speak method
    @Override
    public void speak() {
        System.out.println(name + " will speak calm and melodious.");
    }
}

public class L2_Inheritance {
    public static void main(String[] args) {
        // Creating Artist object
        Artist a = new Artist();
        a.name = "Lucia";
        a.age = 23;
        a.gender = "Female";
        a.Painting(); // specific to Artist
        a.walk();            // inherited from Human
        a.speak();           // inherited from Human

        System.out.println();

        // Creating Musician object
        Musician m = new Musician();
        m.name = "Jack";
        m.age = 51;
        m.gender = "Male";
        m.playKeyboard(); // specific to Musician
        m.walk();         // inherited from Human
        m.speak();        // overridden in Musician
    }
}
