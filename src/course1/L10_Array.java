package course1;

public class L10_Array {
    public static void main(String[] args) {
        // 1. Creating an int array of fixed size 3
        int[] myArray = new int[3]; // default values = {0,0,0}

        // Assign values
        myArray[0] = 4;
        myArray[1] = 7;
        myArray[2] = 10;

        // Access elements
        System.out.println("Second element: " + myArray[1]); // prints 7
        System.out.println("Array length: " + myArray.length);

        // Iterate using for-each
        System.out.println("Array elements:");
        for (int element : myArray) {
            System.out.println(element);
        }

        // 2. Array initialization shortcut
        int[] numbers = {2, 4, 6, 1, 3};
        System.out.println("First element of numbers: " + numbers[0]);

        // 3. Array of objects
        Person[] users = new Person[3]; // array can hold 3 Person objects

        // Create Person objects
        Person alex = new Person("Alex", 55, Gender.MALE);
        Person mia = new Person("Mia", 40, Gender.FEMALE);
        Person james = new Person("James", 3, Gender.MALE);

        // Store objects in array
        users[0] = alex;
        users[1] = mia;
        users[2] = james;

        // Access object attribute via array
        System.out.println("James' age: " + users[2].age);
    }
}
