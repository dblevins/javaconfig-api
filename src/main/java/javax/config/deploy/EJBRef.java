package javax.config.deploy;

/**
 * Created by atsticks on 29.08.14.
 */
public interface EJBRef {

    String getEJBRefName();
    EJBType getEJBRefType();
    Class<?> remoteInterface();

}
