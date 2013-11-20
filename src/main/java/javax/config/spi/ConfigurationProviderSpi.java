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

import java.util.Collection;

import javax.config.Configuration;
import javax.config.ConfigurationUnit;
import javax.config.Environment;

/**
 * This SPI allows to add additional scopes to the system.
 * 
 * @author Anatole Tresch
 */
public interface ConfigurationProviderSpi {

	/**
	 * Called, when a given {@link ConfigurationModel} has to be evaluated.
	 * 
	 * @param modelId
	 *            the id of the model to be read.
	 * @param environment
	 *            The target environment.
	 * @return the corresponding {@link ConfigurationModel}, or {@code null}, if
	 *         not available for the given environment.
	 */
	Configuration getConfiguration(
			String modelId, Environment environment);

	/**
	 * Defines the {@link ConfigurationUnit} accessible by this SPI
	 * implementation.
	 * 
	 * @return the {@link ConfigurationUnit}s to be introduced by this SPI
	 *         implementation.
	 */
	Collection<String> getConfigurations();

	/**
	 * Called, when a given {@link ConfigurationUnit} has to be evaluated for
	 * inclusion into a configuration.
	 * 
	 * @param unit
	 *            the {@link ConfigurationUnit} to be read
	 * @param environment
	 *            The target environment
	 * @return the corresponding collection of {@link ConfigurationNode}, or
	 *         {@code null}, if not available for the given environment.
	 */
	Collection<Configuration> readConfiguration(
			String configId, Environment environment);

}
