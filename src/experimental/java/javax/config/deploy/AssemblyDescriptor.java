package javax.config.deploy;

import java.util.Collection;

/**
 * Created by atsticks on 29.08.14.
 */
public interface AssemblyDescriptor {
    Collection<InterceptorBinding> getInterceptorBindings();
}
