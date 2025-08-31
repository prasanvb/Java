package course4.L2_Exceptions;

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class E4_CustomException {
    static void checkValue(int num) throws MyException {
        if (num == 0) {
            throw new MyException("Zero is not allowed!");
        }
        System.out.println("Valid number: " + num);
    }

    public static void main(String[] args) {
        try {
            checkValue(0);
        } catch (MyException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        }
    }
}
