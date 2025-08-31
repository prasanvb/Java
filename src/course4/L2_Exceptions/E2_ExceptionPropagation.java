package course4.L2_Exceptions;

public class E2_ExceptionPropagation {

    static void methodC() {
        int x = 5/0; // ArithmeticException
    }

    static void methodB() {
        methodC();
    }

    static void methodA() {
        try {
            methodB();
        } catch (ArithmeticException e) {
            System.out.println("Exception handled in methodA");
        }
    }

    public static void main(String[] args) {
        methodA();
    }
}