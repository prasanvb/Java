package course1;

public class L7_ScopeOfVar {

    // Class (static) variable: shared across all instances
    private static int staticCounter = 0;

    // Instance variable: one per object
    private final int instanceId;

    public L7_ScopeOfVar(int id) {
        this.instanceId = id;
        staticCounter++; // All instances see the same staticCounter
    }

    static void printStaticState() {
        System.out.println("[static] staticCounter = " + staticCounter);

        // ❌  Would not compile: instanceId not accessible
        // System.out.println("instanceId "+ instanceId);
    }

    void localAndBlockScope() {
        int local = 42; // Local variable: exists only during this method call
        System.out.println("[method] local = " + local);

        if (local > 0) {
            boolean inBlock = true; // Block variable: exists only inside this if block
            System.out.println("[block] inBlock = " + inBlock);
        }

        // ❌ Would not compile: "cannot find symbol"
        // System.out.println(inBlock);
    }


    public static void main(String[] args) {
        L7_ScopeOfVar.printStaticState(); // static before  instance call

        L7_ScopeOfVar a = new L7_ScopeOfVar(1);
        L7_ScopeOfVar b = new L7_ScopeOfVar(2);

        System.out.println("a.instanceId = " + a.instanceId);
        System.out.println("b.instanceId = " + b.instanceId);

        L7_ScopeOfVar.printStaticState(); // static after instance call

        a.localAndBlockScope();

        System.out.println("Static accessed via class directly: " + L7_ScopeOfVar.staticCounter);
    }
}
