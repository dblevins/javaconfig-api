/*
 * Copyright (c) 2014.
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 *
 * Specification: Java Configuration ("Specification")
 *
 * Copyright (c) 2012-2014, Credit Suisse All rights reserved.
 */
package javax.config.spi;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This interface defines a meta configuration model that defines an arbitrary set of {@link javax.config
 * .PartialMetaConfiguration} instances.
 * <h3>Implementation PropertyMapSpec</h3>
 * <p>
 * Implementations of this class must be
 * <ul>
 * <li>serializable</li>
 * <li>immutable</li>
 * <li>thread-safe</li>
 * </ul>
 * </p>
 */
public interface MetaModel{
    /**
     * Get the ids of the PartialMetaConfiguration instances for the given configured type.
     *
     * @param configuredType the target type, not null.
     * @return the set of ids available, never null.
     */
    public Set<String> getSpecificationIds(Class<?> configuredType);

    /**
     * Access a partial meta configuration.
     *
     * @param configuredType the target type, not null.
     * @param name           the item's name, not null and not empty
     * @return the according meta-configuration, or null.
     */
    public <T> Specification<T> getSpecification(Class<T> configuredType, String name);


    /**
     * Model for a partial configuration.
     */
    public interface Specification<T>{

        /**
         * The target type to be configured.
         *
         * @return the target type to be configured, never null.
         */
        Class<T> getConfiguredType();

        /**
         * Get the specification's name.
         * @return the specification's name, not null.
         */
        public String getName();

        /**
         * Get all partial child configurations.
         * @return all child configs, never null.
         */
        public Collection<Specification> getChildren();

    }

}
