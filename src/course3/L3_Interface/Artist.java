package course3.L3_Interface;

class Artist implements Human, SocialAnimal {
    @Override
    public void work() {
        System.out.println("This is how an Artist works: painting...");
    }

    @Override
    public void speak() {
        System.out.println("This is how an Artist speaks: inspired and thoughtful.");
    }

    // Extra behavior not in interface
    public void preparePainting() {
        System.out.println("Artist is preparing the painting.");
    }
}