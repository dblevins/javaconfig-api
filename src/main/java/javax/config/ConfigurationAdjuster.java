/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

/**
 * <h3>Implementation Specification</h3> 
 * Implementations of this interface must be
 * <ul>
 * <li>reproducible
 * </ul>
 * Implementations of this interface should be
 * <ul>
 * <li>Thread safe.
 * <li>immutable.
 * </ul>
 */
// @FunctionalInterface
public interface ConfigurationAdjuster {

	public Configuration adjustInto(Configuration config);

}