import java.util.concurrent.locks.ReentrantLock;

class SafeCounter {
    // Volatile ensures visibility of the 'running' flag across threads
    private volatile boolean running = true; 
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    // 1. Using ReentrantLock (flexible, manual lock management)
    public void incrementLock() {
        lock.lock(); // Acquire lock
        try {
            count++;
        } finally {
            lock.unlock(); // Guaranteed release in finally block (RAII equivalent)
        }
    }

    // 2. Using Synchronized (simpler, automatic lock management)
    public synchronized void decrementSync() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
