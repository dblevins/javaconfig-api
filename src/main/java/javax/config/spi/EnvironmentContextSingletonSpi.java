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

import javax.config.Environment;
import javax.config.EnvironmentChangeListener;

/**
 * Service SPI for accessing {@link Environment}. Environments are used to
 * access/determine configurations.<br/>
 * <h3>Implementation Specification</h3>
 * <p>Implementations of this class
 * <ul>
 * <li>must be thread safe,
 * <li>may behave contextual.
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface EnvironmentContextSingletonSpi {

	/**
	 * Access the current runtime {@link Environment}.
	 * 
	 * @return the current runtime {@link Environment}, never {@code null}.
	 */
	Environment getCurrentEnvironment();

	/**
	 * Access the initial {@link Environment} corresponding to this Java VM. The
	 * initial environment is the root environment of all environments on this
	 * VM, hereby containing at least:<br/>
	 * <ul>
	 * <li>All environment properties as returned by {@link System#getenv()}.
	 * <li>All predefined System properties, such as
	 * {@code java.home, java.version etc}
	 * </ul>
	 * 
	 * @return the initial runtime {@link Environment}, never {@code null}.
	 */
	Environment getInitialEnvironment();

	/**
	 * Adds a listener for configuration changes, duplicates are ignored.
	 * 
	 * @param l
	 *            the listener to be added.
	 */
	void addEnvironmentChangeListener(EnvironmentChangeListener l);

	/**
	 * Removes a listener for configuration changes.
	 * 
	 * @param l
	 *            the listener to be removed.
	 */
	void removeEnvironmentChangeListener(EnvironmentChangeListener l);

	/**
	 * Access the child {@link Environment} instances of an environment.
	 * 
	 * @return the corresponding child {@link Environment} instances, never {@code null}.
	 */
	Collection<Environment> getChildEnvironments(Environment environment);

}