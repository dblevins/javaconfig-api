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
 * This interface defines a configuration unit. The effective functionality and behavior of a concrete instances
 * is mainly determined by the implementation class.
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
public final class ConfiguredModelPart<T>{

    private Object key;
    private Class<T> partType;
    private String specification;
    private Map<String,String> attributes = new HashMap<>();
    private List<ConfiguredModelPart> childParts = new ArrayList<>();

    /**
     * Creates a new part from a Builder.
     * @param builder
     */
    private ConfiguredModelPart(Builder builder){
        this.key = builder.key;
        this.specification = builder.specification;
        this.partType = builder.partType;
        this.attributes.putAll(builder.attributes);
        this.childParts.addAll(builder.childParts);
    }

    /**
     * Get the configuration unit's name.
     *
     * @return the unit's name, not null.
     */
    public Object getKey(){
        return this.key;
    }

    /**
     * Get the part type, typically the interface tp be implemented by the implementation class name.
     *
     * @return the part type, never null.
     */
    public Class<T> getPartType(){
        return this.partType;
    }

    /**
     * Get the unit's implementation class name.
     *
     * @return the fully qualified implementation class name, not null
     */
    public String getSpecification(){
        return specification;
    }

    /**
     * Get an attribute's value.
     *
     * @param key the attribute's key, not null
     * @return the attribute's value, or null.
     */
    public String getAttribute(String key){
        return attributes.get(key);
    }

    /**
     * Method to determine if an attribute is set.
     *
     * @param key the attribute's key, not null
     * @return the attribute's value.
     * @throws java.lang.IllegalAccessException if no such attribute exists.
     */
    public boolean isAttributeSet(String key){
        return attributes.containsKey(key);
    }

    /**
     * Get all defined attribute keys.
     *
     * @return the attribute key's defined, not null.
     */
    public Set<String> getAttributeKeys(){
        return attributes.keySet();
    }

    /**
     * Access all attributes defined for this instance.
     *
     * @return all defined attributes, never {@code null}
     */
    public Map<String,String> getAttributes(){
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        ConfiguredModelPart that = (ConfiguredModelPart) o;

        if(!key.equals(that.key)){
            return false;
        }
        if(!partType.equals(that.partType)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        int result = key.hashCode();
        result = 31 * result + partType.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "ConfiguredModelPart{" +
                "key=" + key +
                ", partType=" + partType +
                ", specification='" + specification + '\'' +
                ", attributes=" + attributes +
                ", childParts=" + childParts +
                '}';
    }

    /**
     * Builder for creating instances of (immutable) ConfiguredModelPart.
     *
     * @param <T> the part type to be created
     */
    public static final class Builder<T>{
        /** the part's key, not null. */
        private Object key;
        /** the part's type, not null. */
        private Class<T> partType;
        /** the implementation class, may be null, for using the default. */
        private String specification;
        /** Any additional attributes configured. */
        private Map<String,String> attributes = new HashMap<>();
        /** The child parts of this part. */
        private List<ConfiguredModelPart> childParts = new ArrayList<>();

        /**
         * Creates a new Builder.
         *
         * @param key      the part's key, not null.
         * @param partType the part's type, not null.
         */
        public Builder(Object key, Class<T> partType){
            Objects.requireNonNull(key);
            Objects.requireNonNull(partType);
            this.key = key;
            this.partType = partType;
        }

        /**
         * Sets the specification implementing the {@code }partType}. This can be a fully qualified class name
         * of a custom implementation or a specification String, that must be interpreted by the according
         * part factory in place.
         *
         * @param specification the specification of the {@code }partType}, not null
         * @return the Builder instance for chaining
         */
        public Builder<T> setSpecification(String specification){
            Objects.requireNonNull(specification);
            this.specification = specification;
            return this;
        }

        /**
         * Sets an attribute on the unit, passed on initialization to the implementation,
         * allowing to configure a PropertySet.
         *
         * @param key   the attribute's key, not null
         * @param value the attribute's value, not null
         * @return the Builder instance for chaining
         */
        public Builder setAttribute(String key, String value){
            this.attributes.put(key, value);
            return this;
        }

        /**
         * Adds a child part to this part to model a configured hierarchy.
         *
         * @param childPart   the child part to be added, no null
         * @return the Builder instance for chaining
         */
        public Builder addChild(ConfiguredModelPart childPart){
            this.childParts.add(childPart);
            return this;
        }

        /**
         * Creates a new ConfiguredModelPart using the data from this Builder.
         *
         * @return a new ConfiguredModelPart, never null.
         */
        public ConfiguredModelPart<T> create(){
            return new ConfiguredModelPart<T>(this);
        }

        @Override
        public String toString(){
            return "ConfiguredModelPart.Builder{" +
                    "key=" + key +
                    ", partType=" + partType +
                    ", specification='" + specification + '\'' +
                    ", attributes=" + attributes +
                    ", childParts=" + childParts +
                    '}';
        }

    }


}
