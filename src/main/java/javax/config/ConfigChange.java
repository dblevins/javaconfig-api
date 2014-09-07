package javax.config;

/**
 * Class describing the detailed change of an entry.
 */
public interface ConfigChange{

    /**
     * Get the entries absolute key.
     * @return the key, never null.
     */
    String getKey();

    /**
     * Get the current value of the entry affected.
     *
     * @return the entry's value, may also be {@code null}.
     */
    String getValue();

    /**
     * Get the previous value of the entry affected.
     *
     * @return the entry's previous value, may also be {@code null}.
     */
    String getPreviousValue();

    /**
     * Get the update type of this entry.
     *
     * @return the update type, never null.
     */
    default ConfigChangeType getUpdateType(){
        if(getPreviousValue() == null){
            return ConfigChangeType.ADDED;
        }else if(getValue() == null){
            return ConfigChangeType.REMOVED;
        }
        return ConfigChangeType.UPDATED;
    }

}
