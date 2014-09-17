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
public interface ConfigMap extends Map<String,String>{

    /**
     * Get the meta-info of a configuration.
     * @return the configuration's/config map's metaInfo, or null.
     */
    default String getMetaInfo(){
        return get("_metainfo");
    }

    /**
     * Get the meta-info for a configuration key.
     * @param key The map key, not null.
     * @return the configuration's  metaInfo for the given key, or null.
     */
    default String getMetaInfo(String key){
        return get(key + "._metainfo");
    }

    /**
     * Reloads the {@link ConfigMap}.
     */
    default void reload(){
        // by default do nothing
    }

    /**
     * This method allows to check, if an instance is mutable. If an instance is not mutable most of the so called
     * <i>optional</i> method of {@link java.util.Map} will throw an {@link java.lang.UnsupportedOperationException}:
     * <ul>
     * <li>{@link #put(Object, Object)}</li>
     * <li>{@link #putAll(java.util.Map)}</li>
     * <li>{@link #clear()}</li>
     * <li>{@link #putIfAbsent(Object, Object)}</li>
     * <li>{@link #remove(Object)}</li>
     * <li>{@link #remove(Object, Object)}</li>
     * <li>{@link #replace(Object, Object)}</li>
     * <li>{@link #replace(Object, Object, Object)}</li>
     * <li>{@link #replaceAll(java.util.function.BiFunction)}</li>
     * </ul>
     * <p>Note that if an instance is not mutable, it may still change its state on reload or update,
     * but it does not support programmatically controlled, arbitrary changes.</p>
     *
     * @return true, if this instance is mutable.
     */
    boolean isMutable();

}
