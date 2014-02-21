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
import javax.config.Environment;

/**
 * Provides a configuration based on the given {@link Environment}.<br/>
 * <h3>Implementation Specification</h3> Implementations of this interface must
 * be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface ConfigurationProvider {

	/**
	 * Get the qualifier of the configuration provided.
	 * 
	 * @return the config's name, not {@code null}.
	 */
	public Object getKey();

	/**
	 * Provides a configuration instance.
	 *
	 * @return the {@link Configuration} instance
	 */
	Configuration getConfiguration();

	/**
	 * Checks if a {@link Configuration} is available for the given
	 * {@link Environment}.
	 * 
	 * @param environment
	 *            The {@link Environment}, not {@code null}
	 * @return true, if the {@link Configuration} is available
	 */
	boolean isAvailable(Environment environment);

}
