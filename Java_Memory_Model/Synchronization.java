// Synchronization Enforces Ordering and Visibility
// This demonstrates how a synchronized block guarantees that writes made inside the block are 
// flushed to main memory (visibility) and prevents instruction reordering that would lead to 
// stale data.

class SynchronizationDemo {
    private int data = 0;
    private boolean initialized = false;
    private final Object lock = new Object();

    public void writer() {
        synchronized (lock) { // Lock acquisition establishes happens-before relationship
            // Action A: Writing to 'data' and 'initialized'
            data = 42; 
            initialized = true; 
        } // Lock release makes writes visible
    }

    public void reader() {
        // Without synchronization, the JVM could reorder the read of 'initialized' 
        // before the read of 'data', leading to data=0 being read when initialized=true.
        synchronized (lock) { // Lock acquisition guarantees visibility of writer's changes
            // Action B: Reading 'initialized' and 'data'
            if (initialized) {
                System.out.println("Reader: Data is initialized to " + data);
            } else {
                System.out.println("Reader: Data is not yet initialized.");
            }
        }
    }
}
