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
 * Service for accessing ConfigurationServiceSpi instances. A ConfigurationServiceSpi provides access to the
 * different configurations loaded. Hereby several ConfigurationServiceSpi models can be active at the
 * same time.
 *
 * @author Anatole Tresch
 */
public final class ConfigurationServices{

    /**
     * SPI delegate of this singleton class.
     */
    private volatile static ConfigurationServiceSpi servicesSpi = loadConfigurationServicesSpi();

    /**
     * Singleton constructor.
     */
    private ConfigurationServices(){
    }

    private static ConfigurationServiceSpi loadConfigurationServicesSpi(){
        try{
            return Bootstrap.getService(ConfigurationServiceSpi.class);
        }
        catch(Exception e){
            Logger.getLogger(ConfigurationServices.class.getName())
                    .log(Level.WARNING, "Error loading ConfigurationServicesSpi", e);
        }
        return new DefaultConfigurationServiceSpi();
    }

    /**
     * Access all defined {@link Configuration} keys.
     *
     * @return all available configuration keys, never{@code null}.
     */
    public static Collection<Object> getConfigurationKeys(){
        return servicesSpi.getConfigurationKeys();
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
        return servicesSpi.getConfiguration(Configuration.class);
    }

    /**
     * Access a {@link Configuration} by name, matching to the current
     * {@link Environment}.
     *
     * @param key The key of the required {@link Configuration}, not
     *            {@code null}.
     * @return the current {@link Configuration} corresponding to the
     * {@code configId}.
     * @throws ConfigException if the required configuration is not defined or not
     *                         available.
     * @see EnvironmentContext#getCurrentEnvironment()
     */
    public static Configuration getConfiguration(Object key){
        return servicesSpi.getConfiguration(key);
    }

    /**
     * Allows to check if a {@link Configuration} with the given id is
     * defined.
     *
     * @param key The key of the required {@link Configuration}, not
     *            {@code null}.
     * @return true, if the given {@link Configuration} is defined.
     */
    public static boolean isConfigurationDefined(Object key){
        return servicesSpi.isConfigurationDefined(key);
    }

    /**
     * Adds a listener for configuration changes, duplicates are ignored.
     *
     * @param l the listener to be added.
     */
    public static  void addConfigChangeListener(ConfigChangeListener l){
        servicesSpi.addConfigChangeListener(l);
    }

    /**
     * Removes a listener for configuration changes.
     *
     * @param l the listener to be removed.
     */
    public static void removeConfigChangeListener(ConfigChangeListener l){
        servicesSpi.removeConfigChangeListener(l);
    }

    /**
     * Resolved the annotated configuration resources on the given instance.
     *
     * @param instance to POJO instance to be configured.
     * @throws IllegalArgumentException if configuration could not be resolved, or converted.
     */
    public static void configure(Object instance){
        servicesSpi.configure(instance);
    }

    /**
     * Resolved the annotated configuration resources on the given instance.
     *
     * @param instance      to POJO instance to be configured.
     * @param configuration The Configuration to be used.
     * @throws IllegalArgumentException if configuration could not be resolved, or converted.
     */
    public static void configure(Object instance, Configuration configuration){
        servicesSpi.configure(instance, configuration);
    }

    private static final class DefaultConfigurationServiceSpi implements ConfigurationServiceSpi{

        @Override
        public Collection<Object> getConfigurationKeys(){
            return Collections.emptySet();
        }

        @Override
        public Configuration getConfiguration(){
            throw new ConfigException("No default config (no ConfigService SPI registered).");
        }

        @Override
        public Configuration getConfiguration(Object key){
            throw new ConfigException("No such config (no ConfigService SPI registered): " + key);
        }

        @Override
        public boolean isConfigurationDefined(Object key){
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
