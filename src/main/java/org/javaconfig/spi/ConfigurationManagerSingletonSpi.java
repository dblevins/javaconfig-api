/*
 * Copyright 2014 Credit Suisse and other (see authors).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.javaconfig.spi;

import org.javaconfig.ConfigChangeListener;
import org.javaconfig.Configuration;
import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Manager for {@link org.javaconfig.Configuration} instances. Implementations must register an instance
 * using the {@link org.javaconfig.spi.Bootstrap} mechanism in place (by default this is based on the {@link java.util.ServiceLoader}.
 * The {@link org.javaconfig.ConfigurationManager} Singleton in the API delegates its corresponding calls to the
 * instance returned by the current bootstrap service in place.
 *
 * @see org.javaconfig.ConfigurationManager
 * @see org.javaconfig.spi.Bootstrap
 * @author Anatole Tresch
 */
public interface ConfigurationManagerSingletonSpi{

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
     * @throws org.javaconfig.ConfigException if no such configuration is defined.
     */
    Configuration getConfiguration(String name);

    /**
     * Access a configuration.
     * @param qualifiers the configuration's qualifiers, not null.
     * @return the corresponding Configuration instance, never null.
     * @throws org.javaconfig.ConfigException if no such configuration is defined.
     */
    Configuration getConfiguration(Annotation... qualifiers);

    /**
     * Configures an instance, by resolving and injecting the configuration
     * entries.
     *
     * @param instance the instance with configuration annotations, not null.
     * @return the corresponding typed Configuration instance, never null.
     * @throws org.javaconfig.ConfigException if the configuration could not be resolved.
     */
    void configure(Object instance);

    /**
     * Access a typed configuration.
     *
     * @param type the annotated configuration type (could be an interface or
     *             a non abstract class), not null.
     * @return the corresponding typed Configuration instance, never null.
     * @throws org.javaconfig.ConfigException if the configuration could not be resolved.
     */
    <T> T getConfiguration(Class<T> type);

    /**
     * Evaluate the current expression based on the current configuration valid.
     * @param config     The configuration to be used for evaluating, using EL, not null.
     * @param expression the expression, not null.
     * @return the evaluated config expression.
     */
    String evaluateValue(Configuration config, String expression);

    /**
     * Adds a (global) {@link org.javaconfig.ConfigChangeListener} instance that listens to all kind of config changes.
     * @param listener the {@link org.javaconfig.ConfigChangeListener} instance to be added, not null.
     */
    void addConfigChangeListener(ConfigChangeListener listener);

    /**
     * Removes a (global) {@link org.javaconfig.ConfigChangeListener} instance that listens to all kind of config changes,
     * if one is currently registered.
     * @param listener the {@link org.javaconfig.ConfigChangeListener} instance to be removed, not null.
     */
    void removeConfigChangeListener(ConfigChangeListener listener);

}
