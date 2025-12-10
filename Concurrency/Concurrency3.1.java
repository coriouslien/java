import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Starting CompletableFuture demonstration...");

        // 1. supplyAsync: Run a task asynchronously and return a result
        supplyAsync(Supplier<T> supplier): Creates a CompletableFuture that runs a 
        Supplier asynchronously and returns its result.

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1: Fetching data from Source A...");
            try {
                // Simulate delay
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3)); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Data from Source A";
        });

        // 2. thenApply: Transform the result of a CompletableFuture
        CompletableFuture<String> future2 = future1.thenApply(resultA -> {
            System.out.println("Task 2: Processing " + resultA);
            return resultA.toUpperCase();
        });

        // 3. thenCompose: Chain dependent CompletableFutures
        CompletableFuture<String> future3 = future2.thenCompose(processedA -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 3: Fetching related data based on " + processedA);
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return processedA + " + Data from Source B";
        }));

        // 4. thenCombine: Combine results of two independent CompletableFutures
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 4: Fetching data from Source C...");
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Data from Source C";
        }).thenCombine(future3, (resultC, combinedAB) -> {
            System.out.println("Task 5: Combining " + resultC + " and " + combinedAB);
            return "Final Result: " + combinedAB + " | " + resultC;
        });

        // 5. exceptionally: Handle exceptions
        CompletableFuture<String> futureWithError = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task with Error: Simulating an error...");
            if (true) { // Always throws for demonstration
                throw new RuntimeException("Something went wrong!");
            }
            return "This will not be returned.";
        }).exceptionally(ex -> {
            System.err.println("Error caught: " + ex.getMessage());
            return "Error Handled: Default Value";
        });

        // 6. allOf: Wait for multiple CompletableFutures to complete
        CompletableFuture<Void> allOfFutures = CompletableFuture.allOf(future4, futureWithError);

        // Wait for all futures to complete and print results
        allOfFutures.join(); // Blocks until all futures are complete

        System.out.println("\nAll tasks completed. Retrieving results:");
        System.out.println("Future 4 Result: " + future4.get());
        System.out.println("Future with Error Result: " + futureWithError.get());

        // 7. runAsync: Execute a Runnable asynchronously (no return value)
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Task 6: Running a background task without return value...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Task 6 completed.");
        });

        runAsyncFuture.join(); // Wait for it to complete

        System.out.println("CompletableFuture demonstration finished.");
    }
}
