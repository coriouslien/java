import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class StreamsDemo {
    public static void streamAndParallelDemo() {
        System.out.println("\n--- Streams API Demo ---");
        List<String> words = Arrays.asList("apple", "banana", "kiwi", "grape", "avocado", "orange");

        // Sequential Stream: Intermediate and Terminal Operations
        List<String> filteredList = words.stream()
            .filter(w -> w.length() > 4) // Intermediate: Keeps words longer than 4 chars
            .map(String::toUpperCase)    // Intermediate: Converts to uppercase
            .sorted()                    // Intermediate: Sorts the results
            .collect(Collectors.toList()); // Terminal: Collects results into a new List

        System.out.println("Sequential Stream Result: " + filteredList); // [APPLE, AVOCADO, BANANA, GRAPE, ORANGE]

        // Parallel Stream Example
        long sum = Arrays.asList(1, 5, 20, 50, 100, 200).parallelStream()
            .mapToLong(i -> {
                // Simulate CPU-intensive work and print the thread to show parallel execution
                // System.out.println("Processing " + i + " on " + Thread.currentThread().getName());
                return i * 2;
            })
            .sum(); // Terminal: Returns the sum of the resulting longs

        System.out.println("Parallel Stream Sum (doubled): " + sum); // 752
    }
}
