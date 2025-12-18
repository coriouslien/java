// ImmutableEmployee class. Note how we handle the List, which is a mutable object.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Rule 1: Final class
public final class ImmutableEmployee {

    // Rule 2: Private final fields
    private final int id;
    private final String name;
    
    // This is a mutable object, so we must be careful!
    private final List<String> skills;

    // Rule 4: Constructor initializes all fields
    public ImmutableEmployee(int id, String name, List<String> skills) {
        this.id = id;
        this.name = name;

        // Rule 5: Defensive Copying (Construction)
        // We create a new list so that changes to the 'skills' list passed 
        // by the caller do not affect our internal state.
        this.skills = new ArrayList<>(skills);
    }

    // Rule 3: Only Getters, no Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Rule 5: Defensive Copying (Getter)
    public List<String> getSkills() {
        // Return a copy or an unmodifiable view so the caller 
        // cannot add/remove skills from the original object.
        return Collections.unmodifiableList(new ArrayList<>(skills));
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", skills=" + skills + "]";
    }
}

// --- Testing the Immutability ---
class Main {
    public static void main(String[] args) {
        List<String> mySkills = new ArrayList<>();
        mySkills.add("Java");
        mySkills.add("SQL");

        ImmutableEmployee emp = new ImmutableEmployee(1, "Alice", mySkills);
        System.out.println("Original: " + emp);

        // Attempting to modify the original list passed to constructor
        mySkills.add("Python");
        System.out.println("After modifying input list: " + emp); // emp remains unchanged

        // Attempting to modify the list via the getter
        try {
            emp.getSkills().add("C++");
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught expected error: Cannot modify immutable skills list!");
        }
    }
}
