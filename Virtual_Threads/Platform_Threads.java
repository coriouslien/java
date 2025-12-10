import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlatformThreadExample {
    public static void main(String[] args) {
        // Limited number of platform threads
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) { 
            for (int i = 0; i < 100; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Platform Thread: Task " + taskId + " running on " + 
                                       Thread.currentThread().getName());
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
