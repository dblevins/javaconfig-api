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
 * Class describing the detailed change of an configuration property.
 */
public interface ConfigChange{

    /**
     * Get the entries absolute key.
     * @return the key, never null.
     */
    String getKey();

    /**
     * Get the current value of the entry affected.
     *
     * @return the entry's value, may also be {@code null}.
     */
    String getValue();

    /**
     * Get the previous value of the entry affected.
     *
     * @return the entry's previous value, may also be {@code null}.
     */
    String getPreviousValue();

    /**
     * Get the update type of this entry.
     *
     * @return the update type, never null.
     */
    default ConfigChangeType getUpdateType(){
        if(getPreviousValue() == null){
            return ConfigChangeType.ADDED;
        }else if(getValue() == null){
            return ConfigChangeType.REMOVED;
        }
        return ConfigChangeType.UPDATED;
    }

}
