/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 * 
 * Specification: JSR-xxx Java Configuration API ("Specification")
 * 
 * Copyright (c) 2013-2014, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * A configuration models a aggregated set of properties, identified by a unique key.
 * In most cases a configuration is a combination of {@link PropertyProvider} instances, hereby
 * implementing overrides and filtering.
 * <br/>
 * <h3>Implementation PropertyMapSpec</h3>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * </ul>
 * It is not recommended that implementations also are serializable, since the utility functions allow to create
 * serialiable instance out of an arbitrary implementation of this interface.
 *
 * @author Anatole Tresch
 */
public interface Configuration extends PropertyProvider{

    /**
     * Get the property value as {@link Boolean}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
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
        return adapter.apply(get(key));
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
        return adapter.apply(value);
    }

    /**
     * Get the property value as type T. This will implicitly require a corresponding {@link javax.config
     * .PropertyAdapter} to be available that is capable of providing type T from the given String value.
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
     * Get the property value as type T. This will implicitly require a corresponding {@link javax.config
     * .PropertyAdapter} to be available that is capable of providing type T from the given String value.
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
    Set<String> getAreas();

    /**
     * Return a set with all fully qualified area names, containing the transitive closure also including all
     * subarea names, regardless if properties are accessible or not.
     *
     * @return s set with all transitive areas, never {@code null}.
     */
    Set<String> getTransitiveAreas();

    /**
     * Return a set with all fully qualified area names, containing only the
     * areas that match the predicate and have properties attached
     *
     * @param predicate A predicate to deternine, which areas should be returned, not {@code null}.
     * @return s set with all areas, never {@code null}.
     */
    default Set<String> getAreas(final Predicate<String> predicate){
        Set<String> result = new HashSet<>();
        getAreas().stream().filter(predicate).forEach((s) ->result.add(s));
        return result;
    }

    /**
     * Return a set with all fully qualified area names, containing the transitive closure also including all
     * subarea names, regardless if properties are accessible or not.
     *
     * @param predicate A predicate to deternine, which areas should be returned, not {@code null}.
     * @return s set with all transitive areas, never {@code null}.
     */
    default Set<String> getTransitiveAreas(Predicate<String> predicate){
        Set<String> result = new HashSet<>();
        getTransitiveAreas().stream().filter(predicate).forEach((s) ->result.add(s));
        return result;
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
        return operator.apply(this);
    }

    /**
     * Query some value from a configuration.
     *
     * @param query the query, never {@code null}.
     * @return the result
     */
    default <T> T query(ConfigQuery<T> query){
        return query.apply(this);
    }

    /**
     * Add a ConfigChangeListener to this configuration instance.
     * @param l the listener, not null.
     */
    void addConfigChangeListener(ConfigChangeListener l);

    /**
     * Removes a ConfigChangeListener to this configuration instance.
     * @param l the listener, not null.
     */
    void removeConfigChangeListener(ConfigChangeListener l);

}
