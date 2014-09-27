/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.*;

/**
 * Event distributed when a {@link PropertyProvider} or {@link Configuration} has been changed, removed or added.
 *
 * @author Anatole Tresch
 */
public interface ConfigChangeEvent{

    /**
     * The {@link ConfigChange} instances of this change.
     *
     * @return the  {@link ConfigChange} instances, never {@code null}.
     */
    Collection<ConfigChange> getChanges();

    /**
     * Access the {@link Configuration} affected.
     *
     * @return the configuration, never {@code null}.
     */
    PropertyProvider getPropertyMap();

    /**
     * Access the optional meta data.
     *
     * @return the meta data, never {@code null}.
     */
    Map<String,String> getMetaData();

}
