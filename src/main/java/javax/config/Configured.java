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
 * Annotation to enable injection of a configured property.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.METHOD })
public @interface Configured{

    /**
     * Get the property names to be used. Hereby the first non null value evaluated is injected as property value.
     *
     * @return the property names, not null. If missing the field or method name being injected is used by default.
     */
    String[] value() default {};

    /**
     * The default value to be injected, if no such configuration entry was found. If value was found and no default
     * is defined, it is handled as a deployment error.
     */
    String defaultValue() default "";

    /**
     * Define a custom adapter that should be used to adapt the configuration entry injected. This overrides any
     * general internal registered. If no adapter is defined (default) and no corresponding adapter is
     * registered, it is handled as a deployment error.
     */
    Class<? extends PropertyAdapter> adapter() default PropertyAdapter.class;

    /**
     * Define a custom filter/converter that should be applied, e.g. a decryption algorithm for encrypted passwords.
     * By default the configured value is used without any operating.
     */
    Class<? extends PropertyFilter> filter() default PropertyFilter.class;

    boolean triggerChanges() default true;
}
