package internal;

import javax.config.Environment;
import javax.config.spi.EnvironmentManagerSingletonSpi;

/**
 * Created by Anatole on 12.09.2014.
 */
public class TestEnvironmentManagerSingleton implements EnvironmentManagerSingletonSpi{
    @Override
    public Environment getEnvironment(){
        return null;
    }

    @Override
    public Environment getRootEnvironment(){
        return null;
    }
}
