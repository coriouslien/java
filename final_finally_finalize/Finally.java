import java.io.FileWriter;
import java.io.IOException;

class FinallyDemo {
    public static void processFile(String fileName, boolean shouldFail) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write("Writing data...\n");
            System.out.println("TRY: Data written successfully.");

            if (shouldFail) {
                // Simulate an unexpected error
                throw new IOException("Simulated WRITE error.");
            }
        } catch (IOException e) {
            System.err.println("CATCH: Caught exception: " + e.getMessage());
        } finally {
            // FINALLY block executes in ALL cases (success, exception, or even return)
            if (writer != null) {
                try {
                    writer.close(); // Crucial cleanup logic
                    System.out.println("FINALLY: File writer successfully closed.");
                } catch (IOException e) {
                    System.err.println("FINALLY Error: Failed to close writer.");
                }
            }
        }
    }
}
