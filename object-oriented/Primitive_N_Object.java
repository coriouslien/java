// Code Examples Demonstrating the Difference
// The following examples highlight the functional and conceptual differences between the 
// primitive int and its corresponding wrapper object, Integer.

// Example 1: Methods and Nullability
// Objects support methods and can be null; primitives do not.

public class PrimitiveVsObjectDemo {
    public static void main(String[] args) {
        // --- 1. Primitive Type (int) ---
        int primitiveInt = 10;
        
        // Error: Primitives do not have methods.
        // primitiveInt.toString(); 

        // Primitives cannot be null.
        // int nullInt = null; // Compilation Error
        System.out.println("Primitive int value: " + primitiveInt);

        
        // --- 2. Object Type (Integer) ---
        Integer objectInteger = new Integer(10); // Or Integer objectInteger = 10; (Autoboxing)
        
        // Objects support methods inherited from java.lang.Object
        String strValue = objectInteger.toString(); 
        System.out.println("Object Integer method call: " + strValue);
        
        // Objects can be null.
        Integer nullObject = null; 
        System.out.println("Object can be null: " + nullObject); 
        
        // Warning/Error: Attempting to use a null object will cause a NullPointerException
        // System.out.println(nullObject.toString()); 
    }
}
