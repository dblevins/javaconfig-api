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
package javax.config;

import java.util.Collection;

/**
 * Service for accessing configuration. A configuration service is always base
 * on the environment definition provided by one instance of
 * {@link EnvironmentContext}. It is possible to define multiple
 * {@link ConfigService} instances, if required. <h3>Implementation
 * Specification</h3> Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface ConfigService {

	/**
	 * Access all defined {@link Configuration} ids.
	 * 
	 * @return all available configuration ids, never{@code null}.
	 */
	Collection<String> getConfigurationNames();
	
	/**
	 * Access the current {@link Configuration}, matching to the current
	 * {@link Environment}.
	 * 
	 * @see EnvironmentContext#getCurrentEnvironment()
	 * @return the current {@link Configuration} corresponding to the
	 *         current {@code Environment}.
	 */
	Configuration getConfiguration();

	/**
	 * Access a {@link Configuration} by name, matching to the current
	 * {@link Environment}.
	 * 
	 * @see #getCurrentEnvironment()
	 * @param name
	 *            The name of the required {@link Configuration}, not
	 *            {@code null}.
	 * @return the current {@link Configuration} corresponding to the
	 *         {@code configId}.
	 * @throws ConfigException
	 *             if the required configuration is not defined or not
	 *             available.
	 */
	Configuration getConfiguration(String name);

	/**
	 * Access a {@link Configuration} by name, matching the given target
	 * {@link Environment}. This can be used e.g. for accessing deployment
	 * configuration for an application to be deployed.
	 * 
	 * @param environment
	 *            the target environment
	 * @return the current {@link Configuration} corresponding to the
	 *         {@code configId}.
	 * @throws ConfigException
	 *             if the required configuration is not defined or not
	 *             available.
	 */
	Configuration getConfiguration(String name, Environment environment);

	/**
	 * Allows to check if a {@link Configuration} with the given id is
	 * defined.
	 * 
	 * @param configId
	 *            The model id to be looked up, not {@code null}.
	 * @return true, if the given {@link Configuration} is defined.
	 */
	boolean isConfigurationDefined(String configId);

	/**
	 * Create a {@link ConfigurationQuery} for querying arbitrary {@link Configuration}.
	 * 
	 * @return a new {@link ConfigurationQuery} instance.
	 */
	ConfigurationQuery queryConfiguration();

	/**
	 * Create a new {@link ConfigurationUpdater} for updating or deletion of a
	 * {@link Configuration}.
	 * 
	 * @return a new {@link ConfigurationUpdater}.
	 */
	public ConfigurationUpdater updateConfiguration(Configuration config);

	/**
	 * Creates a {@link ConfigurationUpdater} for creating a new
	 * {@link Configuration} instance.
	 * 
	 * @param configId
	 *            the new Configuration name
	 * @return the new {@link ConfigurationUpdater}, never {@code null}.
	 * @throws UnsupportedOperationException
	 *             if no new Configuration can be added.
	 */
	public ConfigurationUpdater createConfiguration(String name);

	/**
	 * Adds a listener for configuration changes, duplicates are ignored.
	 * 
	 * @param l
	 *            the listener to be added.
	 */
	void addConfigChangeListener(ConfigChangeListener l);

	/**
	 * Removes a listener for configuration changes.
	 * 
	 * @param l
	 *            the listener to be removed.
	 */
	void removeConfigChangeListener(ConfigChangeListener l);

	/**
	 * Resolved the annotated configuration resources on the given instance.
	 * 
	 * @param instance
	 *            to POJO instance to be configured.
	 * @throws IllegalArgumentException
	 *             if configuration could not be resolved, or converted.
	 */
	void configure(Object instance);

}
