package course1;

public class L7_ScopeOfVariablesDemo {

    // Class (static) variable: shared across all instances
    private static int staticCounter = 0;

    // Instance variable: one per object
    private final int instanceId;

    public L7_ScopeOfVariablesDemo(int id) {
        this.instanceId = id;
        staticCounter++; // All instances see the same staticCounter
    }

    void demonstrateLocalAndBlockScope() {
        int local = 42; // Local variable: exists only during this method call
        System.out.println("[method] local = " + local);

        if (local > 0) {
            boolean inBlock = true; // Block variable: exists only inside this if block
            System.out.println("[block] inBlock = " + inBlock);
        }

        // ❌ Would not compile: "cannot find symbol"
        // System.out.println(inBlock);
    }

    static void printStaticState() {
        System.out.println("[static] staticCounter = " + staticCounter);
        // ❌ instanceId not accessible here
    }

    public static void main(String[] args) {
        L7_ScopeOfVariablesDemo.printStaticState();

        L7_ScopeOfVariablesDemo a = new L7_ScopeOfVariablesDemo(1);
        L7_ScopeOfVariablesDemo b = new L7_ScopeOfVariablesDemo(2);

        System.out.println("a.instanceId = " + a.instanceId);
        System.out.println("b.instanceId = " + b.instanceId);

        L7_ScopeOfVariablesDemo.printStaticState(); // static is shared

        a.demonstrateLocalAndBlockScope();

        System.out.println("Static via class: " + L7_ScopeOfVariablesDemo.staticCounter);
    }
}
