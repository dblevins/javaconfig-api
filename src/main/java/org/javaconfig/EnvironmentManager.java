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
package org.javaconfig;

import org.javaconfig.spi.Bootstrap;
import org.javaconfig.spi.EnvironmentManagerSingletonSpi;


/**
 * Singleton accessor class for the current environment.
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
     * Get the current {@link Environment}. The environment is used to determine the current runtime state, which
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
     * Get the current root (startup/machine/VM) {@link Environment}.
     * @return the current root Environment, never null.
     */
    public static Environment getRootEnvironment(){
        if(environmentManagerSingletonSpi==null){
            throw new IllegalStateException("No SPI loaded.");
        }
        return environmentManagerSingletonSpi.getRootEnvironment();
    }

}
