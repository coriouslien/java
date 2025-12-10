import java.time.Duration;
import java.util.concurrent.Executors;

class VirtualThreadDemo {
    public static void virtualThreadsDemo() throws InterruptedException {
        System.out.println("\n--- Virtual Threads Demo (Java 21+) ---");
        
        long startTime = System.currentTimeMillis();
        
        Runnable task = () -> {
            // Simulate a blocking I/O operation (e.g., waiting for a database response)
            try {
                Thread.sleep(Duration.ofMillis(10));
            } catch (InterruptedException ignored) {}
            // System.out.println("Task completed on thread: " + Thread.currentThread());
        };

        int NUM_TASKS = 10000;
        
        // 1. Creating Virtual Threads using the factory method  
        // var: see the Virtual_Threads.txt explanation. 
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            
            for (int i = 0; i < NUM_TASKS; i++) {
                executor.submit(task);
            }
            // The executor waits for all submitted tasks to complete before closing.
        } 
        
        long endTime = System.currentTimeMillis();
        
        // Creating 10,000 platform threads would crash or severely strain the system.
        // Creating 10,000 virtual threads is trivial and fast.
        System.out.println("Created and ran " + NUM_TASKS + " virtual threads.");
        System.out.println("Total time: " + (endTime - startTime) + "ms");
    }
}

public class AdvancedJavaFeatures {
    public static void main(String[] args) throws InterruptedException {
        FunctionalFeatures.lambdaAndOptionalDemo();
        StreamsDemo.streamAndParallelDemo();
        
        // This requires Java 21+ to compile and run properly
        VirtualThreadDemo.virtualThreadsDemo(); 
    }
}
