package javax.config.deploy;

import java.util.Collection;

/**
 * Created by atsticks on 29.08.14.
 */
public interface ConfiguredServerResources {
    Collection<ConfiguredDataSource> getDataSources();
    Collection<ConfiguredSecurityContext> getSecurityContexts();
}
