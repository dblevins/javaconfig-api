/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.Map;
import java.util.Set;

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
public interface PropertyMap extends Map<String,String>{

    /**
     * Get the sources read for this {@link PropertyMap} instance.
     *
     * @return the sources for the instance, never {@code null}.
     */
    Set<String> getSources();

    /**
     * Get the meta information for the given key.
     *
     * @param key the key, not {@code null}.
     * @return the according meta-info, or {@code null}.
     */
    Map<String,String> getMetaInfo(String key);

    /**
     * Get the property map's general meta-info.
     * @return the property map's general meta-info, never null.
     */
    Map<String,String> getMetaInfo();

    /**
     * Reloads the {@link PropertyMap}.
     */
    void reload();

    /**
     * This method allows to check, if an instance is mutable. If an instance is not mutable most of the so called
     * <i>optional</i> method of {@link java.util.Map} will throw an {@link java.lang.UnsupportedOperationException}:
     * <ul>
     *     <li>{@link #put(Object, Object)}</li>
     *     <li>{@link #putAll(java.util.Map)}</li>
     *     <li>{@link #clear()}</li>
     *     <li>{@link #putIfAbsent(Object, Object)}</li>
     *     <li>{@link #remove(Object)}</li>
     *     <li>{@link #remove(Object, Object)}</li>
     *     <li>{@link #replace(Object, Object)}</li>
     *     <li>{@link #replace(Object, Object, Object)}</li>
     *     <li>{@link #replaceAll(java.util.function.BiFunction)}</li>
     * </ul>
     * @return true, if this instance is mutable.
     */
    boolean isMutable();

}
