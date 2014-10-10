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

import java.util.*;

/**
 * Event distributed when a {@link PropertyProvider} or {@link Configuration} has been changed, removed or added.
 *
 * @author Anatole Tresch
 */
public interface ConfigChangeEvent{

    /**
     * The {@link ConfigChange} instances of this change.
     *
     * @return the  {@link ConfigChange} instances, never {@code null}.
     */
    Collection<ConfigChange> getChanges();

    /**
     * Access the {@link Configuration} affected.
     *
     * @return the configuration, never {@code null}.
     */
    Configuration getConfiguration();

    /**
     * Access the optional meta data.
     *
     * @return the meta data, never {@code null}.
     */
    Map<String,String> getMetaData();

}
