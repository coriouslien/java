class SynchronizedDemo {
    private int counter = 0;
    private final Object lock = new Object(); // Object used for block synchronization

    // Instance method synchronization (locks 'this')
    public synchronized void incrementMethod() {
        counter++;
    }

    // Synchronized block (locks the 'lock' object)
    public void incrementBlock() {
        synchronized (lock) {
            counter++;
        }
    }
}
