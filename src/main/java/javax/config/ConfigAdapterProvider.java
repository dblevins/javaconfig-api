package javax.config;

import java.util.function.Function;

/**
 * Created by Anatole on 06.09.2014.
 */
public interface ConfigAdapterProvider{

    ConfigAdapterProvider register(Class<?> type, Function<String, ?> adapter);

    default <T> Function<String, T> getAdapter(Class<T> targetType){
        return getAdapter(targetType, null);
    }

    <T> Function<String, T> getAdapter(Class<T> targetType, Configured annotation);

}
