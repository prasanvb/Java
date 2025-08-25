package course1;

public class L5_Assignment {
    public static void main(String[] args) {
        // Boolean literals
        boolean isJavaFun = true;
        System.out.println("Boolean value: " + isJavaFun);

        // Character literals
        char c1 = 'A';        // simple character
        char c2 = '\u0061';   // Unicode for 'a'
        System.out.println("Characters: " + c1 + ", " + c2);

        // String literal (not primitive)
        String greeting = "Hello Java!";
        System.out.println(greeting);

        // Integer literals
        int decimal = 100;     // decimal
        int octal = 0144;      // octal (prefix 0)
        int hex = 0x64;        // hexadecimal (prefix 0x)
        System.out.println("Integers: " + decimal + ", " + octal + ", " + hex);

        // Long literal
        long bigNum = 10000000000L;
        System.out.println("Long value: " + bigNum);

        // Float literal
        float pi = 3.14F;
        System.out.println("Float value: " + pi);

        // Double literal
        double precise = 12345.6789;
        System.out.println("Double value: " + precise);
    }
}
