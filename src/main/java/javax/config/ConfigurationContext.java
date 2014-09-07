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
import java.util.Objects;

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
public interface ConfigurationContext extends Map<String,String>, StageSupplier{

	/**
	 * Get the name of the environment. The environment's name must be unique in
	 * combination with the EnvironmentType.
	 * 
	 * @return the environment's name, not {@code null}
	 */
	String getName();

    /**
     * Get the parent context.
     * @return the parent context, or null.
     */
    ConfigurationContext getParentContext();


}
