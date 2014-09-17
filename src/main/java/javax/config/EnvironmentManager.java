package javax.config;

import javax.config.spi.Bootstrap;
import javax.config.spi.EnvironmentManagerSingletonSpi;
import java.util.Optional;

/**
 * Created by Anatole on 09.09.2014.
 */
public final class EnvironmentManager{

    private static final EnvironmentManagerSingletonSpi contextProvider = loadContextProviderSpi();

    /**
     * Private singleton constructor.
     */
    private EnvironmentManager(){}


    /**
     * Method to load the environment SPI during initial load.
     * @return the EnvironmentProviderSpi SPI, never null.
     */
    private static EnvironmentManagerSingletonSpi loadContextProviderSpi(){
        return Bootstrap.getService(EnvironmentManagerSingletonSpi.class);
    }

    /**
     * Get the current {@link javax.config.Environment}. The environment is used to determine the current runtime state, which
     * is important for returning the correct configuration.
     * @return the current Environment, never null.
     */
    public static Environment getEnvironment(){
        return Optional.of(contextProvider).get().get();
    }

    /**
     * Get the current root (startup/machine/VM) {@link javax.config.Environment}.
     * @return the current root Environment, never null.
     */
    public static Environment getRootEnvironment(){
        return Optional.of(contextProvider).get().getRootEnvironment();
    }

}
