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
package org.javaconfig.annot;

import org.javaconfig.PropertyAdapter;
import org.javaconfig.PropertyFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define an adapter to be used before injecting a configured value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.METHOD })
public @interface ConfigAdapter {

    /**
     * Define a custom adapter that should be used to adapt the configuration entry injected. This overrides any
     * general org.javaconfig.internal registered. If no adapter is defined (default) and no corresponding adapter is
     * registered, it is handled as a deployment error.
     */
    Class<? extends PropertyAdapter> value();

    /**
     * Enables the adapter to be configured additionally, e.g. a List adapter may be configured with the
     * list implementation type and the separator character used.
     * @return the array of parameters, to be interpreted by the ConfigAdapter implementation.
     */
    String[] params() default {};

}
