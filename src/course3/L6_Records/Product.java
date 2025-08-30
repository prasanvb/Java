package course3.L6_Records;

public record Product(String name, double price) {

    /*
    👉 A record automatically provides:
        - A constructor
        - Accessor methods for its fields
        - Implementations of equals(), hashCode(), and toString()
    */

    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    // Custom method inside record
    public String displayInfo() {
        return name + " costs $" + price;
    }
}
