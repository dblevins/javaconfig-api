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

import javax.config.spi.Bootstrap;
import javax.config.spi.ConfigurationServiceSpi;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton accessor for accessing {@link javax.config.Configuration} instances. The
 * configurations provided hereby are managed by the most significant instance of {@link javax.config.spi
 * .ConfigurationServiceSpi} , registered into the JSR's {@link javax.config.spi.Bootstrap} service API.
 *
 * @author Anatole Tresch
 */
public final class ConfigurationServices{

    /**
     * SPI delegate of this singleton accessor, never null.
     */
    private volatile static ConfigurationServiceSpi servicesSpi = loadConfigurationServicesSpi();

    /**
     * Singleton constructor.
     */
    private ConfigurationServices(){
    }

    /**
     * Method used to load the SPI, if loading of {@link javax.config.spi
     * .ConfigurationServiceSpi} from the Bootstrap failed, a default implementation is returned
     * that will not provide any configurations (throwing IllegalStateException).
     *
     * @return the ConfigurationServiceSpi to be used by this singleton, never null.
     */
    private static ConfigurationServiceSpi loadConfigurationServicesSpi(){
        try{
            return Bootstrap.getService(ConfigurationServiceSpi.class);
        }
        catch(Exception e){
            Logger.getLogger(ConfigurationServices.class.getName())
                    .log(Level.WARNING, "Error loading ConfigurationServicesSpi, using default. No configurations " +
                            "will be accessible!", e);
        }
        return new DefaultConfigurationServiceSpi();
    }

    /**
     * Access the keys of all registered {@link Configuration} instances.
     *
     * @return all available configuration keys, never{@code null}.
     */
    public static Collection<ConfigId> getConfigurationIds(){
        return servicesSpi.getConfigurationIds();
    }

    /**
     * Access the current {@link Configuration}, matching to the current
     * {@link Environment}.
     *
     * @return the current {@link Configuration} corresponding to the
     * current {@code Environment}.
     * @see EnvironmentContext#getCurrentEnvironment()
     */
    public static Configuration getConfiguration(){
        return servicesSpi.getConfiguration(null);
    }

    /**
     * Access a {@link Configuration} using its key. It is recommended to use static constant values to identify
     * configurations, e.g. enum values. Using of Strings is basically possible, but discouraged, because spelling
     * errors are not detected by the compiler.
     *
     * @param configId The key of the required {@link Configuration}, not
     *            {@code null}.
     * @return the current {@link Configuration} corresponding to the
     * {@code key}.
     * @throws ConfigException if the required configuration is not defined or not
     *                         available.
     * @see EnvironmentContext#getCurrentEnvironment()
     */
    public static Configuration getConfiguration(ConfigId configId){
        return servicesSpi.getConfiguration(configId);
    }

    /**
     * Allows to check if a {@link Configuration} with the given id is
     * defined.
     *
     * @param configId The config Id of the required {@link Configuration}, not
     *            {@code null}.
     * @return true, if the given {@link Configuration} is defined.
     */
    public static boolean isConfigurationDefined(ConfigId configId){
        return servicesSpi.isConfigurationDefined(configId);
    }

    /**
     * Adds a global listener for configuration changes, duplicates are ignored. Listeners registered with this method
     * will be informed on every configuration change done (matching the same deployment context).
     *
     * @param l the listener to be added.
     */
    public static void addConfigChangeListener(ConfigChangeListener l){
        servicesSpi.addConfigChangeListener(l);
    }

    /**
     * Removes a global listener for configuration changes.
     *
     * @param l the listener to be removed.
     */
    public static void removeConfigChangeListener(ConfigChangeListener l){
        servicesSpi.removeConfigChangeListener(l);
    }

    /**
     * Resolves the annotated configuration resources on the given instance and injects according configuration
     * properties.
     *
     * @param instance the instance to be configured.
     * @throws ConfigException if configuration could not be resolved or converted.
     */
    public static void configure(Object instance){
        servicesSpi.configure(instance);
    }

    /**
     * Resolves the annotated configuration resources on the given instance and injects according configuration
     * properties.
     *
     * @param instance      to instance to be configured.
     * @param configuration The Configuration to be used to resolve the annotated properties.
     * @throws ConfigException if configuration could not be resolved or converted.
     */
    public static void configure(Object instance, Configuration configuration){
        servicesSpi.configure(instance, configuration);
    }

    /**
     * Internal default implementatio of the {@link javax.config.spi.ConfigurationServiceSpi} used,
     * if no valid SPI instance could be loaded from the {@link javax.config.spi.Bootstrap} API.
     */
    private static final class DefaultConfigurationServiceSpi implements ConfigurationServiceSpi{

        @Override
        public Collection<ConfigId> getConfigurationIds(){
            return Collections.emptySet();
        }

        @Override
        public Configuration getConfiguration(){
            throw new ConfigException("No default config (no ConfigService SPI registered).");
        }

        @Override
        public Configuration getConfiguration(ConfigId configId){
            throw new ConfigException("No such config (no ConfigService SPI registered): " + configId);
        }

        @Override
        public boolean isConfigurationDefined(ConfigId configId){
            return false;
        }

        @Override
        public void addConfigChangeListener(ConfigChangeListener l){
            throw new UnsupportedOperationException("No ConfigService SPI registered");
        }

        @Override
        public void removeConfigChangeListener(ConfigChangeListener l){
            throw new UnsupportedOperationException("No ConfigService SPI registered");
        }

        @Override
        public void configure(Object instance){
            throw new UnsupportedOperationException("No ConfigService SPI registered");
        }

        @Override
        public void configure(Object instance, Configuration configuration){
            throw new UnsupportedOperationException("No ConfigService SPI registered");
        }
    }

}
