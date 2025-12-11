/**
 * Demonstrates Java Memory Model visibility problems
 * This shows how changes made by one thread may not be visible to another
 * without proper synchronization.
 */

// Problem 1: Simple visibility issue with non-volatile variable
class VisibilityProblem {
    private boolean stopRequested = false; // NOT volatile
    
    public void runExample() {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            // This loop may run forever because the thread might never see
            // the updated value of stopRequested due to CPU caching
            while (!stopRequested) {
                i++;
            }
            System.out.println("Background thread stopped after " + i + " iterations");
        });
        
        backgroundThread.start();
        
        try {
            Thread.sleep(1000); // Let it run for a second
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        stopRequested = true; // May not be visible to background thread!
        System.out.println("Main thread set stopRequested = true");
        
        try {
            backgroundThread.join(2000); // Wait up to 2 seconds
            if (backgroundThread.isAlive()) {
                System.out.println("WARNING: Background thread is still running (visibility problem!)");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Solution 1: Using volatile keyword
class VisibilitySolution1 {
    private volatile boolean stopRequested = false; // volatile ensures visibility
    
    public void runExample() {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
            System.out.println("Background thread stopped after " + i + " iterations");
        });
        
        backgroundThread.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        stopRequested = true;
        System.out.println("Main thread set stopRequested = true");
        
        try {
            backgroundThread.join(2000);
            if (!backgroundThread.isAlive()) {
                System.out.println("Background thread stopped successfully (volatile worked!)");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Problem 2: Lost updates without synchronization
class CounterProblem {
    private int count = 0; // NOT thread-safe
    
    public void increment() {
        count++; // NOT atomic: read, increment, write
    }
    
    public int getCount() {
        return count;
    }
    
    public void runExample() throws InterruptedException {
        int numThreads = 10;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numThreads];
        
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    increment();
                }
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        int expected = numThreads * incrementsPerThread;
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + count);
        System.out.println("Lost updates: " + (expected - count));
    }
}

// Solution 2: Using synchronized
class CounterSolution1 {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
    
    public void runExample() throws InterruptedException {
        int numThreads = 10;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numThreads];
        
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    increment();
                }
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        int expected = numThreads * incrementsPerThread;
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + getCount());
        System.out.println("SUCCESS: Synchronized prevented lost updates!");
    }
}

// Solution 3: Using AtomicInteger
import java.util.concurrent.atomic.AtomicInteger;

class CounterSolution2 {
    private AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet();
    }
    
    public int getCount() {
        return count.get();
    }
    
    public void runExample() throws InterruptedException {
        int numThreads = 10;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numThreads];
        
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    increment();
                }
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        int expected = numThreads * incrementsPerThread;
        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + getCount());
        System.out.println("SUCCESS: AtomicInteger prevented lost updates!");
    }
}

// Problem 3: Reordering issues
class ReorderingProblem {
    private int x = 0;
    private int y = 0;
    private int a = 0;
    private int b = 0;
    
    public void runExample() throws InterruptedException {
        int iterations = 100000;
        int reorderings = 0;
        
        for (int i = 0; i < iterations; i++) {
            x = 0; y = 0; a = 0; b = 0;
            
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });
            
            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            
            // Due to reordering, it's possible to get x=0 and y=0
            if (x == 0 && y == 0) {
                reorderings++;
            }
        }
        
        System.out.println("Iterations: " + iterations);
        System.out.println("Cases where x=0 and y=0: " + reorderings);
        if (reorderings > 0) {
            System.out.println("This demonstrates instruction reordering!");
        }
    }
}

// Main class to run all examples
public class MemoryVisibilityDemo {
    public static void main(String[] args) {
        System.out.println("=== Problem 1: Visibility Issue ===");
        new VisibilityProblem().runExample();
        
        System.out.println("\n=== Solution 1: Using volatile ===");
        new VisibilitySolution1().runExample();
        
        try {
            System.out.println("\n=== Problem 2: Lost Updates ===");
            new CounterProblem().runExample();
            
            System.out.println("\n=== Solution 2a: Using synchronized ===");
            new CounterSolution1().runExample();
            
            System.out.println("\n=== Solution 2b: Using AtomicInteger ===");
            new CounterSolution2().runExample();
            
            System.out.println("\n=== Problem 3: Instruction Reordering ===");
            new ReorderingProblem().runExample();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
}
