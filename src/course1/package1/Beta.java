package course1.package1;

import course1.package2.Delta;
import course1.package2.Gamma;

class Alpha {
    void sayHello(){
        System.out.println("Default: Alpha says hello!");
    }
}

class Zeta {
    private void sayHello(){
        System.out.println("Private: Zeta says hello!");
    }
}

public class Beta extends Delta {
    static class Kappa {  // Made static so it can be instantiated in static main
        private void sayHello(){
            System.out.println("Private: Kappa says hello!");
        }

        // Add a public method to demonstrate we can call private methods within the same class
        public void callPrivateMethod() {
            sayHello(); // This works - calling private method within same class
        }
    }


    public static void main(String[] args) {
        Alpha a = new Alpha();
        a.sayHello();

        Gamma g = new Gamma();
        g.sayHello();

        // Allowed to call own class and instance of it within main method
        Beta d = new Beta();
        d.sayHello();

        Zeta z = new Zeta();
        // Note: not possible to call private methods from outside the class
        // z.sayHello(); // This would cause compilation error

        Kappa k = new Kappa();
        // Cannot call private method directly from outside the class
        // k.sayHello(); // This would cause compilation error

        // But we can call the public method that internally calls the private method
        k.callPrivateMethod();
    }
}
