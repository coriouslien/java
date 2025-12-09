import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        // Initialize Semaphore with 2 permits, allowing 2 threads to access concurrently
        Semaphore semaphore = new Semaphore(2);

        // Create and start 5 threads trying to access the "printer"
        for (int i = 1; i <= 5; i++) {
            new Thread(new PrinterUser(semaphore, "User-" + i)).start();
        }
    }
}

class PrinterUser implements Runnable {
    private final Semaphore semaphore;
    private final String name;

    public PrinterUser(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is trying to acquire a printer permit.");
            semaphore.acquire(); // Acquire a permit, blocks if no permits are available
            System.out.println(name + " has acquired a permit and is printing.");
            Thread.sleep((long) (Math.random() * 3000)); // Simulate printing
            System.out.println(name + " has finished printing and releasing the permit.");
            semaphore.release(); // Release the permit
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
