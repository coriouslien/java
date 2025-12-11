// Not a Default Behavior (Overridden)
// By default, the equals() method inherited from the base Object class simply performs the exact 
// same reference comparison as ==. 

class SimpleBox {
    int value;
    SimpleBox(int value) {
        this.value = value;
    }
    // .equals() method overridden here.
  @Override
    public boolean equals(Object obj) {
        if (this == obj) {
          return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
          return false;
        }
        SimpleBox other = (SimpleBox) obj;
        return this.value == other.value;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}

public class DefaultEqualsDemo {
    public static void main(String[] args) {
        SimpleBox boxA = new SimpleBox(100);
        SimpleBox boxB = new SimpleBox(100); // Different object instance, same value

        System.out.println("Reference comparison (boxA == boxB):");
        // Output: false (They are different objects in memory)
        System.out.println(boxA == boxB); 

        System.out.println("\nDefault .equals() comparison (boxA.equals(boxB)):");
        // Output: false (Default equals() acts exactly like ==)
        System.out.println(boxA.equals(boxB));
    }
}
