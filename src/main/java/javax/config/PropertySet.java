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
 * A non aggregated set of configuration properties. The contained
 * properties may be read from single or several sources.
 * <p/>
 * <h3>Implementation
 * Specification</h3>
 * <p></p>Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * </ul>
 * It is highly recommended that implementations also are
 * <ul>
 * <li>serializable
 * </ul>
 * </p>
 *
 * @author Anatole Tresch
 */
public interface PropertySet{

    /**
     * Access the set's key. A set's key hereby identifies a given property set type. It depends on the scope
     * of the property set returned by the corresponding PropertySetProvider if two property sets sharing the same key
     * also are equal. It is possible to use Strig instances to identify property sets, though it is highly recommended
     * to define type safe identifiers, e.g. enumeration types.
     *
     * @return the property set's key, never {@code null}.
     */
    public Object getKey();

    /**
     * Get the sources read for this {@link PropertySet} instance.
     *
     * @return the sources for the instance, never {@code null}.
     */
    public Set<String> getSources();

    /**
     * Get the meta information for the given key.
     *
     * @param key the key, not {@code null}.
     * @return the according meta-info, or {@code null}.
     */
    public Map<String,String> getMetaInfo(String key);

    /**
     * Get the property value as {@link String}.
     *
     * @param key the property's absolute, or relative path, e.g. @code a/b/c/d.myProperty}.
     * @return the property's value, or {@code null}.
     */
    public String getProperty(String key);

    /**
     * Get all entries contained within a given {@link PropertySet}.
     *
     * @return the entries contained as a unmodifiable {@link Map}, never {@code null}.
     */
    public Map<String,String> getProperties();

    /**
     * Allows to determine if the given {@link PropertySet} defines any properties in the given
     * context( {@link Environment} ).
     *
     * @param environment the environment where the config is evaluated.
     * @return true, if the {@link PropertySet} is defined.
     */
    public boolean isAvailable(Environment environment);

    /**
     * Reloads the {@link PropertySet}.
     */
    public void reload();

}
