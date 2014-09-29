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
 * Interface for an filter/operator that converts a configured String into another String. One typical
 * use case would the the decryption of an encrypted configuration value.
 */
//@FunctionalInterface
public interface ConfigOperator{

    /**
     * Method that creates a Configuration from another Configuration. This can be used for implementing
     * views, security constraints or for overriding/inheriting of configuration.
     * @param config The target configuration to be operated, never nnull.
     * @return the operated configuration, never null.
     */
    Configuration operate(Configuration config);

}
