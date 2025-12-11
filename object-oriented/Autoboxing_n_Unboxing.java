// Autoboxing and Unboxing (Java's Bridge)
// To make programming easier, Java introduced autoboxing (automatically converting a 
// primitive to its wrapper object) and unboxing (converting a wrapper object back to its primitive).

public class AutoboxingDemo {
    public static void main(String[] args) {
        // Autoboxing: int (primitive) is automatically converted to Integer (object)
        Integer objI = 100; 

        // Unboxing: Integer (object) is automatically converted back to int (primitive)
        int primitiveI = objI; 

        // This feature helps Java feel more OO, but it's just syntactic sugar 
        // built on top of the underlying primitive/object distinction.
    }
}
