package course1;

public class L4_DefaultValues {
    // Instance variables (get default values if not initialized)
    int num;
    boolean flag;
    float decimal;
    char letter;
    String text; // reference type

    public static void main(String[] args) {
        // Local variable (must be initialized before use)
        // int localNum;
        // System.out.println(localNum); // ❌ Error: not initialized

        L4_DefaultValues obj = new L4_DefaultValues();

        // Printing default values of instance variables
        System.out.println("Default int: " + obj.num);       // 0
        System.out.println("Default boolean: " + obj.flag);  // false
        System.out.println("Default float: " + obj.decimal); // 0.0
        System.out.println("Default char: " + obj.letter + "'"); // '\u0000'
        System.out.println("Default String: " + obj.text);   // null
    }
}
