package course2;

public class L2_Instanceof {
    public static void main(String[] args) {
        // Example 1: Traditional instanceof before Java 16
        Object obj1 = "Hello, Java";
        if (obj1 instanceof String) {
            String s = (String) obj1; // manual cast
            System.out.println("Traditional: " + s.toUpperCase());
        }

        // Example 2: Pattern matching for instanceof (Java 16+)
        Object obj2 = "Pattern Matching";
        if (obj2 instanceof String s) { // automatic cast
            System.out.println("Pattern Matching: " + s.toLowerCase());
        }

        // Example 3: Null check
        String obj3 = null;
        System.out.println("null instanceof String: " + (obj3 instanceof String));

        // Example 4: Integer check
        Object obj4 = 123;
        if (obj4 instanceof Integer i) {
            System.out.println("Integer value + 10: " + (i + 10));
        }

        // Example 5: Shape hierarchy example
        Shape shape1 = new Circle(5);
        if (shape1 instanceof Circle c) {
            System.out.println("Circle area: " + (Math.PI * c.radius * c.radius));
        } else if (shape1 instanceof Rectangle r) {
            System.out.println("Rectangle area: " + (r.width * r.height));
        }

        Shape shape2 = new Rectangle(4, 6);
        if (shape2 instanceof Circle c) {
            System.out.println("Circle area: " + (Math.PI * c.radius * c.radius));
        } else if (shape2 instanceof Rectangle r) {
            System.out.println("Rectangle area: " + (r.width * r.height));
        }
    }
}

// Sealed interface hierarchy example
sealed interface Shape permits Circle, Rectangle {
}

final class Circle implements Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }
}

final class Rectangle implements Shape {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
