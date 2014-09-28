/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A non map of configuration properties. The contained
 * properties may be read from single or several sources.<br/>
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
public interface PropertyProvider { // extends Map<String,String>{

    /**
     * Get the current number of properties available.
     * @return the number of available properties.
     */
    int size();

    /**
     * Checks, if the given value is present.
     * @param value the value, not null.
     * @return true, if the given value is present.
     */
    boolean containsValue(String value);

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
     * Access the set of property values, defined by this provider.
     * @return the value set, never null.
     */
    default Collection<String> values(){
        return toMap().values();
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
     * Reloads the {@link PropertyProvider}.
     */
    default void reload(){
        // by default do nothing
    }

}
