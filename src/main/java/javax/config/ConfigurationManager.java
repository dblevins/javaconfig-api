package javax.config;

import javax.config.spi.Bootstrap;
import javax.config.spi.ConfigurationManagerSingletonSpi;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Optional;

/**
 * Singleton accessor for accessing {@link javax.config.Configuration} instances.
 */
public final class ConfigurationManager{
    /** The backing SPI instance. */
    private static final ConfigurationManagerSingletonSpi configProvider = loadConfigServiceSingletonSpi();

    /**
     * Private singleton constructor.
     */
    private ConfigurationManager(){}

    /**
     * Method to initially load the singleton SPI.
     * @return the SPI, never null.
     */
    private static ConfigurationManagerSingletonSpi loadConfigServiceSingletonSpi(){
        return Bootstrap.getService(ConfigurationManagerSingletonSpi.class);
    }

    /**
     * Allows to check if a configuration with a given name is defined.
     * @param name the configuration's name, not null, not empty.
     * @return true, if such a configuration is defined.
     */
    public static boolean isConfigurationDefined(String name){
        return Optional.of(configProvider).get().isConfigurationDefined(name);
    }

    /**
     * Allows to check if a configuration with a given name is defined.
     * @param qualifiers the configuration's qualifiers, not null.
     * @return true, if such a configuration is defined.
     */
    public static boolean isConfigurationDefined(Annotation... qualifiers){
        return Optional.of(configProvider).get().isConfigurationDefined(qualifiers);
    }

    /**
     * Access a configuration by name.
     * @param name the configuration's name, not null, not empty.
     * @return the corresponding Configuration instance, never null.
     * @throws javax.config.ConfigException if no such configuration is defined.
     */
    public static Configuration getConfiguration(String name){
        return Optional.of(configProvider).get().getConfiguration(name);
    }

    /**
     * Access a configuration.
     * @param qualifiers the configuration's qualifiers, not null.
     * @return the corresponding Configuration instance, never null.
     * @throws javax.config.ConfigException if no such configuration is defined.
     */
    public static Configuration getConfiguration(Annotation... qualifiers){
        return Optional.of(configProvider).get().getConfiguration(qualifiers);
    }

    /**
     * Evaluate the current expression based on the current configuration valid.
     * @param expression the expression, not null.
     * @return the evaluated config expression.
     */
    public static String evaluateValue(String expression){
        return Optional.of(configProvider).get().evaluateValue(expression);
    }
}
