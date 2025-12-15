// Interface (Capability/Contract)
// An interface defines two different capabilities (CanFly and CanSwim) that completely unrelated 
// classes can implement.

// 1. Interface A (Capability: Flying)
interface CanFly {
    // Field is implicitly public static final
    int MAX_ALTITUDE = 5000;

    // Abstract method (no body)
    void fly();

    // Default method (since Java 8, provides a default implementation)
    default void checkAltitude() {
        System.out.println("Current altitude is safe.");
    }
}

// 2. Interface B (Capability: Swimming)
interface CanSwim {
    void swim();
}

// 3. Concrete Class implements multiple interfaces
class Duck implements CanFly, CanSwim {
    @Override
    public void fly() {
        System.out.println("Duck is flying up to " + MAX_ALTITUDE + " feet.");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming on the pond.");
    }
}
