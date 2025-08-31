package course4.L2_Exceptions;

public class E3_ThrowsException {

    static void validateAge(int age) throws IllegalArgumentException {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or above.");
        }
        System.out.println("Valid age!");
    }

    public static void main(String[] args) {
        try {
            validateAge(15); // will throw exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
