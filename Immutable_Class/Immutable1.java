import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * An example of a thread-safe, immutable class.
 */
public final class ImmutableStudent {

    // Rule 2: All fields are private and final
    private final int id;
    private final String name;
    private final Date enrollmentDate;
    private final List<String> courses; // A mutable object reference!

    // Rule 4 & 5 (Constructor with Defensive Copying)
    public ImmutableStudent(int id, String name, Date enrollmentDate, List<String> courses) {
        // Rule 4: Initialize all state in the constructor
        this.id = id;
        this.name = name;

        // Rule 5 (Defensive Copying - Date): Create a deep copy of the mutable Date object 
        // to prevent external modification of the internal state.
        this.enrollmentDate = new Date(enrollmentDate.getTime());

        // Rule 5 (Defensive Copying - List): Create a deep copy of the mutable List object.
        this.courses = new ArrayList<>(courses);
    }

    // Rule 3: No setter methods are provided

    // Getters for Immutability (Simple Fields)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Rule 5 (Defensive Copying - Date Getter): Return a deep copy
    public Date getEnrollmentDate() {
        return new Date(this.enrollmentDate.getTime());
    }

    // Rule 5 (Defensive Copying - List Getter): Return a deep copy
    public List<String> getCourses() {
        // Return a copy of the list so external changes don't affect our internal list
        return new ArrayList<>(this.courses);
    }

    @Override
    public String toString() {
        return "ImmutableStudent [id=" + id + ", name=" + name + ", courses=" + courses + 
               ", enrolled=" + enrollmentDate.toInstant() + "]";
    }

    public static void main(String[] args) {
        // State preparation (mutable data)
        Date originalDate = new Date();
        List<String> originalCourses = new ArrayList<>(List.of("Math", "Physics"));

        // 1. Creation: The object is constructed and state is fixed
        ImmutableStudent student = new ImmutableStudent(101, "Alex", originalDate, originalCourses);
        System.out.println("Original Student: " + student);
        
        // --- Attempting to violate immutability ---

        // A. Attack via Constructor Input (Mutation of original mutable objects)
        originalDate.setTime(0); // Mutate the original Date object passed to the constructor
        originalCourses.add("Chemistry"); // Mutate the original List object passed to the constructor

        // The student object remains unchanged due to defensive copying in the constructor!
        System.out.println("\nAfter Mutating Constructor Inputs:");
        System.out.println("Original Student (Unchanged): " + student); 
        
        // B. Attack via Getter Output (Mutation of returned mutable objects)
        Date returnedDate = student.getEnrollmentDate();
        returnedDate.setTime(0); // Mutate the returned copy

        List<String> returnedCourses = student.getCourses();
        returnedCourses.add("Art"); // Mutate the returned copy

        // The student object remains unchanged due to defensive copying in the getter!
        System.out.println("\nAfter Mutating Getter Outputs:");
        System.out.println("Original Student (Unchanged): " + student); 
        
        // Note: The date string printed will show the original time, not the mutated time (time 0).
    }
}
