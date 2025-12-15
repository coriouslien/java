final class Config { // Class cannot be extended
    final static int MAX_ATTEMPTS = 3; // Constant variable

    public final void printConfig() { // Method cannot be overridden
        System.out.println("Max Attempts: " + MAX_ATTEMPTS);
    }
}

class MutableClass {
    final int id; // Must be initialized in constructor

    public MutableClass(int id) {
        this.id = id; // Assignment is allowed only once
    }

    // public void setId(int newId) { this.id = newId; } // Error: Cannot assign final field
}
