// An Immutable Class with Mutable Fields (using defensive copying)
// This example demonstrates how to handle a mutable field (HashMap) correctly using defensive 
// copies.

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ImmutableConfig {

    private final int version;
    private final Map<String, String> settings;

    /**
     * Constructor performs a deep copy of the mutable Map argument.
     */
    public ImmutableConfig(int version, Map<String, String> settings) {
        this.version = version;
        // Defensive copy: create a new HashMap to store internal state,
        // rather than using the reference passed by the client.
        this.settings = new HashMap<>(settings);
    }

    public int getVersion() {
        return version;
    }

    /**
     * Getter performs a deep copy to prevent the internal state from being modified externally.
     */
    public Map<String, String> getSettings() {
        // Defensive copy: return a clone of the internal map, not the internal reference.
        return new HashMap<>(this.settings);
        // A Java 9+ alternative is to use Map.copyOf(settings) which returns an unmodifiable map.
    }
    public static void main(String[] args) {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("Key1", "Value1");
        originalMap.put("Key2", "Value2");

        ImmutableConfig config = new ImmutableConfig(1, originalMap);

        System.out.println("Original Config Map: " + config.getSettings());

        // Scenario 1: External modification of the original map used in the constructor
        originalMap.put("Key3", "Value3");
        System.out.println("After external modification of originalMap: " + config.getSettings());
        // Output shows Key3 is NOT present in config.settings, proving defensive copy worked.

        // Scenario 2: Modification via the getter method's return value
        Map<String, String> mapFromGetter = config.getSettings();
        mapFromGetter.put("Key4", "Value4");
        System.out.println("After modification of map from getter: " + config.getSettings());
        // Output shows Key4 is NOT present in config.settings, proving defensive copy worked in getter.
    }
}

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ImmutableConfig {

    private final int version;
    private final Map<String, String> settings;

    /**
     * Constructor performs a deep copy of the mutable Map argument.
     */
    public ImmutableConfig(int version, Map<String, String> settings) {
        this.version = version;
        // Defensive copy: create a new HashMap to store internal state,
        // rather than using the reference passed by the client.
        this.settings = new HashMap<>(settings);
    }

    public int getVersion() {
        return version;
    }

    /**
     * Getter performs a deep copy to prevent the internal state from being modified externally.
     */
    public Map<String, String> getSettings() {
        // Defensive copy: return a clone of the internal map, not the internal reference.
        return new HashMap<>(this.settings);
        // A Java 9+ alternative is to use Map.copyOf(settings) which returns an unmodifiable map.
    }
    public static void main(String[] args) {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("Key1", "Value1");
        originalMap.put("Key2", "Value2");

        ImmutableConfig config = new ImmutableConfig(1, originalMap);

        System.out.println("Original Config Map: " + config.getSettings());

        // Scenario 1: External modification of the original map used in the constructor
        originalMap.put("Key3", "Value3");
        System.out.println("After external modification of originalMap: " + config.getSettings());
        // Output shows Key3 is NOT present in config.settings, proving defensive copy worked.

        // Scenario 2: Modification via the getter method's return value
        Map<String, String> mapFromGetter = config.getSettings();
        mapFromGetter.put("Key4", "Value4");
        System.out.println("After modification of map from getter: " + config.getSettings());
        // Output shows Key4 is NOT present in config.settings, proving defensive copy worked in getter.
    }
    
}

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ImmutableConfig {

    private final int version;
    private final Map<String, String> settings;

    /**
     * Constructor performs a deep copy of the mutable Map argument.
     */
    public ImmutableConfig(int version, Map<String, String> settings) {
        this.version = version;
        // Defensive copy: create a new HashMap to store internal state,
        // rather than using the reference passed by the client.
        this.settings = new HashMap<>(settings);
    }

    public int getVersion() {
        return version;
    }

    /**
     * Getter performs a deep copy to prevent the internal state from being modified externally.
     */
    public Map<String, String> getSettings() {
        // Defensive copy: return a clone of the internal map, not the internal reference.
        return new HashMap<>(this.settings);
        // A Java 9+ alternative is to use Map.copyOf(settings) which returns an unmodifiable map.
    }
    public static void main(String[] args) {
        Map<String, String> originalMap = new HashMap<>();
        originalMap.put("Key1", "Value1");
        originalMap.put("Key2", "Value2");

        ImmutableConfig config = new ImmutableConfig(1, originalMap);

        System.out.println("Original Config Map: " + config.getSettings());

        // Scenario 1: External modification of the original map used in the constructor
        originalMap.put("Key3", "Value3");
        System.out.println("After external modification of originalMap: " + config.getSettings());
        // Output shows Key3 is NOT present in config.settings, proving defensive copy worked.

        // Scenario 2: Modification via the getter method's return value
        Map<String, String> mapFromGetter = config.getSettings();
        mapFromGetter.put("Key4", "Value4");
        System.out.println("After modification of map from getter: " + config.getSettings());
        // Output shows Key4 is NOT present in config.settings, proving defensive copy worked in getter.
    }
    
}
// output:
// Original Config Map: {Key2=Value2, Key1=Value1}
// After external modification of originalMap: {Key2=Value2, Key1=Value1}
// After modification of map from getter: {Key2=Value2, Key1=Value1}
