import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

class AsyncDemo {
    // Task 1: Fetch user ID (simulates API call)
    public CompletableFuture<Integer> fetchUserId() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("T1: Fetching User ID...");
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            return 101; 
        });
    }

    // Task 2: Fetch user balance using the ID from Task 1
    public CompletableFuture<Double> fetchBalance(int userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: Fetching balance for user " + userId + "...");
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            return ThreadLocalRandom.current().nextDouble(1000.0, 5000.0);
        });
    }

    public void runAsync() {
        System.out.println("Starting CompletableFuture pipeline...");

        // 1. Start T1 (fetchUserId)
        CompletableFuture<Void> finalResult = fetchUserId()
            // 2. Chain T2: When T1 completes, take its result (ID) and run T2 (fetchBalance)
            .thenCompose(this::fetchBalance) 
            // 3. Chain final action: When T2 completes, accept its result (Balance) and print
            .thenAccept(balance -> {
                System.out.printf("T3: Final Balance Received: $%.2f%n", balance);
            })
            // 4. Handle errors anywhere in the chain
            .exceptionally(ex -> {
                System.err.println("Error in pipeline: " + ex.getMessage());
                return null;
            });
        
        // Wait for the final asynchronous task to complete (in a real app, main thread wouldn't wait)
        finalResult.join(); 
        System.out.println("CompletableFuture pipeline finished.");
    }
}
