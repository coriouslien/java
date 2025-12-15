// Abstract class
abstract class Vehicle {
    protected String brand = "Generic Brand";

    void fuelUp() {
        System.out.println("Fueling up the vehicle.");
    }

    public abstract void start();
}

// Concrete class extending the abstract class
class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("The car is starting with a key or button.");
    }
}

class Motorcycle extends Vehicle {
    @Override
    public void start() {
        System.out.println("The motorcycle is starting with a kick start.");
    }
}

// Main class to run the example
public class AbstractClassDemo {
    public static void main(String[] args) {
        Car myCar = new Car();
        System.out.println(myCar.brand);
        myCar.fuelUp();
        myCar.start();
        
        System.out.println();

        Motorcycle myMotorcycle = new Motorcycle();
        System.out.println(myMotorcycle.brand);
        myMotorcycle.fuelUp();
        myMotorcycle.start();
    }
}
