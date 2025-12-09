// Example 2: CountDownLatch and Semaphore
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

class SynchronizationDemo {
    // CountDownLatch: Wait for 3 threads to finish preparation
    private final CountDownLatch latch = new CountDownLatch(3);
    // Semaphore: Limit concurrent access to 2 resources
    private final Semaphore semaphore = new Semaphore(2);

    public void runDemo() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Latch Example: Workers prepare, Main thread waits for all.
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is preparing...");
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException ignored) {}
                latch.countDown(); // Decrement the latch count
                System.out.println(Thread.currentThread().getName() + " finished preparation. Count: " + latch.getCount());
            });
        }

        System.out.println("Main thread waiting for all workers to prepare...");
        latch.await(); // Main thread blocks until count reaches zero

        System.out.println("\nAll workers prepared. Starting final operation.");
        
        // Semaphore Example: Only 2 threads can access the critical section (resource) at a time.
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                try {
                    semaphore.acquire(); // Blocks if 2 permits are already taken
                    System.out.println(Thread.currentThread().getName() + " acquired resource permit.");
                    Thread.sleep(200); // Simulate using the resource
                } catch (InterruptedException ignored) {
                } finally {
                    semaphore.release(); // Releases the permit
                    System.out.println(Thread.currentThread().getName() + " released resource permit.");
                }
            });
        }
        
        executor.shutdown();
    }
}
