package course4.L3_ReferenceMethod;

import java.util.Arrays;
import java.util.List;

public class ReferenceMethod {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");

        // Using lambda expression
        numbers.stream()
                .map(numString -> Integer.parseInt(numString))
                .forEach(num -> System.out.println("Using Lambda :"+num));

        // Using method reference (more concise)
        String prefix = "Reference method: ";
        numbers.stream()
                .map(Integer::parseInt)  // Static method reference
                .map(String::valueOf)  // Static method reference
                .map(prefix::concat)  // Instance method reference
                .map(String::toUpperCase)
                .forEach(System.out::println); // Instance method reference
    }
}
