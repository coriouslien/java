// Custom Class Comparison (Overriding .equals())
// In a custom class, if you do not override .equals(), its default behavior 
// (inherited from Object) is identical to ==. We must override it to compare content.

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Default equals() from Object would compare references (memory addresses).
    // We override it to compare content (x and y values).
    @Override
    public boolean equals(Object obj) {
        // 1. Check if the objects are the same instance (optimization)
        if (this == obj) {
            return true;
        }
        
        // 2. Check for null or incompatible type
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        // 3. Cast and compare fields (content)
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    // NOTE: When overriding equals(), you MUST also override hashCode()
    @Override
    public int hashCode() {
        // Example hash calculation using Java's built-in utility
        return java.util.Objects.hash(x, y);
    }
}

public class CustomComparisonDemo {
    public static void main(String[] args) {
        // p1 and p2 have the same content but are separate objects on the heap
        Point p1 = new Point(5, 10); 
        Point p2 = new Point(5, 10);
        // p3 is a reference to the same object as p1
        Point p3 = p1; 

        System.out.println("\n--- 2. Custom Class Comparison Demo ---");

        // 1. == (Reference Comparison)
        System.out.println("p1 == p2 (Different Objects): " + (p1 == p2)); // False
        System.out.println("p1 == p3 (Same Object Reference): " + (p1 == p3)); // True
        
        // 2. .equals() (Content Comparison)
        System.out.println("p1.equals(p2) (Same Content): " + p1.equals(p2)); // True (because we overrode equals)
        System.out.println("p1.equals(p3): " + p1.equals(p3)); // True
    }
}
