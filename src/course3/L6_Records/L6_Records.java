package course3.L6_Records;

public class L6_Records {
    public static void main(String[] args) {
        // Basic record usage
        Product laptop = new Product("Laptop", 1200.50);
        System.out.println(laptop.name());    // Accessor
        System.out.println(laptop.price());
        System.out.println(laptop.displayInfo());
        System.out.println(laptop);          // toString auto-generated

        // Testing validation
        try {
            Product invalid = new Product("Invalid", -10);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Record implementing interface
        User user = new User("Alice", 101);
        System.out.println("User: " + user.name() + ", ID: " + user.id());

        // equals() and hashCode() auto-generated
        User user2 = new User("Alice", 101);
        System.out.println("Is user equal user2 ? " + user.equals(user2));
        System.out.println("user hashcode : "+user.hashCode());
        System.out.println("user2 hashcode : "+user2.hashCode());
    }
}
