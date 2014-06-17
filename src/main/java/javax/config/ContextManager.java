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
 * Service for accessing {@link ConfigurationContext}. Environments are used to
 * access/determine configurations.<br/>
 * <h3>Implementation PropertyMapSpec</h3> This class is
 * <ul>
 * <li>thread safe,
 * <li>and behaves contextual.
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface ContextManager{

	/**
	 * Access the current runtime {@link ConfigurationContext}.
	 * 
	 * @return the current runtime {@link ConfigurationContext}, never {@code null}.
	 */
	public ConfigurationContext getCurrentContext();

	/**
	 * Access the initial {@link ConfigurationContext} corresponding to this Java VM. The
	 * initial environment is the root environment of all environments on this
	 * VM, hereby containing at least:<br/>
	 * <ul>
	 * <li>All environment properties as returned by {@link System#getenv()}.
	 * <li>All predefined System properties, such as
	 * {@code java.home, java.version etc}
	 * </ul>
	 * 
	 * @return the initial runtime {@link ConfigurationContext}, never {@code null}.
	 */
	public ConfigurationContext getRootContext();

}
