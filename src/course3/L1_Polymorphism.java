package course3;

class TempHuman {
    static void makeHumanSpeak(Human h) {
        h.name = h.getClass().getSimpleName(); // set name as class type
        h.speak();
    }
}


public class L1_Polymorphism {
    public static void main(String[] args) {
        Human h1 = new Artist();
        h1.name = "Lucia";
        h1.walk();   // calls Human's walk
        h1.speak(); // Human's speak()
        System.out.println();

        Human h2 = new Musician();
        h2.name = "Jack";
        // ❌ Cannot resolve method 'playKeyboard' in 'Human'
        // h2.playKeyboard();
        h2.walk();   // calls Human's walk
        h2.speak(); // Musician's overridden speak()
        System.out.println();

        // Demonstrating polymorphic method parameter
        // Passing subclass objects to a method expecting Human
        TempHuman.makeHumanSpeak(h1); // Artist is-a Human
        TempHuman.makeHumanSpeak(h2); // Musician is-a Human
    }


}
