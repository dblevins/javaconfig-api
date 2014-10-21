/*
 * Copyright 2014 Credit Suisse and other (see authors).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.javaconfig;

import java.beans.PropertyChangeEvent;
import java.util.*;

/**
 * Created by Anatole on 06.09.2014.
 */
public final class ConfigChangeBuilder {

    final SortedMap<String, PropertyChangeEvent> delta = new TreeMap<>();
    PropertyProvider source;
    long baseVersion;

    private ConfigChangeBuilder(PropertyProvider source, long baseVersion) {
        Objects.requireNonNull(source);
        this.source = source;
        this.baseVersion= baseVersion;
    }

    public static final ConfigChangeBuilder of(PropertyProvider source, long baseVersion) {
        return new ConfigChangeBuilder(source, baseVersion);
    }

    public static final ConfigChangeBuilder of(Configuration configuration) {
        return new ConfigChangeBuilder(configuration, configuration.getVersion());
    }

    public ConfigChangeBuilder addChange(PropertyChangeEvent detail) {
        Objects.requireNonNull(detail);
        this.delta.put(detail.getPropertyName(), detail);
        return this;
    }

    public ConfigChangeBuilder addChange(String key, String value, String oldValue) {
        return addChange(new PropertyChangeEvent(source, key, oldValue, value));
    }

    public ConfigChangeBuilder addChanges(PropertyProvider newState) {
        compare(newState, this.source).forEach((c) -> {
            this.delta.put(c.getPropertyName(), c);
        });
        return this;
    }

    public ConfigChange build() {
        return new ConfigChange(this.source, baseVersion, Collections.unmodifiableCollection(this.delta.values()));
    }


    public static Collection<PropertyChangeEvent> compare(PropertyProvider map1, PropertyProvider map2) {
        List<PropertyChangeEvent> changes = new ArrayList<>();
        for (Map.Entry<String, String> en : map1.toMap().entrySet()) {
            String val = map2.get(en.getKey());
            changes.add(new PropertyChangeEvent(map1, en.getKey(), val, en.getValue()));
        }
        for (Map.Entry<String, String> en : map2.toMap().entrySet()) {
            String val = map1.get(en.getKey());
            changes.add(new PropertyChangeEvent(map1, en.getKey(), val, en.getValue()));
        }
        return changes;
    }

    /**
     * Removes all recorded change events.
     */
    public void clear() {
        this.delta.clear();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PropertyChangeEventBuilder [source=" + source + ", " +
                ", delta=" + delta + "]";
    }


    public String get(String key) {
        PropertyChangeEvent change = this.delta.get(key);
        if(change!=null && !(change.getNewValue()==null)){
            return (String)change.getNewValue();
        }
        return null;
    }
}
