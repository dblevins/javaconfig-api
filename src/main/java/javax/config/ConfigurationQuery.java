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

/**
 * This interface allows to query something from a {@link Configuration}. This
 * can also be used as <i>adapter</i> for evaluating something different from a
 * property value.
 * <h3>Implementation Specification</h3>
 * Implementations of this interface should be
 * <ul>
 * <li>Thread safe.
 * <li>immutable.
 * </ul>
 * 
 * @author Anatole Tresch
 * 
 * @param <T>
 *            The returned result type.
 */
@FunctionalInterface
public interface ConfigurationQuery<T> {

	T queryFrom(Configuration config);
}