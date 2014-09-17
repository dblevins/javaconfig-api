/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

/**
 * Class describing the detailed change of an entry.
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
