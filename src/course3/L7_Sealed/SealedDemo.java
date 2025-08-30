package course3.L7_Sealed;

public class SealedDemo {
    // Method demonstrating pattern matching with sealed classes
    // Since Shape is sealed, the compiler knows exactly all allowed subclasses
    // → Circle, Rectangle, Square (and their subtypes, depending on sealed rules)
    public static String getShapeInfo(Shape shape) {
        // No default case is needed because the compiler knows all possible subclasses
        return switch (shape) {
            case Circle c -> "It's a Circle";
            case Rectangle r -> "It's a Rectangle";
            case Square s -> "It's a Square";
        };
    }

    // Demonstrating how sealed classes help in controlled inheritance with Payment hierarchy
    public static void printPaymentType(Payment payment) {
        // Using instanceof pattern matching to determine exact type of Payment
        if (payment instanceof CreditCard) {
            System.out.println("Payment via Credit Card");
        } else if (payment instanceof UPI) {
            System.out.println("Payment via UPI");
        } else if (payment instanceof NetBanking) {
            System.out.println("Payment via NetBanking");
        }
    }

    public static void main(String[] args) {
        // ---------------- Working with Shapes ----------------

        // Circle is a final implementation of Shape
        Shape circle = new Circle();
        // Rectangle is sealed, and FilledRectangle is its only permitted subclass
        Shape rectangle = new FilledRectangle();
        // Square is non-sealed, so it can be extended further
        Shape square = new Square();
        // ColorfulSquare is a subclass of Square (possible only because Square is non-sealed)
        Shape colorfulSquare = new ColorfulSquare();

        // Pattern matching with sealed interface Shape
        System.out.println(getShapeInfo(circle));          // Output: It's a Circle
        System.out.println(getShapeInfo(rectangle));       // Output: It's a Rectangle
        System.out.println(getShapeInfo(square));          // Output: It's a Square
        System.out.println(getShapeInfo(colorfulSquare));  // Output: It's a Square (falls under Square branch)

        System.out.println("-----");

        // ---------------- Working with Payments ----------------

        // Payment is a sealed class that only permits CreditCard, UPI, and NetBanking
        Payment p1 = new CreditCard();
        printPaymentType(p1); // Output: Payment via Credit Card

        Payment p2 = new UPI();
        printPaymentType(p2); // Output: Payment via UPI

        Payment p3 = new NetBanking();
        printPaymentType(p3); // Output: Payment via NetBanking
    }
}

// ---------------- Sealed Shape Hierarchy ----------------

// Shape is a sealed interface, restricting its implementations
// Only Circle, Rectangle, and Square can implement Shape
sealed interface Shape permits Circle, Rectangle, Square {
}

// Circle is a final class implementing Shape
// Since it's final, it cannot be subclassed further
final class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

// Rectangle is sealed, which means only specific classes can extend it
// Here, only FilledRectangle is permitted
sealed class Rectangle implements Shape permits FilledRectangle {
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// FilledRectangle is a final class that extends Rectangle
// No further subclassing allowed
final class FilledRectangle extends Rectangle {
    public void draw() {
        System.out.println("Drawing Filled Rectangle");
    }
}

// Square is declared non-sealed, which removes restrictions
// Any other class can now extend Square
non-sealed class Square implements Shape {
    public void draw() {
        System.out.println("Drawing Square");
    }
}

// Example of extending a non-sealed class (Square)
// This is only possible because Square is non-sealed
class ColorfulSquare extends Square {
    public void draw() {
        System.out.println("Drawing Colorful Square");
    }
}

// ---------------- Sealed Payment Hierarchy ----------------

// Payment is a sealed class
// Only CreditCard, UPI, and NetBanking are allowed subclasses
sealed class Payment permits CreditCard, UPI, NetBanking {
}

// CreditCard is final, no further subclassing possible
final class CreditCard extends Payment {
}

// UPI is final, no further subclassing possible
final class UPI extends Payment {
}

// NetBanking is final, no further subclassing possible
final class NetBanking extends Payment {
}
