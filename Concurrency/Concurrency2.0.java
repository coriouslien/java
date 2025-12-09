import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // Initialize CountDownLatch with a count of 3, meaning it waits for 3 events.
        CountDownLatch latch = new CountDownLatch(3);

        // Create and start three worker threads
        new Thread(new Worker(latch, "Worker-1")).start();
        new Thread(new Worker(latch, "Worker-2")).start();
        new Thread(new Worker(latch, "Worker-3")).start();

        System.out.println("Main thread waiting for workers to complete initialization...");
        // Main thread waits until the latch's count reaches zero
        latch.await();
        System.out.println("All workers have completed initialization. Main thread can now proceed.");
    }
}

class Worker implements Runnable {
    private final CountDownLatch latch;
    private final String name;

    public Worker(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is starting initialization.");
            Thread.sleep((long) (Math.random() * 2000)); // Simulate work
            System.out.println(name + " has completed initialization.");
            latch.countDown(); // Decrement the latch's count
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
