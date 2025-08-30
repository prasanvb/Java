package course4.L1_Strings;

public class StringClass {
    public static void main(String[] args) {
        // String creation using 'new' keyword
        String s1 = new String("Welcome");
        String s2 = new String("Welcome");
        System.out.println("s1 == s2 ? " + (s1 == s2)); // false (different objects)

        // String creation using literals
        String s3 = "Welcome";
        String s4 = "Welcome";
        System.out.println("s3 == s4 ? " + (s3 == s4)); // true (same object from pool)

        // Demonstrating immutability
        String s5 = "Hello";
        s5.concat(" World"); // creates new object, but not reassigned
        System.out.println("s5 after concat, but not reassigned: " + s5); // Hello

        // Correct reassignment
        s5 = s5.concat(" World");
        System.out.println("s5 after concat and  reassigned: " + s5); // Hello World

        // Using common methods
        String str = " Welcome to Java ";
        System.out.println("Length: " + str.length()); // 16
        System.out.println("Char at 0: " + str.charAt(12)); // ' '
        System.out.println("Trimmed: '" + str.trim() + "'"); // 'Welcome to Java'
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Substring(1,8): " + str.substring(1, 8)); // 'Welcome'
        System.out.println("Replace spaces: " + str.replace(" ", "-"));
    }
}