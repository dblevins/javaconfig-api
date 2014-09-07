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

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Manager for {@link javax.config.Configuration} instances.
 *
 * @author Anatole Tresch
 */
public interface ConfigurationManager{

    /**
     * Gets a list with all configuration names defined.
     * @return all nameds, never null.
     */
    Collection<String> getConfigurationNames();

    /**
     * Allows to check if a configuration with a given name is defined.
     * @param name the configuration's name, not null, not empty.
     * @return true, if such a configuration is defined.
     */
    boolean isConfigurationDefined(String name);

    /**
     * Allows to check if a configuration with a given name is defined.
     * @param qualifiers the configuration's qualifiers, not null.
     * @return true, if such a configuration is defined.
     */
    boolean isConfigurationDefined(Annotation... qualifiers);

    /**
     * Access a configuration by name.
     * @param name the configuration's name, not null, not empty.
     * @return the corresponding Configuration instance, never null.
     * @throws javax.config.ConfigException if no such configuration is defined.
     */
    Configuration getConfiguration(String name);

    /**
     * Access a configuration.
     * @param qualifiers the configuration's qualifiers, not null.
     * @return the corresponding Configuration instance, never null.
     * @throws javax.config.ConfigException if no such configuration is defined.
     */
    Configuration getConfiguration(Annotation... qualifiers);
}
