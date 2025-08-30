package course3.L3_Interface;

interface Human {
    // Constants (implicitly public, static, final)
    String BLOOD_COLOR = "Red";
    int EYES = 2;
    int NOSE = 1;

    // Abstract methods (implicitly public and abstract)
    void work();
    void speak();

    // Default method (Java 8+ feature)
    default void introduce() {
        System.out.println("Hello! I am from the Human interface.");
    }
}