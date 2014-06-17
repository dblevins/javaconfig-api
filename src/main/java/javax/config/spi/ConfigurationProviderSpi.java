/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config.spi;

import javax.config.Configuration;
import javax.config.ConfigurationContext;

/**
 * Provides a configuration based on the given {@link javax.config.ConfigurationContext}.<br/>
 * <h3>Implementation PropertyMapSpec</h3> Implementations of this interface must
 * be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface ConfigurationProviderSpi{

	/**
	 * Provides a configuration instance.
	 * @param configurationContext
     *            The {@link javax.config.ConfigurationContext}, not {@code null}
	 * @return the {@link Configuration} instance
	 */
	Configuration getConfiguration(ConfigurationContext configurationContext);

	/**
	 * Checks if a {@link Configuration} is available for the given
	 * {@link javax.config.ConfigurationContext}.
	 * 
	 * @param configurationContext
	 *            The {@link javax.config.ConfigurationContext}, not {@code null}
	 * @return true, if the {@link Configuration} is available
	 */
	boolean isAvailable(ConfigurationContext configurationContext);

}
