public final class StudentRecord {

    private final int id;
    private final String name;

    /**
     * Parameterized constructor to initialize fields.
     * Fields are final and assigned once.
     */
    public StudentRecord(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for id (safe to return primitive).
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name (safe to return immutable String).
     */
    public String getName() {
        return name;
    }

    // Optional: override toString for better output
    @Override
    public String toString() {
        return "StudentRecord{id=" + id + ", name='" + name + "'}";
    }
    public static void main(String[] args) {
        StudentRecord student = new StudentRecord(101, "Alice");
        System.out.println("Original: " + student);

        // Attempting to change state:
        // student.name = "Bob"; // Compile error: name has private access
        // student.setName("Bob"); // Compile error: no setter method

        // Any perceived "change" must create a new instance (as String does with concat)
        // This is how immutability works: the original object is untouched.
    }
}
