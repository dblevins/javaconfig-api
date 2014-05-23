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

import java.util.Collection;

/**
 * Accessor for accessing {@link javax.config.Configuration} instances. The
 * configurations provided hereby are managed by the most significant instance of {@link javax.config.spi
 * .ConfigurationServiceSpi} , registered into the JSR's {@link javax.config.spi.Bootstrap} service API.
 *
 * @author Anatole Tresch
 */
public interface ConfigurationManager{

    /**
     * Access the current {@link Configuration}, matching to the current
     * {@link Environment}.
     *
     * @return the current {@link Configuration} corresponding to the
     * current {@code Environment}.
     * @see EnvironmentManager#getCurrentEnvironment()
     */
    public Configuration getConfiguration();

    /**
     * Access the current {@link Configuration}, matching to the current
     * {@link Environment}.
     * @param environment The target environment.
     * @return the current {@link Configuration} corresponding to the
     * current {@code Environment}.
     * @see EnvironmentManager#getCurrentEnvironment()
     */
    public Configuration getConfiguration(Environment environment);

//    /**
//     * Access the keys of all registered {@link Configuration} instances.
//     *
//     * @return all available configuration keys, never{@code null}.
//     */
//    public Collection<String> getConfigurationIds();
//
//    /**
//     * Access a {@link Configuration} using its key. It is recommended to use static constant values to identify
//     * configurations, e.g. enum values. Using of Strings is basically possible, but discouraged, because spelling
//     * errors are not detected by the compiler.
//     *
//     * @param configId The key of the required {@link Configuration}, not
//     *            {@code null}.
//     * @return the current {@link Configuration} corresponding to the
//     * {@code key}.
//     * @throws ConfigException if the required configuration is not defined or not
//     *                         available.
//     * @see EnvironmentManager#getCurrentEnvironment()
//     */
//    public Configuration getConfiguration(String configId);
//
//    /**
//     * Allows to check if a {@link Configuration} with the given id is
//     * defined.
//     *
//     * @param configId The config Id of the required {@link Configuration}, not
//     *            {@code null}.
//     * @return true, if the given {@link Configuration} is defined.
//     */
//    public boolean isConfigurationDefined(String configId);


}
