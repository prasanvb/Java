package course3.L5_Encapsulation;

public class L5_Encapsulation {

    public static void main(String[] args) {
        // Creating Person objects
        Person mia = new Person();
        mia.setName("Mia");        // using setter
        mia.setAge(40);
        mia.setGender("Female");
        mia.sleepHours();          // behavior depends on encapsulated variable

        mia.setName("1234");       //  ⚠️ Returns "Invalid name! Only alphabets allowed."
        System.out.println("Name: " + mia.getName());


        Person james = new Person();
        james.setName("James");
        james.setAge(3);
        james.setGender("Male");
        james.sleepHours();

        james.setGender("Unknown"); //  ⚠️ Returns "Invalid gender"
        System.out.println("Name: " + james.getName());



    }
}