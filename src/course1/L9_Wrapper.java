package course1;


public class L9_Wrapper {
    public static void main(String[] args) {
        // Manual boxing (old way, deprecated since Java 9)
        // Integer count1 = new Integer(10); // ❌ Deprecated

        // Recommended way - using valueOf()
        Integer count2 = Integer.valueOf(10);

        // Auto-boxing (Java automatically converts primitive to object)
        Integer count3 = 10; // ✅ preferred

        // Auto-unboxing (object → primitive automatically)
        int temp = count3;  // Java converts Integer → int automatically

        // Methods available in Wrapper classes
        System.out.println("Count2 as String: " + count2.toString());  // converts to "10"
        System.out.println("Count3 equals 10? " + count3.equals(10));  // true

        // Wrapper classes useful in collections (cannot use int directly in ArrayList)
        java.util.ArrayList<Integer> numbers = new java.util.ArrayList<>();
        numbers.add(5); // auto-boxing
        numbers.add(15);
        System.out.println("Numbers list: " + numbers);
    }
}