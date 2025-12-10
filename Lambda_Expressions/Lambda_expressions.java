import java.util.Optional;
import java.util.function.Consumer;

class FunctionalFeatures {
    public static void lambdaAndOptionalDemo() {
        System.out.println("--- Lambda & Optional Demo ---");
        
        // 1. Lambda Expression (Implementing the Consumer functional interface)
        Consumer<String> printer = message -> 
            System.out.println("Lambda Output: " + message);

        printer.accept("Hello, Functional World!");
        
        // 2. Optional Usage
        String name = "Alice";
        Optional<String> optionalName = Optional.ofNullable(name);

        // Safe access using map/orElse
        String result = optionalName
            .map(String::toUpperCase) // If present, convert to uppercase
            .orElse("Guest");        // If not present (null), use "Guest"

        System.out.println("Optional Result: " + result);

        // Example of handling absence:
        Optional<String> absentName = Optional.ofNullable(null);
        absentName.ifPresentOrElse(
            n -> System.out.println("Present: " + n),
            () -> System.out.println("Absent: Name not provided.")
        );
    }
}
