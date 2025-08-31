package course2;

public class L5_Singleton {

    // Step 1: Private static variable of the same class (holds single instance)
    private static L5_Singleton instance;

    // Step 2: Private constructor prevents external instantiation
    private L5_Singleton() {
        System.out.println("L5_Singleton Instance Created!");
    }

    // Step 3: Public static method to provide global access point
    // Using synchronized to make it thread-safe
    public static synchronized L5_Singleton getInstance() {
        if (instance == null) {
            instance = new L5_Singleton();
        }
        return instance;
    }

    // Example method
    public void showMessage(String obj) {
        System.out.println("Hello from L5_Singleton! " + obj);
    }

    // Main method to test L5_Singleton
    public static void main(String[] args) {
        System.out.println("Getting first instance...");
        L5_Singleton obj1 = L5_Singleton.getInstance();
        obj1.showMessage("obj1");

        System.out.println("\nGetting second instance...");
        L5_Singleton obj2 = L5_Singleton.getInstance();
        obj2.showMessage("obj2");

        // Check if both references point to the same object
        System.out.println("\nAre both instances same? " + (obj1 == obj2));
    }
}
