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

import java.util.Set;

/**
 * An configuration model is a aggregate combination of {@link Configuration}
 * instances. Hereby the effective properties visible are dependent on the
 * current {@link Environment}. It is possible that whole {@link Configuration}
 * instances can be not active, or that some {@link ConfigurationUnit} instances
 * may not be available.
 * 
 * @author Anatole Tresch
 */
public interface ConfigurationModel {

	/**
	 * Get the model's name.
	 * 
	 * @return the model name, never {@code null}.
	 */
	public String getName();

	/**
	 * Get the names of the contained {@link Configuration} entries.
	 * 
	 * @return the names of the contained {@link Configuration} entries, never
	 *         {@code null}.
	 */
	public Set<String> getConfigurations();

	/**
	 * Get the contained {@link Configuration} entries.
	 * 
	 * @param path
	 *            The regular expression path expression for selecting, never
	 *            {@code null}.
	 * @return the contained {@link Configuration} entries, never {@code null}.
	 */
	public Set<Configuration> getConfigurations(String path);

	/**
	 * Get the {@link Configuration} with the given name.
	 * 
	 * @ses {@link #isAvailable()}
	 * @param name
	 *            the config name, not {@code null}.
	 * @return the according {@link Configuration}
	 * @throws IllegalArgumentException
	 *             if no such config exists.
	 * @throws IllegalStateException
	 *             if the required configuration is not available in the current
	 *             runtime context.
	 */
	public Configuration getConfiguration(String name);

	/**
	 * Allows to evaluate if a node exists.
	 * 
	 * @param key
	 *            the configuration path.
	 * @return {@code true}, if such a node exists.
	 */
	public boolean containsConfiguration(String key);

	/**
	 * Extension point for adjusting configuration.
	 * 
	 * @param adjuster
	 *            A configuration ajuster, e.g. a filter, or an adjuster
	 *            combining configurations.
	 * @return the new adjusted configuration, never {@code null}.
	 */
	public ConfigurationModel with(ConfigurationModelAdjuster adjuster);

	/**
	 * Query some value from a configuration.
	 * 
	 * @param query
	 *            the query, never {@code null}.
	 * @return the result
	 */
	public <T> T query(ConfigurationModelQuery<T> query);

}
