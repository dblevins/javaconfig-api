package javax.config.deploy;

import java.util.Collection;
import java.util.Map;

/**
 * Created by atsticks on 29.08.14.
 */
public interface PersistenceUnitConfig {

    String getName();
    String getDescription();
    String getMappingFile();
    String getJarFile();
    Collection<Class> getMappedClasses();
    Map<String,String> getProperties();

}
