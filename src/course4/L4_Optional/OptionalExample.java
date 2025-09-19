package course4.L4_Optional;


import java.util.Optional;
import java.util.List;
import java.util.Arrays;

public class OptionalExample {

    static List<User> users = Arrays.asList(
            new User(1, "Alice", true),
            new User(2, "Bob", false)
    );


    // ✅ Good: Using Optional for return type that might be absent
    static Optional<User> findUserById(int id) {
        // Simulate database lookup
        return users.stream()
                .filter(user -> user.id() == id)
                .findFirst();
    }

    // ✅ Good: Method chaining with transformations
    static String getUserDisplayName(int id) {
        return findUserById(id)
                .filter(User::active)           // Only active users .filter(User -> User.active() == true)
                .map(User::name)               // Transform to name  .map(User -> User.name())
                .map(name -> "User: " + name)     // Add prefix
                .orElse("Guest User");            // Default value
    }

    // ✅ Good: Using Optional with exception handling
    static User getActiveUserById(int id) {
        return findUserById(id)
                .filter(User::active)
                .orElseThrow(() -> new UserNotFoundException("Active user not found with id: " + id));
    }

    public static void main(String[] args) {
        // Case 1: User exists and is active
        System.out.println(getUserDisplayName(1)); // "User: Alice"

        // Case 2: User exists but is inactive
        System.out.println(getUserDisplayName(2)); // "Guest User"

        // Case 3: User doesn't exist
        System.out.println(getUserDisplayName(3)); // "Guest User"

        // Case 4: Exception handling
        try {
            User user = getActiveUserById(2);
            System.out.println(user);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage()); // "Active user not found with id: 2"
        }
    }
}

// Supporting classes
record User (int id, String name, boolean active){
}

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
