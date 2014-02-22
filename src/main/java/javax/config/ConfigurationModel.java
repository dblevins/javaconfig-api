/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE.
 *
 * Specification: JSR-xxx Java Configuration API ("Specification")
 *
 * Copyright (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.util.*;

/**
 * This interface defines a configuration model that defines an arbitrary set of ConfigurationPart instances.
 * Hereby the basic part types used by default are:<br/>
 * <ul>
 * <li>Configuration</li>
 * <li>PropertySet</li>
 * </ul>
 * Additional types can be added as useful, e.g. for configuring advanced requirements.
 * <br/>
 * <h3>Implementation Specification</h3>
 * <p>
 * Implementations of this interface must be:
 * <ul>
 * <li>Serializable</li>
 * <li>Thread-safe</li>
 * </ul>
 * Though not required, it is also highly recommended that implementations are immutable.
 * </p>
 */
public final class ConfigurationModel{

    /** Default specification of ConfigurationPart. */
    public static final String DEFAULT_SPEC = "<default>";

    /**
     * The meta model's name, never null.
     */
    private String name;
    /**
     * The configured parts.
     */
    private Map<Class<?>,Map<Object,ConfiguredModelPart<?>>> configuredParts = new HashMap<>();

    private ConfigurationModel(Builder builder){
        this.name = builder.name;

    }

    /**
     * Return the name of the meta model.
     *
     * @return the meta model name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Access configured configuration part.
     *
     * @param partType the required partType
     * @return the configured parts matching, never null
     */
    public <T> Collection<ConfiguredModelPart<T>> getParts(Class<T> partType){
        Map<Object,ConfiguredModelPart<T>> parts = Map.class.cast(configuredParts.get(partType));
        if(parts == null){
            return Collections.emptySet();
        }
        return parts.values();
    }

    /**
     * Access all the configured part types.
     *
     * @return the part types, never null
     */
    public Collection<Class<?>> getConfiguredPartTypes(){
        return this.configuredParts.keySet();
    }

    /**
     * Access a ConfiguredModelPart.
     *
     * @param key      the configuration part's key.
     * @param partType the required partType
     * @return the corresponding Config, or null.
     */
    public <T> ConfiguredModelPart<T> getConfiguredPart(Object key, Class<T> partType){
        Map<Object,ConfiguredModelPart<T>> parts = Map.class.cast(configuredParts.get(partType));
        if(parts == null){
            return null;
        }
        return parts.get(key);
    }

    /**
     * Allows to check, if a configuration unit with the given name is defined.
     *
     * @param key      the unit's key, not null
     * @param partType the required partType
     * @return true, if such a configuration unit is defined.
     */
    public <T> boolean isPartConfigured(Object key, Class<T> partType){
        Map<Object,ConfiguredModelPart<T>> parts = Map.class.cast(configuredParts.get(partType));
        if(parts == null){
            return false;
        }
        return parts.containsKey(key);
    }

    /**
     * Get the names of configuration units defined in this meta model.
     *
     * @return the names of configuration units defined
     */
    public <T> Set<Object> getConfiguredPartKeys(Class<T> partType){
        Map<Object,ConfiguredModelPart<T>> parts = Map.class.cast(configuredParts.get(partType));
        if(parts == null){
            return Collections.emptySet();
        }
        return parts.keySet();
    }

    @Override
    public String toString(){
        return "ConfigurationModel{" +
                "partTypes=" + getConfiguredPartTypes() +
                "configuredParts=" + configuredParts +
                '}';
    }

    public ConfigurationModel mergeModel(ConfigurationModel model){
        // TODO implement this merge functionality later
        return this;
    }

    /**
     * Builder to create a new meta model.
     */
    public static final class Builder{
        /**
         * The meta model's name, never null.
         */
        private String name;
        /**
         * The configured parts.
         */
        private Map<Class<?>,Map<Object,ConfiguredModelPart<?>>> configuredParts = new HashMap<>();

        /**
         * Creates a new builder.
         *
         * @param name the meta model's name, not null.
         */
        public Builder(String name){
            Objects.requireNonNull(name);
            this.name = name;
        }

        public Builder setPartTypes(Class<?>... partTypes){
            for(Class<?> partType : partTypes){
                getPartMap(partType);
            }
            return this;
        }

        public Builder add(ConfiguredModelPart<?> part){
            Objects.requireNonNull(part);
            Map<Object,ConfiguredModelPart<?>> parts = getPartMap(part.getPartType());
            parts.put(part.getKey(), part);
            return this;
        }

        private Map<Object,ConfiguredModelPart<?>> getPartMap(Class<?> partType){
            Map<Object,ConfiguredModelPart<?>> parts =configuredParts.get(partType);
            if(parts == null){
                parts = new HashMap<>();
                configuredParts.put(partType, parts);
            }
            return  Map.class.cast(parts);
        }

        /**
         * Creates a new meta model based on the current Builder's daa.
         *
         * @return a new model instance, never null.
         */
        public ConfigurationModel create(){
            return new ConfigurationModel(this);
        }

        @Override
        public String toString(){
            return "ConfigurationModel.Builder{" +
                    "name='" + name + '\'' +
                    ", configuredParts=" + configuredParts +
                    '}';
        }
    }

}
