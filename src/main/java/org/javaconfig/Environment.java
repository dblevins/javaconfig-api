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
 * Models the current runtime environment. Instances of this class are used to
 * evaluate the correct configuration artifacts.<br/>
 * <h3>Implementation PropertyMapSpec</h3>
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
public interface Environment extends PropertyProvider, StageSupplier{

    /**
     * Get the parent context.
     * @return the parent context, or null.
     */
    Environment getParentEnvironment();


}
