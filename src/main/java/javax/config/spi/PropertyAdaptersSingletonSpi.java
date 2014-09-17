/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config.spi;

import javax.config.Configured;
import javax.config.PropertyAdapter;
import java.util.function.Function;

/**
 * SPI that is used by the {@link javax.config.PropertyAdapters} singleton as delegation instance.
 */
public interface PropertyAdaptersSingletonSpi{

    /**
     * Registers a new PropertyAdapter for the given target type, hereby replacing any existing adapter for
     * this type.
     * @param targetType The target class, not null.
     * @param adapter The adapter, not null.
     * @param <T> The target type
     * @return any adapter replaced with the new adapter, or null.
     */
    <T> PropertyAdapter<T> register(Class<T> targetType, PropertyAdapter<T> adapter);

    /**
     * Get an adapter converting to the given target type.
     * @param targetType the target type class
     * @return true, if the given target type is supported.
     */
    default <T> PropertyAdapter<T> getAdapter(Class<T> targetType){
        return getAdapter(targetType, null);
    }

    /**
     * Get an adapter converting to the given target type.
     * @param targetType the target type class
     * @param <T> the target type
     * @return the corresponding adapter, never null.
     * @throws javax.config.ConfigException if the target type is not supported.
     */
    <T> PropertyAdapter<T> getAdapter(Class<T> targetType, Configured annotation);

    /**
     * Checks if the given target type is supported, i.e. a adapter is registered and accessible.
     * @param targetType the target type class
     * @return true, if the given target type is supported.
     */
    boolean isTargetTypeSupported(Class<?> targetType);
}
