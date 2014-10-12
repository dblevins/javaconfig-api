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

import java.util.Map;

/**
 * A non map of configuration properties. The contained
 * properties may be read from single or several sources.<br/>
 * Property maps are the building blocks out of which complex
 * configuration is setup. Hereby you can define the configuration
 * using a corresponding meta model or also programmatically using a <i>buildable</i>
 * configuration.
 * <p/>
 * <h3>Implementation
 * PropertyMapSpec</h3>
 * <p></p>Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * </ul>
 * It is highly recommended that implementations also are
 * <ul>
 * <li>Immutable</li>
 * <li>serializable</li>
 * </ul>
 * </p>
 *
 * @author Anatole Tresch
 */
public interface MutablePropertyProvider extends PropertyProvider{
    /**
     * Add/replace an entry.
     * @param key the key, not null.
     * @param value the new value, not null.
     * @return any previous assigned value, or null.
     */
    String put(String key, String value);

    /**
     * Puts all values given. Any existing values will be overriden.
     * @param m the map to be used, not null.
     */
    void putAll(Map<? extends String,? extends String> m);

    /**
     * Removes a single key from the map.
     * @param key the key, not null.
     * @return any orevious value, or null.
     */
    String remove(String key);

    /**
     * Clear all keys currently stored.
     */
    void clear();

    /**
     * Default method to be implemented, since the implementor would be exact this type of instance.
     * @return this, which is correct.
     */
    @Override
    default MutablePropertyProvider toMutableProvider(){
        return this;
    }

}
