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

import java.util.Set;
import java.util.function.Predicate;

/**
 * A configuration models a aggregated set of properties, identified by a unique key.
 * In most cases a configuration is a combination of {@link PropertyMap} instances, hereby
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
public interface Configuration extends PropertyMap{

    /**
     * Access the identifying key of a configuration.
     * @return the configuration's key
     */
    public String getConfigId();

    /**
     * Get the property value as {@link Boolean}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Boolean getBoolean(String key);

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
    public Boolean getBooleanOrDefault(String key, Boolean defaultValue);

    /**
     * Get the property value as {@link Byte}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Byte getByte(String key);

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
    public Byte getByteOrDefault(String key, Byte defaultValue);

    /**
     * Get the property value as {@link Short}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Short getShort(String key);

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
    public Short getShortOrDefault(String key, Short defaultValue);

    /**
     * Get the property value as {@link Integer}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Integer getInteger(String key);

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
    public Integer getIntegerOrDefault(String key, Integer defaultValue);

    /**
     * Get the property value as {@link Long}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Long getLong(String key);

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
    public Long getLongOrDefault(String key, Long defaultValue);

    /**
     * Get the property value as {@link Float}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Float getFloat(String key);

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
    public Float getFloatOrDefault(String key, Float defaultValue);

    /**
     * Get the property value as {@link Double}.
     *
     * @param key the property's absolute, or relative path, e.g. @code
     *            a/b/c/d.myProperty}.
     * @return the property's value.
     * @throws IllegalArgumentException if no such property exists.
     */
    public Double getDouble(String key);

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
    public Double getDoubleOrDefault(String key, Double defaultValue);

    /**
     * Get the property value as type {@code Class<T>}.
     * <p>
     * If {@code Class<T>} is not one of
     * {@code Boolean, Short, Integer, Long, Float, Double, BigInteger,
     * BigDecimal, String} , an according {@link PropertyAdapter} must be
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
    public <T> T getAdapted(String key, PropertyAdapter<T> adapter);

    /**
     * Get the property value as type {@code Class<T>}.
     *
     * @param key          the property's absolute, or relative path, e.g. @code
     *                     a/b/c/d.myProperty}.
     * @param adapter      the {@link PropertyAdapter} to perform the conversion from
     *                     {@link String} to {@code Class<T>}, not {@code null}.
     * @param defaultValue the default value, returned if no such property exists or the
     *                     property's value is {@code null}.
     * @return the property's value.
     * @throws IllegalArgumentException if the value could not be converted to the required target
     *                                  type.
     */
    public <T> T getAdaptedOrDefault(String key, PropertyAdapter<T> adapter, T defaultValue);

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
    public <T> T getOrDefault(String key, Class<T> type, T defaultValue);

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
    public <T> T get(String key, Class<T> type);

    /**
     * Return a set with all fully qualifies area names.
     *
     * @return s set with all areas, never {@code null}.
     */
    public Set<String> getAreas();

    /**
     * Return a set with all fully qualified area names, containing the transitive closure also including all
     * subarea names, regardless if properties are accessible or not.
     *
     * @return s set with all transitive areas, never {@code null}.
     */
    public Set<String> getTransitiveAreas();

    /**
     * Return a set with all fully qualified area names, containing only the
     * areas that match the predicate and have properties attached
     *
     * @param predicate A predicate to deternine, which areas should be returned, not {@code null}.
     * @return s set with all areas, never {@code null}.
     */
    public Set<String> getAreas(Predicate<String> predicate);

    /**
     * Return a set with all fully qualified area names, containing the transitive closure also including all
     * subarea names, regardless if properties are accessible or not.
     *
     * @param predicate A predicate to deternine, which areas should be returned, not {@code null}.
     * @return s set with all transitive areas, never {@code null}.
     */
    public Set<String> getTransitiveAreas(Predicate<String> predicate);

    /**
     * Allows to evaluate if an area exists.
     *
     * @param key the configuration area (sub)path.
     * @return {@code true}, if such a node exists.
     */
    public boolean containsArea(String key);

    /**
     * Extension point for adjusting configuration.
     *
     * @param adjuster A configuration ajuster, e.g. a filter, or an adjuster
     *                 combining configurations.
     * @return the new adjusted configuration, never {@code null}.
     */
    public Configuration with(ConfigurationAdjuster adjuster);

    /**
     * Query some value from a configuration.
     *
     * @param query the query, never {@code null}.
     * @return the result
     */
    public <T> T query(ConfigurationQuery<T> query);


}
