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
package org.javaconfig.internal;

import org.javaconfig.Environment;
import org.javaconfig.spi.EnvironmentManagerSingletonSpi;

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