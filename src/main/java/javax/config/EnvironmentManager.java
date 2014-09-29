package javax.config;

import javax.config.spi.Bootstrap;
import javax.config.spi.EnvironmentManagerSingletonSpi;


/**
 * Created by Anatole on 09.09.2014.
 */
public final class EnvironmentManager{

    private static final EnvironmentManagerSingletonSpi environmentManagerSingletonSpi = loadContextProviderSpi();

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
        if(environmentManagerSingletonSpi==null){
            throw new IllegalStateException("No SPI loaded.");
        }
        return environmentManagerSingletonSpi.getEnvironment();
    }

    /**
     * Get the current root (startup/machine/VM) {@link javax.config.Environment}.
     * @return the current root Environment, never null.
     */
    public static Environment getRootEnvironment(){
        if(environmentManagerSingletonSpi==null){
            throw new IllegalStateException("No SPI loaded.");
        }
        return environmentManagerSingletonSpi.getRootEnvironment();
    }

}
