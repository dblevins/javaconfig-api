package javax.config.deploy;

import java.util.Collection;

/**
 * Created by atsticks on 29.08.14.
 */
public interface EJBJarConfiguration {

    Collection<EJBConfiguration> getEJBConfigs();
//    List<Class<? extends Interceptor>> getInterceptorClasses();
    AssemblyDescriptor getAssemblyDescriptor();
    
}
