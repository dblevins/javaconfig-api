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
 * Copyright (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config.spi;

import javax.config.ConfigChangeListener;
import javax.config.Configuration;
import java.util.Collection;

/**
 * Service for accessing configuration. A configuration service is always base
 * on the environment definition provided by one instance of
 * {@link javax.config.EnvironmentContext}. It is possible to define multiple
 * {@link ConfigurationServiceSpi} instances, if required. <h3>Implementation
 * PropertyMapSpec</h3> Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * </ul>
 *
 * @author Anatole Tresch
 */
public interface ConfigurationServiceSpi{

    /**
     * Access all defined {@link javax.config.Configuration} keys.
     *
     * @return all available configuration keys, never{@code null}.
     */
    Collection<String> getConfigurationIds();

    /**
     * Access the current {@link javax.config.Configuration}, matching to the current
     * {@link javax.config.Environment}.
     *
     * @return the current {@link javax.config.Configuration} corresponding to the
     * current {@code Environment}.
     * @see javax.config.EnvironmentContext#getCurrentEnvironment()
     */
    Configuration getConfiguration();

    /**
     * Access a {@link Configuration} by name, matching to the current
     * {@link javax.config.Environment}.
     *
     * @param configId The config id of the required {@link Configuration}, not
     *            {@code null}.
     * @return the current {@link Configuration} corresponding to the
     * {@code configId}.
     * @throws javax.config.ConfigException if the required configuration is not defined or not
     *                         available.
     * @see javax.config.EnvironmentContext#getCurrentEnvironment()
     */
    Configuration getConfiguration(String configId);

    /**
     * Allows to check if a {@link Configuration} with the given id is
     * defined.
     *
     * @param configId The config id of the required {@link Configuration}, not
     *            {@code null}.
     * @return true, if the given {@link Configuration} is defined.
     */
    boolean isConfigurationDefined(String configId);

    /**
     * Adds a listener for configuration changes, duplicates are ignored.
     *
     * @param l the listener to be added.
     */
    void addConfigChangeListener(ConfigChangeListener l);

    /**
     * Removes a listener for configuration changes.
     *
     * @param l the listener to be removed.
     */
    void removeConfigChangeListener(ConfigChangeListener l);

    /**
     * Resolved the annotated configuration resources on the given instance.
     *
     * @param instance to POJO instance to be configured.
     * @throws IllegalArgumentException if configuration could not be resolved, or converted.
     */
    void configure(Object instance);

    /**
     * Resolved the annotated configuration resources on the given instance.
     *
     * @param instance      to POJO instance to be configured.
     * @param configuration The Configuration to be used.
     * @throws IllegalArgumentException if configuration could not be resolved, or converted.
     */
    void configure(Object instance, Configuration configuration);

}
