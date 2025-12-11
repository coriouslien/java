import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterExample {
    // AtomicInteger manages thread safety internally using CAS operations
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        // This operation is performed atomically without explicit locks
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicCounterExample counter = new AtomicCounterExample();
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
        System.out.println("Final Count (AtomicInteger): " + counter.getCount());
    }
}
