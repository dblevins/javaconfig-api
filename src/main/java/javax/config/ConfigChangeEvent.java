/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import java.io.Serializable;
import java.util.*;

/**
 * Event distributed when a {@link Configuration} has been changed, removed or added.
 *
 * @author Anatole Tresch
 */
public final class ConfigChangeEvent{
    /**
     * The {@link javax.config.ConfigChangeEvent.UpdateType} of change.
     */
    private final UpdateType changeUpdateType;
    /**
     * The affected {@link Configuration}-
     */
    private final Configuration configuration;
    /**
     * Additional event meta data.
     */
    private final Map<String,String> metaData;
    /**
     * A detailed list of the changes encountered.
     */
    private final Map<String,ChangeDetail> delta = new HashMap<>();

    /**
     * Creates a new instances from the {@link Builder}.
     *
     * @param builder the builder with the value to be set.
     */
    private ConfigChangeEvent(Builder builder){
        Objects.requireNonNull(builder.configuration);
        Objects.requireNonNull(builder.changeUpdateType);
        this.configuration = builder.configuration;
        this.changeUpdateType = builder.changeUpdateType;
        this.metaData = builder.metaData;
        this.delta.putAll(builder.delta);
    }

    /**
     * The {@link javax.config.ConfigChangeEvent.UpdateType} of {@link Configuration} change.
     *
     * @return the changeUpdateType, never {@code null}.
     */
    public final UpdateType getChangeUpdateType(){
        return changeUpdateType;
    }

    /**
     * Access the {@link Configuration} affected.
     *
     * @return the configuration, never {@code null}.
     */
    public final Configuration getConfiguration(){
        return configuration;
    }

    /**
     * Access the optional meta data.
     *
     * @return the meta data, never {@code null}.
     */
    public Map<String,String> getMetaData(){
        return Collections.unmodifiableMap(metaData);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "ConfigChangeEvent [configuration=" + configuration + ", changeUpdateType=" + changeUpdateType + ", metaData=" +
                metaData + ", delta=" + delta + "]";
    }

    /**
     * Basic type of change.
     */
    public static enum UpdateType{
        /**
         * The new and old configuration show differences in some keys.
         */
        UPDATED,
        /**
         * The configuration was removed.
         */
        REMOVED,
        /**
         * The configuration has been added.
         */
        ADDED
    }

    /**
     * Class describing the detailed change of an entry.
     */
    public static final class ChangeDetail implements Serializable{
        /**
         * serial version.
         */
        private static final long serialVersionUID = -1602542417894307122L;
        /** Cache for change detail instances. */
        private static final Map<String,ChangeDetail> CACHE = new LinkedHashMap<String,ChangeDetail>(100, 0.9f, true){
            private static final long serialVersionUID = -1602542426994307092L;
            private static final int MAX_SIZE = 500;

            @Override
            protected boolean removeEldestEntry(Map.Entry<String,ChangeDetail> eldest){
                if(size()>MAX_SIZE){
                    return true;
                }
                return false;
            }
        };

        /**
         * The current value.
         */
        private final String value;
        /**
         * The previous value.
         */
        private final String previousValue;

        /**
         * Creates a new instance.
         *
         * @param value    the entry's current value, != oldValue.
         * @param oldValue the entry's previous value, != value.
         */
        private ChangeDetail(String value, String oldValue){
            if(oldValue == value){
                throw new IllegalArgumentException("oldValue==value");
            }
            if(oldValue != null){
                if(oldValue.equals(value)){
                    throw new IllegalArgumentException("oldValue.equals(value)");
                }
            }else{
                if(value.equals(oldValue)){
                    throw new IllegalArgumentException("value.equals(oldValue)");
                }
            }
            this.previousValue = oldValue;
            this.value = value;
        }

        public static final ChangeDetail of(String value, String oldValue){
            String key = buildKey(value, oldValue);
            ChangeDetail det = CACHE.get(key);
            if(det == null){
                det = new ChangeDetail(value, oldValue);
                CACHE.put(key, det);
            }
            return det;
        }

        private static String buildKey(String value, String oldValue){
            StringBuilder b = new StringBuilder(256);
            b.append(oldValue == null ? "<null>" : oldValue);
            b.append("->");
            b.append(value == null ? "<null>" : value);
            return b.toString();
        }

        /**
         * Get the current value of the entry affected.
         *
         * @return the entry's value, may also be {@code null}.
         */
        public final String getValue(){
            return value;
        }

        /**
         * Get the previous value of the entry affected.
         *
         * @return the entry's previous value, may also be {@code null}.
         */
        public final String getPreviousValue(){
            return previousValue;
        }

        /**
         * Get the update type of this entry.
         * @return the update type, never null.
         */
        public UpdateType getUpdateType(){
            if(previousValue==null){
                return UpdateType.ADDED;
            }
            else if(value==null){
                return UpdateType.REMOVED;
            }
            return UpdateType.UPDATED;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode(){
            final int prime = 31;
            int result = 1;
            result = prime * result + ((previousValue == null) ? 0 : previousValue.hashCode());
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj){
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            ChangeDetail other = (ChangeDetail) obj;
            if(previousValue == null){
                if(other.previousValue != null)
                    return false;
            }else if(!previousValue.equals(other.previousValue))
                return false;
            if(value == null){
                if(other.value != null)
                    return false;
            }else if(!value.equals(other.value))
                return false;
            return true;
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString(){
            return "ChangeDetail [" + buildKey(value, previousValue) + "]";
        }

    }

    public static final class Builder{
        private final Map<String,ChangeDetail> delta = new HashMap<>();
        private UpdateType changeUpdateType;
        private Configuration configuration;
        private Map<String,String> metaData = new HashMap<>();

        public Builder(Configuration configuration, UpdateType changeUpdateType){
            Objects.requireNonNull(configuration);
            this.configuration = configuration;
        }

        public Builder addChange(String key, ChangeDetail detail){
            Objects.requireNonNull(key);
            Objects.requireNonNull(detail);
            this.delta.put(key, detail);
            return this;
        }

        public Builder addChangeMetaData(String key, String value){
            this.metaData.put(key, value);
            return this;
        }

        public Builder removeChangeMetaData(String key){
            this.metaData.remove(key);
            return this;
        }

        public ConfigChangeEvent create(){
            return new ConfigChangeEvent(this);
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString(){
            return "Builder [configuration=" + configuration + ", updateType=" + changeUpdateType + ", " +
                    "metaData=" + metaData +
                    ", delta=" + delta + "]";
        }

    }
}
