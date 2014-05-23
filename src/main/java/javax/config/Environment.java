/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Models the current runtime environment. Instances of this class are used to
 * evaluate the correct configuration artifacts.<br/>
 * <h3>Implementation PropertyMapSpec</h3>
 * <p>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * <li>serializable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface Environment {

	/**
	 * Get the name of the environment. The environment's name must be unique in
	 * combination with the EnvironmentType.
	 * 
	 * @return the environment's name, not {@code null}
	 */
	public String getName();

    /**
     * Get the environment's stage.
     * @return the current stage, never null.
     */
    public Stage getStage();

	/**
	 * Get the overall (read-only) properties for this {@link Environment}. The
	 * values of the parent environment, by default are also visible, but may be
	 * overridden, or extended by this {@link Environment}. Removal of entries
	 * visible on the parent is rare, but is not forbidden.
	 * 
	 * @return the overall (read-only) properties for this {@link Environment},
	 *         never {@code null}.
	 */
	public Set<String> getAttributeKeys(Class<?> targetType);

	/**
	 * Get an attribute.
	 * 
	 * @param key
	 *            the attribute's key.
	 * @param type
	 *            the attribute's type.
	 * @return the attribute's value.
	 */
	public <T> T getAttribute(String key, Class<T> type);

	/**
	 * Get an attribute.
	 *
	 * @param type
	 *            the attribute's type.
	 * @return the attribute's value.
	 */
	public <T> T getAttribute(Class<T> type);

	/**
	 * Get the parent {@link Environment} of this environment. The values of the
	 * parent environment, by default are also visible, but may be overridden,
	 * or extended by this {@link Environment}. Removal of entries visible on
	 * the parent is rare, but is not forbidden.
	 *
	 * @return the parent {@link Environment}, or {@code null}, if there is no
	 *         parent, e.g. for the global system environment.
	 */
	public Environment getParent();

}
