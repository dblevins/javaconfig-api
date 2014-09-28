package javax.config.deploy;

import java.util.Collection;

/**
 * Created by atsticks on 29.08.14.
 */
public interface JPAConfiguration {

    /**
     * <persistence-unit name="my-pu">
     <description>My Persistence Unit</description>
     <provider>com.objectdb.jpa.Provider</provider>
     <mapping-file>META-INF/mappingFile.xml</mapping-file>
     <jar-file>packedEntity.jar</jar-file>
     <class>sample.MyEntity1</class>
     <class>sample.MyEntity2</class>
     <properties>
     <property name="javax.persistence.jdbc.url"
     value="objectdb://localhost/my.odb"/>
     <property name="javax.persistence.jdbc.user" value="admin"/>
     <property name="javax.persistence.jdbc.password" value="admin"/>
     </properties>
     </persistence-unit>
     */

    Collection<PersistenceUnitConfig> getPersistenceUnitConfigs();
    
}