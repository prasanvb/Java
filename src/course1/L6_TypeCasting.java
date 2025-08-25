package course1;

public class L6_TypeCasting {
    public static void main(String[] args) {
        // Implicit casting (widening)
        int inty = 100;
        long longy = inty; // no explicit cast needed
        System.out.println("Implicit cast int to long: " + longy);

        // Explicit casting (narrowing)
        float f = 45.67f;
        int truncated = (int) f; // fraction lost
        System.out.println("Explicit cast float to int: " + truncated);

        // Special case with byte
        byte x = 10;
        byte y = 20;

        // Compiler knows at compile-time: 10 + 20 = 30 (fits in byte)
        byte z1 = 10 + 20;
        System.out.println("z1 = " + z1);

        // Runtime expression: must explicitly cast
        byte z2 = (byte) (x + y);
        System.out.println("z2 = " + z2);
    }
}
