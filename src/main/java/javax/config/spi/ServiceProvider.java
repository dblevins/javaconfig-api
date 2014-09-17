/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config.spi;

import java.util.Collections;
import java.util.List;

/**
 * This class models the component that is managing the lifecycle of the
 * services used by the Configuration API.
 * 
 * @author Anatole Tresch
 * @author Werner Keil
 */
public interface ServiceProvider {

	/**
	 * Access a list of services, given its type. The bootstrap mechanism should
	 * order the instance for precedence, hereby the most significant should be
	 * first in order.
	 * 
	 * @param serviceType
	 *            the service type.
	 * @return The instance to be used, never {@code null}
	 */
	@SuppressWarnings("unchecked")
    default <T> List<T> getServices(Class<T> serviceType){
          return getServices(serviceType, Collections.emptyList());
    }

	/**
	 * Access a list of services, given its type. The bootstrap mechanism should
	 * order the instance for precedence, hereby the most significant should be
	 * first in order.
	 * 
	 * @param serviceType
	 *            the service type.
	 * @param defaultList
	 *            the lis returned, if no services could be found.
	 * @return The instance to be used, never {@code null}
	 */
    <T> List<T> getServices(Class<T> serviceType, List<T> defaultList);

}
