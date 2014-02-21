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
 * The selector is responsible for determining if a configuration should be
 * included into the current configuration aggregate for a given runtime
 * {@link Environment}.
 * 
 * @author Anatole Tresch
 */
@FunctionalInterface
public interface EnvironmentSelector {

	/**
	 * Selector INSTANCE that selects every environment.
	 */
	public static final EnvironmentSelector ANY = new EnvironmentSelector() {
		@Override
		public boolean isMatching(Environment environment) {
			return true;
		}
	};

	/**
	 * Method that evaluates if a concrete environment is matching the
	 * constraints of this selector.
	 * 
	 * @param environment
	 *            The environment, not {@code null}.
	 * @return {@code true} if the environment is selected.
	 */
	public boolean isMatching(Environment environment);

}
