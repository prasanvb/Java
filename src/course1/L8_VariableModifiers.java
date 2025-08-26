package course1;

// File: L8_VariableModifiers.java
public class L8_VariableModifiers {

    // ---------------- Field Modifiers ----------------

    public static int population = 0;   // static: shared across all objects
    private final String id;            // final: must be set once, cannot be changed
    protected int age;                  // protected: accessible in package + subclasses
    String nickname;                    // default (package-private): accessible in package
    private transient String secret;    // transient: ignored in serialization (concept only)

    // ---------------- Constructor ----------------

    public L8_VariableModifiers(String id, String secret, int age, String nickname) {
        this.id = id;
        this.secret = secret;
        this.age = age;
        this.nickname = nickname;
        population++; // static counter increases each time an object is created
    }

    // ---------------- Simple Methods ----------------

    @Override
    public String toString() {
        return "L8_VariableModifiers{" +
                "id='" + id + '\'' +
                ", age=" + age +
                ", nickname='" + nickname + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }

    // ---------------- Main ----------------

    public static void main(String[] args) {
        L8_VariableModifiers p1 = new L8_VariableModifiers("A001", "p@ss", 25, "Ace");
        L8_VariableModifiers p2 = new L8_VariableModifiers("A002", "hidden", 30, "Bolt");

        System.out.println("Population (static, shared) = " + L8_VariableModifiers.population);

        System.out.println(p1); // uses toString()
        System.out.println(p2);

        // final: id cannot be changed
        // p1.id = "NEW"; // ❌ compile error

        // static: same counter for all objects
        System.out.println("Population via p1 = " + p1.population);
        System.out.println("Population via p2 = " + p2.population);

        // local variable rules
        final int local = 10; // ✅ allowed
        System.out.println("final local = " + local);

        // ❌ Illegal on locals (uncomment to test):
        // public int x = 1;
        // static int y = 2;
        // transient int z = 3;
    }
}
