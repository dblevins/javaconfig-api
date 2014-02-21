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
 * Adapter for evaluating something different from a property value.
 *
 * @param <T> the target type.
 * @author Anatole Tresch
 */
@FunctionalInterface
public interface PropertyAdapter<T>{
    /**
     * Adapt the given property value, to the type T.
     *
     * @param value the property value, never {@code
     *              null}
     * @return the adapte value, not {@code null}
     */
    public T adapt(String value);

}
