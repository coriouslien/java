// Abstract Class (Close Family)
// An abstract class defines the blueprint for a hierarchy of vehicles, providing common implemented 
// methods and requiring specific ones.

// 1. Abstract Class (Cannot be instantiated)
abstract class Vehicle {
    // Concrete field (instance state)
    private String engineType;

    // Constructor is defined and used by subclasses
    public Vehicle(String engineType) {
        this.engineType = engineType;
        System.out.println("Vehicle created with engine: " + engineType);
    }

    // Concrete method (Implemented logic shared by all subclasses)
    public void startEngine() {
        System.out.println("Starting the " + engineType + " engine.");
    }

    // Abstract method (Must be implemented by concrete subclasses)
    public abstract void drive();
}

// 2. Subclass extends the abstract class (Single Inheritance)
class Car extends Vehicle {
    public Car() {
        super("Gasoline"); // Calls the abstract class constructor
    }

    @Override
    public void drive() {
        System.out.println("Car is driving on four wheels.");
    }
}
