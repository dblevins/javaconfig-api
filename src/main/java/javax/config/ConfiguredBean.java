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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to control injection and resolution of a configured bean.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface ConfiguredBean{

    /**
     * Get the area names that should be used to resolve the entries in the given bean.
     *
     * @return the target area names, not null. By default not area names are prepended thus the configured
     * properties must be absolutely named.
     */
    String[] configAreas() default {};

    /**
     * Handler that determine how to react on config changes. By default no handlers is registered, so all changes are
     * applied and change events are published to the listening components.
     * @return the handler class to be instantiated.
     */
    Class<? extends ConfigChangeHandler> configChangeHandler() default ConfigChangeHandler.class;

    /**
     * Allows to declare an operator that should be applied before injecting values into the bean.
     * @return the operator class to be used.
     */
    Class<? extends ConfigOperator> configOperator() default ConfigOperator.class;

}
