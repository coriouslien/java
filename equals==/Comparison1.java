// String Comparison (Pre-Overridden)
// The java.lang.String class is a crucial example because it overrides the .equals() method to 
// compare the character sequences, while == still compares the references.

public class StringComparisonDemo {
    public static void main(String[] args) {
        // Case A: Identical literals (JVM optimization uses the String pool)
        String s1 = "Hello";
        String s2 = "Hello";
        
        // Case B: Explicitly creating a new object on the heap
        String s3 = new String("Hello"); 

        System.out.println("--- 1. String Comparison Demo ---");
        
        // 1. == (Reference Comparison)
        System.out.println("s1 == s2 (Literals): " + (s1 == s2)); // True (Same object in the pool)
        // False (Different objects on the heap)
        System.out.println("s1 == s3 (Literal vs New): " + (s1 == s3)); 
        
        // 2. .equals() (Content Comparison)
        System.out.println("s1.equals(s2): " + s1.equals(s2)); // True (Content is identical)
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // True (Content is identical)
    }
}
