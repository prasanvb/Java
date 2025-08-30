package course3.L3_Interface;

public class L3_Interface {
    public static void main(String[] args) {
        // 🚧 Reference Type Human, Artist object
        Human h1 = new Artist();
        h1.work();
        h1.speak();
        h1.introduce(); // Default method

        System.out.println("---");

        // 🚧 Reference type Human, Musician object
        Human h2 = new Musician();
        h2.work();
        h2.speak();
        h2.introduce();

        System.out.println("---");

        // 🚧 Reference Type Social Animal, Artist object
        SocialAnimal s1 = new Artist();
        s1.action();

        System.out.println("---");

        //🚧 Using concrete class reference to call extra methods
        Artist a = new Artist();
        a.preparePainting();

        Musician m = new Musician();
        m.playInstrument();
    }
}