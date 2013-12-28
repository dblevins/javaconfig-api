/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.Map;

/**
 * Models the current runtime environment. Instances of this class are selected by
 * {@link EnvironmentSelector} to evaluate if configuration artifacts are to be included (selected)
 * into the current configuration aggregate.
 * <h3>Implementation Specification</h3>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * <li>serializable
 * </ul>
 * @author Anatole Tresch
 */
public interface Environment {

	// // Which properties hould be explicit?
	// // Stage
	// // Development Mode?
	// // Global Log Level?
	// // Tier?
	// // AppId?
	// // EarID?
	// // RuntimeID?
	// // Timezone?
	// // Country?
	// // Language?
	// // User?

	/**
	 * Get the {@link Stage}. The {@link Stage} provides a general switch supported by the software
	 * components. It can be refined/extended by additional properties, as required.
	 * 
	 * @see #getProperties()
	 * @return the current {@link Stage}, never {@code null}.
	 */
	public Stage getStage();

	/**
	 * Get the overall (read-only) properties for this {@link Environment}. The values of the parent
	 * environment, by default are also visible, but may be overridden, or extended by this
	 * {@link Environment}. Removal of entries visible on the parent is rare, but is not forbidden.
	 * 
	 * @return the overall (read-only) properties for this {@link Environment}, never {@code null}.
	 */
	public Map<String, String> getProperties();

	/**
	 * Get the parent {@link Environment} of this environment. The values of the parent environment,
	 * by default are also visible, but may be overridden, or extended by this {@link Environment}.
	 * Removal of entries visible on the parent is rare, but is not forbidden.
	 * 
	 * @see ConfigService#getCurrentEnvironment()
	 * @see ConfigService#getSystemEnvironment()
	 * @return the parent {@link Environment}, or {@code null}, if there is no parent, e.g. for the
	 *         global system environment as returned by {@link ConfigService#getSystemEnvironment()}
	 *         .
	 */
	public Environment getParent();

}
