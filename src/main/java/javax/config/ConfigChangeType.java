package javax.config;

/**
 * Basic type of change.
 */
public enum ConfigChangeType{
    /**
     * The new and old configuration show differences in some keys.
     */
    UPDATED,
    /**
     * The configuration was removed.
     */
    REMOVED,
    /**
     * The configuration has been added.
     */
    ADDED
}
