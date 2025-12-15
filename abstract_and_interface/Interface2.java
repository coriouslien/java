// Interface
interface Flyable {
    String DESCRIPTION = "An object that can fly.";

    void fly();
    
    default void glides() {
        System.out.println("Gliding through the air.");
    }
}

// Class implementing the interface
class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird is flying by flapping wings.");
    }
}

// Another unrelated class implementing the same interface
class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Airplane is flying using jet engines.");
    }
}

// Main class to run the example
public class InterfaceDemo {
    public static void main(String[] args) {
        Bird myBird = new Bird();
        System.out.println(Flyable.DESCRIPTION);
        myBird.fly();
        myBird.glides();

        System.out.println();

        Airplane myAirplane = new Airplane();
        myAirplane.fly();
        myAirplane.glides();
    }
}
