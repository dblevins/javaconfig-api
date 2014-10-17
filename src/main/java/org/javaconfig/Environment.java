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

import java.util.Set;

/**
 * Models a runtime environment. Instances of this class are used to
 * evaluate the correct configuration artifacts.<br/>
 * <h3>Implementation Requirements</h3>
 * <p>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * <li>serializable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface Environment extends StageSupplier, Iterable<Environment>{

    /**
     * Get a unique type (within this VM) for this environment.
     * Types represent the environment level within the hierarchy
     * of possible environments, e.g. {@code system, ear, webapp, tenant}.
     */
    String getEnvironmentType();

    /**
     * Get a unique name (in combination with the environment type within this VM)
     * for this environment instance.
     * Where a human readable name is available this would be preferable
     * over a technical key/UUID.
     * @return a unique id for this environment, when comined with the environment type.
     */
    String getEnvironmentId();

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
    Set<String> keySet();

    /**
     * Get an qualified path to this environment instance, by appending the
     * current environment id and type (in backets) with the ones of its parent and so on, e.g.
     * <code>root[system].HumanOne[ear].rest[webapp].atsticks[user]</code>
     * @return the qualified path of this environment instance
     */
    String getEnvironmentPath();

    /**
     * Get the parent context.
     * @return the parent context, or null.
     */
    Environment getParentEnvironment();


}
