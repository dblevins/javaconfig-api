package javax.config.deploy;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by atsticks on 29.08.14.
 */
public interface ConfiguredDataSource {

    /**
     * Get the datasource's name in JNDI.
     * @return the unique name, e.g. {@code "java:global/MyApp/myDS"}, never null.
     */
    String getName();

    /**
     * Get the datasource's class name.
     * @return the datasource's class name, never null. Example: e{@code "org.apache.derby.jdbc.ClientDataSource"}
     */
    Class<? extends DataSource> getClassName();

    /**
     * Get the datasource's port, e.g. 1257.
     * @return the datasource's port.
     */
    int getPortNumber();

    /**
     * Get the datasource's server name, e.g. {@code localhost}.
     * @return the datasource's server name, not null.
     */
    String getServerName();

    /**
     * Get the datasource's database name, e.g. {@code testDB}.
     *  @return the datasource's database name, not null.
     */
    String getDatabaseName();

    /**
     * Get the datasource's user.
     * @return the datasource's user..
     */
    String getUser();

    /**
     * Get the datasource's password.
     * @return the datasource's password..
     */
    String getPassword();

    /**
     * Get the datasource's additional properties, e.g. {@code createDatabase=create}.
     * @return the datasource's additional properties, never null.
     */
    Map<String,String> getProperties();

}
