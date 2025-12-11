// Primitive vs. Object
// This example demonstrates how a primitive int cannot have methods called on it, whereas an 
// Integer object can. 
public class OopExample {

    public static void main(String[] args) {

        // --- 1. Using a Primitive Type ---
        int primitiveInt = 42;

        System.out.println("Primitive int value: " + primitiveInt);

        // ERROR: Cannot call methods on a primitive type
        // primitiveInt.toString(); // This line would cause a compilation error
        // System.out.println(primitiveInt.getClass()); // Also an error

        // --- 2. Using an Object (Wrapper Class) ---
        Integer objectInt = 42; // Autoboxing converts the primitive 42 into an Integer object

        System.out.println("Object Integer value: " + objectInt);

        // SUCCESS: Can call methods on the object
        String intAsString = objectInt.toString();
        System.out.println("Object toString() method output: " + intAsString);

        // SUCCESS: Can use static methods on the class as well
        int parsedInt = Integer.parseInt("100");
        System.out.println("Using static parse method: " + parsedInt);

        // You can also compare objects to null, but not primitives
        Integer nullableInt = null;
        System.out.println("Object can be null: " + nullableInt);

        // ERROR: Primitives cannot be null
        // int anotherPrimitive = null; // This line would cause a compilation error
    }
}
