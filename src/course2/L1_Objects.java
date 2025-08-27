package course2;

public class L1_Objects {

    public static void main(String[] args) {
        // Create object to call non-static methods
        L1_Objects obj = new L1_Objects();

        L1_Objects.staticMethod();  // Static method via class name

        // Call sayHello method with different parameters
        obj.sayHello("female");
        obj.sayHello("MALE"); // Case-insensitive check

        // Call different modifier methods
        obj.publicMethod();         // Accessible everywhere
        obj.protectedMethod();      // Accessible within package and subclasses
        obj.finalMethod();          // Final method (cannot be overridden)
    }

    //  Method Structure & Parameters
    public void sayHello(String gender) {
        // Case-insensitive comparison
        String message = gender.equalsIgnoreCase("male")
                ? "Hello Sir"
                : "Hello Madam";

        System.out.println(message);
    }

    // Static Method
    public static void staticMethod() {
        System.out.println("Static method: belongs to class, can be called without object");
    }

    // Public Method
    public void publicMethod() {
        System.out.println("Public method: accessible everywhere");
    }

    // Private Method
    private void privateMethod() {
        System.out.println("Private method: accessible only inside this class");
    }

    // Helper to call private method from within the class
    public void callPrivateMethod() {
        privateMethod();
    }

    // Protected Method
    protected void protectedMethod() {
        System.out.println("Protected method: accessible within package and subclasses");
    }

    // Final Method
    public final void finalMethod() {
        System.out.println("Final method: cannot be overridden");
    }
}
