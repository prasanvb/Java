package course4.L2_Exceptions;

public class E1_Exception {

    public static void main(String[] args) {
        String[] fruits = {"Banana", "Watermelon", "Apple", "Orange"};

        try {
            // Simulating invalid user input
            int userInput = Integer.parseInt("ABC"); // will cause NumberFormatException
            System.out.println("User picked: " + fruits[userInput - 1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numbers only.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please choose a number between 1 and 4.");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        } finally {
            System.out.println("Finally block always executes.");
        }
    }
}
