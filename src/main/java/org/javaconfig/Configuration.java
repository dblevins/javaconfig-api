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
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A configuration models a aggregated set of properties, identified by a unique key, but adds higher level access functions to
 * a {@link org.javaconfig.PropertyProvider}. Hereby in most cases a configuration is a wrapper around a composite
 * {@link PropertyProvider} instance, which may combine multiple child providers in well defined tree like structure,
 * where nodes define logically the rules of priority, filtering, combination and overriding.
 * <br/>
 * <h3>Implementation Requirements</h3>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * </ul>
 * It is not recommended that implementations also are serializable, since the any configuration can be <i>freezed</i>
 * by reading out its complete configuration map into a serializable and remotable structure. This helps significantly
 * simplifying the development of this interface, e.g. for being backed up by systems and stores that are not part of
 * this library at all.
 *
 * @author Anatole Tresch
 */
public interface Configuration extends PropertyProvider{

    /**
     * Get the property value as {@link Boolean}.
     *
     * @param key the property's absolute, or relative path, e.g. {@code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Boolean getBoolean(String key){
        return get(key, Boolean.class);
    }

    /**
     * Get the property value as {@link Boolean}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Boolean getBooleanOrDefault(String key, Boolean defaultValue){
        return getOrDefault(key, Boolean.class, defaultValue);
    }

    /**
     * Get the property value as {@link Byte}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Byte getByte(String key){
        return get(key, Byte.class);
    }

    /**
     * Get the property value as {@link Byte}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Byte getByteOrDefault(String key, Byte defaultValue){
        return getOrDefault(key, Byte.class, defaultValue);
    }

    /**
     * Get the property value as {@link Short}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Short getShort(String key){
        return get(key, Short.class);
    }

    /**
     * Get the property value as {@link Short}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Short getShortOrDefault(String key, Short defaultValue){
        return getOrDefault(key, Short.class, defaultValue);
    }

    /**
     * Get the property value as {@link Integer}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Integer getInteger(String key){
        return get(key, Integer.class);
    }

    /**
     * Get the property value as {@link Integer}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Integer getIntegerOrDefault(String key, Integer defaultValue){
        return getOrDefault(key, Integer.class, defaultValue);
    }

    /**
     * Get the property value as {@link Long}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Long getLong(String key){
        return get(key, Long.class);
    }

    /**
     * Get the property value as {@link Long}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Long getLongOrDefault(String key, Long defaultValue){
        return getOrDefault(key, Long.class, defaultValue);
    }

    /**
     * Get the property value as {@link Float}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Float getFloat(String key){
        return get(key, Float.class);
    }

    /**
     * Get the property value as {@link Float}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Float getFloatOrDefault(String key, Float defaultValue){
        return getOrDefault(key, Float.class, defaultValue);
    }

    /**
     * Get the property value as {@link Double}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    default Double getDouble(String key){
        return get(key, Double.class);
    }

    /**
     * Get the property value as {@link Double}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default Double getDoubleOrDefault(String key, Double defaultValue){
        return getOrDefault(key, Double.class, defaultValue);
    }

    /**
     * Get the property value as type {@code Class<T>}.
     * <p>
     * If {@code Class<T>} is not one of
     * {@code Boolean, Short, Integer, Long, Float, Double, BigInteger,
     * BigDecimal, String} , an according adapter must be
     * available to perform the conversion from {@link String} to
     * {@code Class<T>}.
     *
     * @param key     the property's absolute, or relative path, e.g. @code
     *                a/b/c/d.myProperty}.
     * @param adapter the PropertyAdapter to perform the conversion from
     *                {@link String} to {@code Class<T>}, not {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type, or no such property exists.
     */
    default <T> T getAdapted(String key, PropertyAdapter<T> adapter){
        return getAdaptedOrDefault(key, adapter, null);
    }

    /**
     * Get the property value as type {@code Class<T>}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param adapter      the adapter to perform the conversion from
     *                     {@link String} to {@code Class<T>}, not {@code null}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    default <T> T getAdaptedOrDefault(String key, PropertyAdapter<T> adapter, T defaultValue){
        String value = getOrDefault(key, null);
        if(value==null){
            return defaultValue;
        }
        return adapter.adapt(value);
    }

    /**
     * Get the property value as type T. This will implicitly require a corresponding {@link org.javaconfig.PropertyAdapter}
     * to be available that is capable of providing type T from the given String value.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param type         The target type required, not null.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    <T> T getOrDefault(String key, Class<T> type, T defaultValue);

    /**
     * Get the property value as type T. This will implicitly require a corresponding {@link
     * org.javaconfig.PropertyAdapter} to be available that is capable of providing type T
     * from the given String value.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param type         The target type required, not null.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    <T> T get(String key, Class<T> type);

    /**
     * Return a set with all fully qualifies area names.
     *
     * @return s set with all areas, never {@code null}.
     */
    default Set<String> getAreas(){
        final Set<String> areas = new HashSet<>();
        this.keySet().forEach(s -> {
            int index = s.lastIndexOf('.');
            if(index > 0){
                areas.add(s.substring(0, index));
            }
            else{
                areas.add("<root>");
            }
        });
        return areas;
    }

    /**
     * Return a set with all fully qualified area names, containing the transitive closure also including all
     * subarea names, regardless if properties are accessible or not.
     *
     * @return s set with all transitive areas, never {@code null}.
     */
    default Set<String> getTransitiveAreas(){
        final Set<String> transitiveAreas = new HashSet<>();
        getAreas().forEach(s -> {
            int index = s.lastIndexOf('.');
            if (index < 0) {
                transitiveAreas.add("<root>");
            } else {
                while (index > 0) {
                    s = s.substring(0, index);
                    transitiveAreas.add(s);
                    index = s.lastIndexOf('.');
                }
            }
        });
        return transitiveAreas;
    }

    /**
     * Return a set with all fully qualified area names, containing only the
     * areas that match the predicate and have properties attached
     *
     * @param predicate A predicate to deternine, which areas should be returned, not {@code null}.
     * @return s set with all areas, never {@code null}.
     */
    default Set<String> getAreas(final Predicate<String> predicate){
        return getAreas().stream().filter(predicate).collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Return a set with all fully qualified area names, containing the transitive closure also including all
     * subarea names, regardless if properties are accessible or not.
     *
     * @param predicate A predicate to deternine, which areas should be returned, not {@code null}.
     * @return s set with all transitive areas, never {@code null}.
     */
    default Set<String> getTransitiveAreas(Predicate<String> predicate){
        return getTransitiveAreas().stream().filter(predicate).collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * Allows to evaluate if an area exists.
     *
     * @param key the configuration area (sub)path.
     * @return {@code true}, if such a node exists.
     */
    default boolean containsArea(String key){
        return getAreas().contains(key);
    }

    /**
     * Extension point for adjusting configuration.
     *
     * @param operator A configuration operator, e.g. a filter, or an adjuster
     *                 combining configurations.
     * @return the new adjusted configuration, never {@code null}.
     */
    default Configuration with(ConfigOperator operator){
        return operator.operate(this);
    }

    /**
     * Query some value from a configuration.
     *
     * @param query the query, never {@code null}.
     * @return the result
     */
    default <T> T query(ConfigQuery<T> query){
        return query.query(this);
    }

    /**
     * Field that allows property providers to be versioned, meaning that each change on a provider requires this value
     * to be incremented by one. This can be easily used to implement versioning (and optimistic locking)
     * in distributed (remote) usage scenarios.
     * @return the version number of the current instance.
     */
    default long getVersion(){
        return 0;
    }

    /**
     * Add a ConfigChangeListener to this configuration instance.
     * @param l the listener, not null.
     */
    void addPropertyChangeListener(PropertyChangeListener l);

    /**
     * Removes a ConfigChangeListener to this configuration instance.
     * @param l the listener, not null.
     */
    void removePropertyChangeListener(PropertyChangeListener l);
}
