/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
 * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
 * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
 * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
 * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
 * (c) 2012-2013, Credit Suisse All rights reserved.
 */
package javax.config;

import javax.config.spi.Bootstrap;
import javax.config.spi.PropertyAdaptersSingletonSpi;
import java.util.Optional;
import java.util.function.Function;

/**
 * Singleton accessor that provides {@link javax.config.PropertyAdapter} instance, usable for converting String
 * based configuration entries into any other target types.
 */
public final class PropertyAdapters{
    /** The SPI finally registered backing this singleton. */
    private static final PropertyAdaptersSingletonSpi propertyAdaptersSingletonSpi = loadConfigAdapterProviderSpi();

    /**
     * Method that loads the singleton backing bean from the {@link javax.config.spi.Bootstrap} component.
     * @return the PropertyAdaptersSingletonSpi, never null.
     */
    private static PropertyAdaptersSingletonSpi loadConfigAdapterProviderSpi(){
        return Bootstrap.getService(PropertyAdaptersSingletonSpi.class);
    }

    /**
     * Orivate singleton constructor.
     */
    private PropertyAdapters(){}

    /**
     * Registers a new PropertyAdapter for the given target type, hereby replacing any existing adapter for
     * this type.
     * @param targetType The target class, not null.
     * @param adapter The adapter, not null.
     * @param <T> The target type
     * @return any adapter replaced with the new adapter, or null.
     */
    public static <T> PropertyAdapter<T> register(Class<T> targetType, PropertyAdapter<T> adapter){
        return Optional.of(propertyAdaptersSingletonSpi).get().register(targetType, adapter);
    }

    /**
     * Get an adapter converting to the given target type.
     * @param targetType the target type class
     * @return true, if the given target type is supported.
     */
    public static boolean isTargetTypeSupported(Class<?> targetType){
        return Optional.of(propertyAdaptersSingletonSpi).get().isTargetTypeSupported(targetType);
    }

    /**
     * Get an adapter converting to the given target type.
     * @param targetType the target type class
     * @param <T> the target type
     * @return the corresponding adapter, never null.
     * @throws javax.config.ConfigException if the target type is not supported.
     */
    public static  <T> PropertyAdapter<T> getAdapter(Class<T> targetType){
        return getAdapter(targetType, null);
    }

    /**
     * Get an adapter converting to the given target type.
     * @param targetType the target type class
     * @param annotation the {@link javax.config.Configured} annotation, or null. If the annotation is not null and
     *                   defines an overriding adapter, this instance is created and returned.
     * @param <T> the target type
     * @return the corresponding adapter, never null.
     * @throws javax.config.ConfigException if the target type is not supported, or the overriding adapter cannot be
     * instantiated.
     */
    public static  <T> PropertyAdapter<T> getAdapter(Class<T> targetType, Configured annotation){
        return Optional.of(propertyAdaptersSingletonSpi).get().getAdapter(targetType, annotation);
    }

}
