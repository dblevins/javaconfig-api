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
 * Interface for an filter/operator that converts a configured String into another String. One typical
 * use case would the the decryption of an encrypted configuration value.
 */
@FunctionalInterface
public interface PropertyFilter{

    /**
     * Filters the given property value to a new value.
     * @param propertyValue the value to be filtered.
     * @return the filtered value, not null.
     */
    String filter(String propertyValue);

}
