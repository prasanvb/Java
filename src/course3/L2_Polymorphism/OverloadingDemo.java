package course3.L2_Polymorphism;

class Goat {
    private int minimumSpeed;

    public void setMinimumSpeed(int speed) {
        this.minimumSpeed = speed;
    }

    // Base run method
    public void run() {
        System.out.println("I am running at " + minimumSpeed + " km/h.");
    }

    // Overloaded run method
    public String run(boolean hasDanger) {
        if (hasDanger) {
            return "Goat is in danger! Running at " + (minimumSpeed * 2) + " km/h.";
        } else {
            return "Goat is safe. Running at " + minimumSpeed + " km/h.";
        }
    }
}

public class OverloadingDemo {
    public static void main(String[] args) {
        Goat goat = new Goat();
        goat.setMinimumSpeed(24);

        goat.run();  // no parameter
        System.out.println(goat.run(true));   // overloaded with parameter
        System.out.println(goat.run(false));
    }
}