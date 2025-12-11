// Polymorphism Failure
// Primitives cannot participate in polymorphism, which is a cornerstone of object-oriented design.

import java.util.List;
import java.util.ArrayList;

class Shape {
    public void draw() {
        System.out.println("Drawing a shape.");
    }
}
class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle.");
    }
}

public class PolymorphismDemo {
    public static void processObjects(List<Object> objects) {
        for (Object obj : objects) {
            // Polymorphism: We can treat all objects uniformly
            if (obj instanceof Shape) {
                ((Shape) obj).draw();
            } else if (obj instanceof Integer) {
                System.out.println("Processing object: " + obj);
            }
        }
    }

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        
        // 1. Objects participate in the system
        objectList.add(new Circle());
        objectList.add(new Shape());
        objectList.add(Integer.valueOf(50)); // The object wrapper for 50

        System.out.println("--- Polymorphism (Object) Success ---");
        processObjects(objectList);
        
        // 2. Primitives cannot be stored in a collection of objects directly 
        //    without being wrapped (autoboxing handles this automatically in modern Java,
        //    but the underlying List only accepts the Integer object).
        
        // int primitiveValue = 100;
        // objectList.add(primitiveValue); // This actually adds Integer.valueOf(100)
    }
}
