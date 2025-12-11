// Comparing Strings
// This example uses the Java String pool behavior and new String() to illustrate memory 
// differences. 

public class ReferenceEqualityDemo {
    public static void main(String[] args) {
        // s1 and s2 point to the same String literal in the String pool
        String s1 = "Java";
        String s2 = "Java";
        
        // s3 is a new object created explicitly on the heap, 
        // even though its content is the same
        String s3 = new String("Java");

        System.out.println("Comparing String literals (s1 == s2):");
        // Output: true (same memory location in the String pool)
        System.out.println(s1 == s2); 

        System.out.println("\nComparing literal with new object (s1 == s3):");
        // Output: false (different memory locations/references)
        System.out.println(s1 == s3); 

        // Note: For content comparison, Java Strings override .equals()
        System.out.println("\nComparing content with .equals() (s1.equals(s3)):");
        // Output: true (content is the same)
        System.out.println(s1.equals(s3)); 
    }
}
