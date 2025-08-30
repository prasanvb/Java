package course4.L1_Strings;

public class StringBufferBuilder {
    public static void main(String[] args) {
        // Using StringBuffer
        StringBuffer sb1 = new StringBuffer("Welcome to Java");
        sb1.append("!");
        System.out.println("StringBuffer Append: " + sb1); // Welcome to Java!

        sb1.delete(6, 10); // deletes "to"
        System.out.println("After delete: " + sb1); // Welcom Java!

        sb1.insert(7, "Amazing ");
        System.out.println("After insert: " + sb1); // Welcom Amazing Java!

        // Using StringBuilder
        StringBuilder sb2 = new StringBuilder("Hello");
        sb2.append(" World");
        System.out.println("StringBuilder Append: " + sb2); // Hello World
    }
}