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

/**
 * Models a runtime environment. Instances of this class are used to
 * evaluate the correct configuration artifacts.<br/>
 * <h3>Implementation Requirements</h3>
 * <p>
 * Implementations of this interface must be
 * <ul>
 * <li>Thread safe.
 * <li>Immutable
 * <li>serializable
 * </ul>
 * 
 * @author Anatole Tresch
 */
public interface Environment extends PropertyProvider, StageSupplier, Iterable<Environment>{

    /**
     * Get a unique type (within this VM) for this environment.
     * Types represent the environment level within the hierarchy
     * of possible environments, e.g. {@code system, ear, webapp, tenant}.
     */
    String getEnvironmentType();

    /**
     * Get a unique name (in combination with the environment type within this VM)
     * for this environment instance.
     * Where a human readable name is available this would be preferable
     * over a technical key/UUID.
     * @return a unique id for this environment, when comined with the environment type.
     */
    String getEnvironmentId();

    /**
     * Get an qualified path to this environment instance, by appending the
     * current environment id and type (in backets) with the ones of its parent and so on, e.g.
     * <code>root[system].HumanOne[ear].rest[webapp].atsticks[user]</code>
     * @return the qualified path of this environment instance
     */
    String getEnvironmentPath();

    /**
     * Get the parent context.
     * @return the parent context, or null.
     */
    Environment getParentEnvironment();


}
