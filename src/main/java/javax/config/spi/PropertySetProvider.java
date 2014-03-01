/*
 *
 *  * Copyright (c) 2014.
 *  * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 *  * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 *  * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 *  * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 *  * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 *  * BUTTON AT THE BOTTOM OF THIS PAGE.
 *  *
 *  * Specification: Java Configuration ("Specification")
 *  *
 *  * Copyright (c) 2012-2014, Credit Suisse All rights reserved.
 *
 */
package javax.config.spi;

import javax.config.Environment;
import javax.config.PropertyMap;

/**
 * Provides a PropertyMap. Hereby a set can be implemented as shared instance, or created from scratch on each
 * access. Nevertheless each property set can behave contextually, depending on the current {@link javax.config.Environment}.<br/>
 * <h3>Implementation Specification</h3> Implementations of this interface must
 * be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * </ul>
 *
 * @author Anatole Tresch
 */
public interface PropertySetProvider{

	/**
	 * Get the qualifier of the PropertyMap provided.
	 *
	 * @return the config's name, not {@code null}.
	 */
	public Object getKey();

	/**
	 * Provides a PropertyMap instance.
	 *
	 * @return the {@link javax.config.PropertyMap} instance
	 */
	PropertyMap getPropertySet();

	/**
	 * Checks if a {@link javax.config.PropertyMap} is available for the given
	 * {@link javax.config.Environment}.
	 *
	 * @param environment
	 *            The {@link javax.config.Environment}, not {@code null}
	 * @return true, if the {@link javax.config.PropertyMap} is available
	 */
	boolean isAvailable(Environment environment);

}
