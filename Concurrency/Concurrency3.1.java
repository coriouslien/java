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
        // supplyAsync(Supplier<T> supplier): Creates a CompletableFuture that runs a 
        // Supplier asynchronously and returns its result.

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
        // thenApply(Function<? super T, ? extends U> fn): Transforms the result of the 
        // current CompletableFuture with a given function, returning a new CompletableFuture 
        // with the transformed result.
        CompletableFuture<String> future2 = future1.thenApply(resultA -> {
            System.out.println("Task 2: Processing " + resultA);
            return resultA.toUpperCase();
        });

        // 3. thenCompose: Chain dependent CompletableFutures
        // thenCompose(Function<? super T, ? extends CompletionStage<U>> fn): Chains 
        // two CompletableFutures where the second CompletableFuture depends on the result 
        // of the first. The function passed to thenCompose returns a new CompletionStage.
        CompletableFuture<String> future3 = 
            future2.thenCompose(processedA -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 3: Fetching related data based on " + processedA);
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return processedA + " + Data from Source B";
        }));

        // 4. thenCombine: Combine results of two independent CompletableFutures
        // thenCombine(
        // CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn): 
        // Combines the results of two independent CompletableFutures using a BiFunction, 
        // returning a new CompletableFuture with the combined result.
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
        // (Function<Throwable, ? extends T> fn) explain: see the Concurrency.txt
        // exceptionally(Function<Throwable, ? extends T> fn): Provides a way to handle exceptions 
        // that occur during the execution of a CompletableFuture. If an exception occurs, 
        // the provided function is executed, and its result becomes the result of the 
        // CompletableFuture.
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
        // allOf(CompletableFuture<?>... cfs): Returns a new CompletableFuture that is completed 
        // when all of the given CompletableFutures are completed. It is useful for waiting for 
        // multiple independent asynchronous operations to finish.

        CompletableFuture<Void> allOfFutures = CompletableFuture.allOf(future4, futureWithError);

        // Wait for all futures to complete and print results
        allOfFutures.join(); // Blocks until all futures are complete

        System.out.println("\nAll tasks completed. Retrieving results:");
        System.out.println("Future 4 Result: " + future4.get());
        System.out.println("Future with Error Result: " + futureWithError.get());

        // 7. runAsync: Execute a Runnable asynchronously (no return value)
        // runAsync(Runnable runnable): Creates a CompletableFuture that runs a Runnable 
        // asynchronously without returning any result.
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Task 6: Running a background task without return value...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Task 6 completed.");
        });
        // join() and get(): Both methods wait for the CompletableFuture to complete. 
        // join() throws an unchecked CompletionException if the computation completes exceptionally, 
        // while get() throws checked InterruptedException and ExecutionException. join() is often 
        // preferred in scenarios where you expect the operation to succeed or want to handle 
        // exceptions via exceptionally().
        runAsyncFuture.join(); // Wait for it to complete

        System.out.println("CompletableFuture demonstration finished.");
    }
}
