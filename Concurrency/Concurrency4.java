
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounterExample {
    private int count = 0;
    // Create a ReentrantLock instance
    private final Lock lock = new ReentrantLock();

    public void increment() {
        // Acquire the lock
        lock.lock();
        try {
            // Critical section of code
            count++;
        } finally {
            // ALWAYS release the lock in a finally block to prevent deadlocks
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        LockCounterExample counter = new LockCounterExample();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // The result will consistently be 2000
        System.out.println("Final Count (ReentrantLock): " + counter.getCount());
    }
}
