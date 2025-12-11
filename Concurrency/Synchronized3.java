public class SynchronizedCounterExample {
    private int count = 0;

    // Synchronizing the instance method locks the 'this' object
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounterExample counter = new SynchronizedCounterExample();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        // Wait for both threads to complete
        t1.join();
        t2.join();

        // The result will consistently be 2000 due to synchronization
        System.out.println("Final Count (synchronized): " + counter.getCount()); 
    }
}
