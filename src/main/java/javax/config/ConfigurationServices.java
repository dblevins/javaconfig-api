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
import javax.config.spi.ConfigurationServicesSpi;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for accessing ConfigurationService instances. A ConfigurationService provides access to the
 * different configurations loaded as defined by a meta model. Hereby several meta models can be active at the
 * same time.
 *
 * @author Anatole Tresch
 */
public final class ConfigurationServices{

    /**
     * SPI delegate of this singleton class.
     */
    private volatile static ConfigurationServicesSpi servicesSpi = loadConfigurationServicesSpi();

    /**
     * Singleton constructor.
     */
    private ConfigurationServices(){
    }

    private static ConfigurationServicesSpi loadConfigurationServicesSpi(){
        try{
            return Bootstrap.getService(ConfigurationServicesSpi.class);
        }
        catch(Exception e){
            Logger.getLogger(ConfigurationServices.class.getName())
                    .log(Level.WARNING, "Error loading ConfigurationServicesSpi", e);
        }
        return new DefaultConfigurationServicesSpi();
    }

    public static ConfigurationModel getMetaModel(String metaModelName){
        return servicesSpi.getMetaModel(metaModelName);
    }

    /**
     * Access all defined {@link javax.config.ConfigurationModel} keys.
     *
     * @return all available ConfigurationModel keys, never{@code null}.
     */
    public static Collection<String> getMetaModelNames(){
        return servicesSpi.getMetaModelNames();
    }

    /**
     * Access a {@link javax.config.ConfigurationService} by name.
     *
     * @param name the meta model's name, not null
     * @return the coresponding {@link javax.config.ConfigurationService} corresponding to the
     * defined {@code ConfigurationModel}.
     */
    public static final ConfigurationService getService(String name){
        ConfigurationService cfgService = servicesSpi.getService(name);
        if(cfgService == null){
            throw new IllegalArgumentException("No such conguration service: " + name);
        }
        return cfgService;
    }

    /**
     * Allows to check if a {@link javax.config.Configuration} with the given id is
     * defined.
     *
     * @param name The name of the service required {@link javax.config.ConfigurationService}, not
     *             {@code null}.
     * @return true, if the given {@link javax.config.ConfigurationService} is defined.
     */
    public static boolean isServiceDefined(String name){
        return servicesSpi.getService(name) != null;
    }

    private static final class DefaultConfigurationServicesSpi implements ConfigurationServicesSpi{
        @Override
        public ConfigurationModel getMetaModel(String metaModelName){
            throw new IllegalArgumentException("No such Configuration Meta Model: " + metaModelName);
        }

        @Override
        public Collection<String> getMetaModelNames(){
            return Collections.emptySet();
        }

        @Override
        public ConfigurationService getService(String name){
            return null;
        }
    }

}
