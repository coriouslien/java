import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadExample {
    public static void main(String[] args) {
        // Create an ExecutorService that uses virtual threads
        // Executors.newVirtualThreadPerTaskExecutor() creates an ExecutorService that spawns 
        // a new virtual thread for each submitted task, demonstrating the ease of creating and 
        // managing a large number of concurrent operations.
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) { 
            for (int i = 0; i < 10000; i++) { // Can easily handle many more tasks
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Virtual Thread: Task " + taskId + " running on " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100); // Simulate some work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }
}
