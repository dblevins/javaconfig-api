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
 * Interface for an query that converts a Configuration into another object. One typical
 * use cases would creating a complex configuration parameter type from a Configuration instance or
 * constraint views on configuration.
 */
//@FunctionalInterface
public interface ConfigQuery<T>{

    /**
     * Queries the given configuration.
     * @param config the configuration to be wuiried, not null.
     * @return the result T.
     */
    T query(Configuration config);

}
