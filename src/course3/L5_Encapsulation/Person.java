package course3.L5_Encapsulation;

class Person {
    // Step 1: Make variables private
    private String name;
    private int age;
    private String gender;

    // Step 2: Provide public getters and setters
    public String getName() {
        return name;   // Read-only access
    }

    public void setName(String name) {
        // Example validation
        if (name.matches("[a-zA-Z]+")) {
            this.name = name;
        } else {
            System.out.println("Invalid name! Only alphabets allowed.");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be positive!");
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
            this.gender = gender;
        } else {
            System.out.println("Invalid gender!");
        }
    }

    // Example method that uses variables internally
    public void sleepHours() {
        if (age < 12) {
            System.out.println(name + " will sleep more than 12 hours in a day.");
        } else {
            System.out.println(name + " will sleep less than 10 hours in a day.");
        }
    }
}