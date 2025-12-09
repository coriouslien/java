// Example 1: ReentrantLock vs. synchronized and volatile

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyExamples {

    // Shared resource for ReentrantLock example
    private int reentrantLockCounter = 0;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    // Shared resource for Synchronized example
    private int synchronizedCounter = 0;
    private final Object synchronizedLock = new Object(); // Object for synchronized block

    // Shared resource for Volatile example
    private volatile boolean running = true;

    // Method using ReentrantLock
    // ReentrantLock:
    // Explicitly managed using lock() and unlock() methods.
    // unlock() is placed in a finally block to ensure the lock is always released, even 
    // if exceptions occur.
    // Provides more flexibility than synchronized, including features like tryLock() and 
    // lockInterruptibly().
    public void incrementReentrantLockCounter() {
        reentrantLock.lock(); // Acquire the lock
        try {
            reentrantLockCounter++;
        } finally {
            reentrantLock.unlock(); // Release the lock in a finally block
        }
    }

    // Method using synchronized keyword
    // synchronized:
    // Uses the synchronized keyword on a method or a block of code.
    // Acquires an intrinsic lock associated with an object (or the class itself for static 
    // methods).
    // The lock is automatically released when the synchronized block or method exits.
    public void incrementSynchronizedCounter() {
        synchronized (synchronizedLock) { // Acquire the intrinsic lock of synchronizedLock object
            synchronizedCounter++;
        }
    }

    // Method to stop a thread using volatile
    // volatile:
    // Applied to a variable, ensuring that changes to its value are immediately visible to 
    // all threads.
    // Primarily guarantees visibility, not atomicity for compound operations (like 
    // incrementing a counter).
    // Suitable for simple flags or status variables where atomicity is not required, as 
    // demonstrated by the running flag.
    public void stopRunning() {
        running = false; // Changes are immediately visible to other threads
    }

    // Method that runs while 'running' is true
    public void doVolatileWork() {
        while (running) {
            // Simulate some work
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Volatile thread stopped.");
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrencyExamples examples = new ConcurrencyExamples();

        // ReentrantLock Example
        Thread reentrantLockThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                examples.incrementReentrantLockCounter();
            }
        });
        Thread reentrantLockThread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                examples.incrementReentrantLockCounter();
            }
        });

        reentrantLockThread1.start();
        reentrantLockThread2.start();
        reentrantLockThread1.join();
        reentrantLockThread2.join();
        System.out.println("ReentrantLock Counter: " + examples.reentrantLockCounter);

        Synchronized Example
        Thread synchronizedThread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                examples.incrementSynchronizedCounter();
            }
        });
        Thread synchronizedThread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                examples.incrementSynchronizedCounter();
            }
        });

        synchronizedThread1.start();
        synchronizedThread2.start();
        synchronizedThread1.join();
        synchronizedThread2.join();
        System.out.println("Synchronized Counter: " + examples.synchronizedCounter);

        // Volatile Example
        Thread volatileWorkerThread = new Thread(examples::doVolatileWork);
        volatileWorkerThread.start();
        Thread.sleep(100); // Give the worker thread some time to start
        examples.stopRunning(); // Signal the worker thread to stop
        volatileWorkerThread.join();
        System.out.println("Volatile example finished.");
    }
}
