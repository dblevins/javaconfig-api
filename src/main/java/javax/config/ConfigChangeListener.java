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
 * Interface implemented by code interested in configuration changes, especially for code running in
 * a standalone/non CDI context. When CDI is available, {@link ConfigChangeEvent} are distributed by
 * sending corresponding enterprise events.
 * 
 * @author Anatole Tresch
 */
public interface ConfigChangeListener {
	
	/**
	 * Method called on change.
	 * 
	 * @param event
	 *            the {@link ConfigChangeEvent}, never {@code null}.
	 */
	void configChanged(ConfigChangeEvent event);
}
