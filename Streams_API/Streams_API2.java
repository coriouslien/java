import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaStreamExample {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");

        // 1. Filtering and Mapping
        // Filter names starting with 'A' and convert them to uppercase
        List<String> filteredAndMappedNames = names.stream()
                                                    .filter(name -> name.startsWith("A")) // Intermediate operation: filter
                                                    .map(String::toUpperCase)             // Intermediate operation: map
                                                    .collect(Collectors.toList());        // Terminal operation: collect
        System.out.println("Filtered and Mapped Names: " + filteredAndMappedNames); // Output: [ALICE]

        // 2. Sorting and Limiting
        // Sort names alphabetically and take the first 3
        List<String> sortedAndLimitedNames = names.stream()
                                                    .sorted()       // Intermediate operation: sorted
                                                    .limit(3)       // Intermediate operation: limit
                                                    // Terminal operation: collect
                                                    .collect(Collectors.toList());  
        // Output: [Alice, Bob, Charlie]
        System.out.println("Sorted and Limited Names: " + sortedAndLimitedNames);

        // 3. Counting elements
        // Count names longer than 4 characters
        long longNamesCount = names.stream()
                                    // Intermediate operation: filter
                                    .filter(name -> name.length() > 4) 
                                    .count();         // Terminal operation: count
        System.out.println("Number of long names: " + longNamesCount); // Output: 4

        // 4. Reducing elements
        // Concatenate all names into a single string
        String concatenatedNames = names.stream()
                                        // Terminal operation: reduce
                                        .reduce("", (accumulator, name) -> accumulator + name + " "); 
        // Output: Alice Bob Charlie David Eve Frank
        System.out.println("Concatenated Names: " + concatenatedNames.trim()); 

        // 5. Grouping elements
        // Group names by their first letter
        Map<Character, List<String>> namesByFirstLetter = names.stream()
                                    // Terminal operation: collect with groupingBy                              
                                    .collect(Collectors.groupingBy(name -> name.charAt(0)));
        // Output: {A=[Alice], B=[Bob], C=[Charlie], D=[David], E=[Eve], F=[Frank]}
        System.out.println("Names Grouped by First Letter: " + namesByFirstLetter); 

        // 6. Parallel Stream Example
        // Filter names in parallel
        List<String> parallelFilteredNames = names.parallelStream()
                                                    .filter(name -> name.contains("e"))
                                                    .collect(Collectors.toList());
        System.out.println("Parallel Filtered Names (containing 'e'): " + parallelFilteredNames); // Output: [Alice, Charlie, Eve]
    }
}
