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

import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Set;

/**
 * This interface models a provider that serves configuration properties. The contained
 * properties may be read from single or several sources (composite).<br/>
 * Property providers are the building blocks out of which complex
 * configuration is setup.
 * <p/>
 * <h3>Implementation Requirements</h3>
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
public interface PropertyProvider {

    /**
     * Get the current number of properties available. This information must be seens as optional.
     * In the case a provider is not able to determine how many properties are served it should
     * return -1.
     * Composite implementations must depending on their composite assembling policies evaluate
     * this value as useful. Of course, -1, as undefined value, have to be handled correspondingly.
     * @return the number of available properties, or -1.
     */
    int size();

    /**
     * Access a property.
     * @param key the property's key, not null.
     * @return the property's value.
     */
    String get(String key);

    /**
     * Checks if a property is defined.
     * @param key the property's key, not null.
     * @return true, if the property is existing.
     */
    boolean containsKey(String key);

    /**
     * Access the current properties as Map.
     * @return the a corresponding map, never null.
     */
    Map<String, String> toMap();

    /**
     * Get the meta-info of a configuration.
     * @return the configuration's/config map's metaInfo, or null.
     */
    MetaInfo getMetaInfo();

    /**
     * Allows to check if this provider is mutable, meaning #toMutableProvider
     * must return a non null result.
     * @return true if this provider is mutable.
     * @see #toMutableProvider()
     */
    default boolean isMutable(){
        return false;
    }

    /**
     * Access a mutable instance of this PropertyProvider.
     * @return a mutable instance of this PropertyProvider, never null.
     * @throws java.lang.IllegalStateException if this provider instance is not mutable.
     * @see #isMutable()
     */
    default MutablePropertyProvider toMutableProvider(){
        throw new IllegalStateException("PropertyProvider is not mutable.");
    }

    /**
     * Check if no properties are currently available.
     * @return true, if no properties are currently accessible.
     */
    default boolean isEmpty(){
        return size()==0;
    }


    /**
     * Access a property.
     * @param key the property's key, not null.
     * @return the property's value.
     */
    default String getOrDefault(String key, String defaultValue){
        String value = get(key);
        if(value==null){
            return defaultValue;
        }
        return value;
    }

    /**
     * Access the set of property keys, defined by this provider.
     * @return the key set, never null.
     */
    default Set<String> keySet(){
        return toMap().keySet();
    }

    /**
     * Reloads the {@link PropertyProvider}.
     */
    default void load(){
        // by default do nothing
    }

    /**
     * Add a ConfigChangeListener to this configuration instance.
     * @param l the listener, not null.
     */
    default void addPropertyChangeListener(PropertyChangeListener l){
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a ConfigChangeListener to this configuration instance.
     * @param l the listener, not null.
     */
    default void removePropertyChangeListener(PropertyChangeListener l){
        return;
    }

}
