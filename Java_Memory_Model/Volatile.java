// volatile Solves Visibility Issues
// This example shows how a variable not declared volatile can cause the reader thread to miss an 
// update, whereas the volatile variable works correctly.

class VisibilityDemo {
    // Shared state variables
    private boolean simpleReady = false;
    private volatile boolean volatileReady = false; // JMM guarantees visibility

    public void writerThread() {
        System.out.println("Writer: Starting...");
        // Write #1 to the shared variable
        simpleReady = true; 
        // Write #2 to the volatile variable
        volatileReady = true; 
        System.out.println("Writer: Set both flags to true.");
    }

    public void simpleReaderThread() {
        System.out.println("Simple Reader: Waiting for simpleReady...");
        // Without 'volatile', this loop might run forever if the initial 'false' 
        // value is cached in the CPU register/local cache.
        while (!simpleReady) {
            // Spinning... (May not terminate)
        }
        System.out.println("Simple Reader: Detected simpleReady is true.");
    }
    
    public void volatileReaderThread() {
        System.out.println("Volatile Reader: Waiting for volatileReady...");
        // JMM guarantees that the latest write will be flushed and visible here.
        while (!volatileReady) {
            // Spinning... (Guaranteed to terminate quickly)
        }
        System.out.println("Volatile Reader: Detected volatileReady is true.");
    }
}

public class JMMExample {
    public static void main(String[] args) throws InterruptedException {
        VisibilityDemo demo = new VisibilityDemo();

        // Start the writer thread
        Thread writer = new Thread(demo::writerThread);
        
        // Start the volatile reader (This works reliably)
        Thread volatileReader = new Thread(demo::volatileReaderThread);
        
        // Start the simple reader (This is unreliable and may hang)
        // Thread simpleReader = new Thread(demo::simpleReaderThread); 

        volatileReader.start();
        // simpleReader.start(); // Uncomment this line to see the potential bug
        
        // Wait a moment, then start the writer
        Thread.sleep(500); 
        writer.start();

        writer.join();
        volatileReader.join();
        
        System.out.println("\nProgram finished. Volatile mechanism proved reliable.");
    }
}
