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

import javax.config.Environment;

/**
 * This SPI allows to define the current runtime environment.
 * <p>
 * <h4>Implementation Requirements</h4>
 * Implementations of this interface must be:
 * <ul>
 * <li>thread safe</li>
 * <li>contextual, and</li>
 * <li>fast, since this may be called many times.</li>
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface EnvironmentContext {

	/**
	 * Defines the {@link Environment} for the current runtime context.
	 * 
	 * @return the {@link Environment} for the given runtime context.
	 */
	Environment getCurrentEnvironment();

	/**
	 * Defines the global {@link Environment} for this system.
	 * 
	 * @return the {@link Environment} for this system.
	 */
	Environment getSystemEnvironment();

}
