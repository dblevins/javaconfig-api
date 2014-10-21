package org.javaconfig;

import java.beans.PropertyChangeEvent;
import java.util.*;

/**
 * Event that contains a set of changes that were applied or could be applied.
 * This class is immutable and thread-safe. To create instances use
 * {@link org.javaconfig.ConfigChangeBuilder}.
 *
 * Created by Anatole on 22.10.2014.
 */
public final class ConfigChange {

    private PropertyProvider propertyProvider;
    private long baseVersion;
    private Map<String,PropertyChangeEvent> changes = new HashMap<>();

    ConfigChange(PropertyProvider propertyProvider, long baseVersion, Collection<PropertyChangeEvent> changes) {
        this.propertyProvider = Objects.requireNonNull(propertyProvider);
        this.baseVersion = baseVersion;
        changes.forEach((c) -> this.changes.put(c.getPropertyName(), c));
    }

    public PropertyProvider getPropertyProvider(){
        return this.propertyProvider;
    }

    public long getBaseVersion(){
        return baseVersion;
    }

    public Collection<PropertyChangeEvent> getEvents(){
        return Collections.unmodifiableCollection(this.changes.values());
    }

    /**
     * Access the number of removed entries.
     * @return the number of removed entries.
     */
    public int getRemovedSize() {
        return (int) this.changes.values().stream().filter((e) -> e.getNewValue() == null).count();
    }

    /**
     * Access the number of added entries.
     * @return the number of added entries.
     */
    public int getAddedSize() {
        return (int) this.changes.values().stream().filter((e) -> e.getOldValue() == null).count();
    }

    /**
     * Access the number of updated entries.
     * @return the number of updated entries.
     */
    public int getUpdatedSize() {
        return (int) this.changes.values().stream().filter((e) -> e.getOldValue()!=null && e.getNewValue()!=null).count();
    }


    /**
     * Checks if the given key was removed.
     * @param key the target key, not null.
     * @return true, if the given key was removed.
     */
    public boolean isRemoved(String key) {
        PropertyChangeEvent change = this.changes.get(key);
        if(change!=null){
            return change.getNewValue()==null;
        }
        return false;
    }

    /**
     * Checks if the given key was added.
     * @param key the target key, not null.
     * @return true, if the given key was added.
     */
    public boolean isAdded(String key) {
        PropertyChangeEvent change = this.changes.get(key);
        if(change!=null){
            return change.getOldValue()==null;
        }
        return false;
    }

    /**
     * Checks if the given key was updated.
     * @param key the target key, not null.
     * @return true, if the given key was updated.
     */
    public boolean isUpdated(String key) {
        PropertyChangeEvent change = this.changes.get(key);
        if(change!=null){
            return change.getOldValue()!=null && change.getNewValue()!=null;
        }
        return false;
    }

    /**
     * Checks if the given key is added, or updated AND NOT removed.
     * @param key the target key, not null.
     * @return true, if the given key was added, or updated BUT NOT removed.
     */
    public boolean containsKey(String key) {
        PropertyChangeEvent change = this.changes.get(key);
        if(change!=null){
            return change.getNewValue()!=null;
        }
        return false;
    }

    /**
     * Applies all changes to the given map instance.
     * @param map the target map.never null.
     */
    public void applyChangesTo(Map<String, String> map) {
        for(Map.Entry<String,PropertyChangeEvent> en: this.changes.entrySet()){
            if(en.getValue().getNewValue() == null){
                map.remove(en.getKey());
            }
            else{
                map.put(en.getKey(), (String)en.getValue().getNewValue());
            }
        }
    }


    @Override
    public String toString() {
        return "ConfigChange{" +
                "properties=" + propertyProvider +
                ", baseVersion=" + baseVersion +
                ", changes=" + changes +
                '}';
    }
}
