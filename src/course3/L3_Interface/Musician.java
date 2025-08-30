package course3.L3_Interface;

class Musician implements Human, SocialAnimal {
    @Override
    public void work() {
        System.out.println("This is how a Musician works: composing music...");
    }

    @Override
    public void speak() {
        System.out.println("This is how a Musician speaks: rhythm in words.");
    }

    // Extra behavior not in interface
    public void playInstrument() {
        System.out.println("Musician is playing the guitar.");
    }
}