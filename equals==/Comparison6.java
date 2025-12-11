// Overridden Behavior (Custom Comparison Logic)
// For classes where you need to define what "equality" means based on internal data 
// (like String, Integer, or custom objects), you must override the .equals() method. 

class CustomBox {
    int value;

    CustomBox(int value) {
        this.value = value;
    }

    // Override the equals method to compare the 'value' field, not the reference
    @Override
    public boolean equals(Object obj) {
        // Optimization: if they are the exact same object reference, they must be equal
        if (this == obj) {
            return true;
        }
        // Check if the object is null or not of the same class type
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Cast the object to the correct type
        CustomBox otherBox = (CustomBox) obj;
        // Compare internal fields (content)
        return this.value == otherBox.value;
    }

    // Good practice: When overriding equals(), you must also override hashCode()
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}

public class OverriddenEqualsDemo {
    public static void main(String[] args) {
        CustomBox boxC = new CustomBox(500);
        CustomBox boxD = new CustomBox(500); // Different object instance, same value

        System.out.println("Reference comparison (boxC == boxD):");
        // Output: false (Still different objects in memory)
        System.out.println(boxC == boxD);

        System.out.println("\nOverridden .equals() comparison (boxC.equals(boxD)):");
        // Output: true (We customized .equals() to check the internal 'value')
        System.out.println(boxC.equals(boxD));
    }
}
