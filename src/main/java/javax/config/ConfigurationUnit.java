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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A non aggregated configuration part read based on a configuration expression.
 * The contained configuration entries may be read from single or several
 * sources.
 * 
 * @author Anatole Tresch
 */
public interface ConfigurationUnit {

	/**
	 * Access the unit's name.
	 * 
	 * @return the name, never {@code null}.
	 */
	public String getName();

	/**
	 * Get the sources read for this {@link ConfigurationMap} instance.
	 * 
	 * @return the sources for the instance, never {@code null}.
	 */
	public Set<String> getSources();

	/**
	 * Get a list of exceptions occurred during creation of a
	 * {@link ConfigurationMap}.
	 * 
	 * @return the ordered list of errors occurred.
	 */
	public List<Throwable> getErrors();

	/**
	 * Get the {@link PropertyValueMetaInfo} for the given key.
	 * 
	 * @param key
	 *            the key, not {@code null}.
	 * @return the according meta-info, or {@code null}.
	 */
	public Map<String, String> getMetaInfo(String key);

	/**
	 * Get the property value as {@link String}.
	 * 
	 * @param key
	 *            the property's absolute, or relative path, e.g. @code
	 *            a/b/c/d.myProperty}.
	 * @return the property's value, or {@code null}.
	 */
	public String getProperty(String key);

	/**
	 * Get all entries contained within a given {@link ConfigurationMap}.
	 * 
	 * @return the entries contained as a unmodifiable {@link Map}, never
	 *         {@code null}.
	 */
	public Map<String, String> getProperties();

	/**
	 * Allows to determine if the given {@link ConfigurationMap} defines any
	 * properties in the given context( {@link Environment} ).
	 * 
	 * @param environment
	 *            the environment where the config is evaluated.
	 * @return true, if the {@link ConfigurationMap} is defined.
	 */
	public boolean isActive(Environment environment);

	/**
	 * Reloads the {@link ConfigurationUnit}.
	 */
	public void reload();

}
