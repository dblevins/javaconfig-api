package javax.config.deploy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by atsticks on 29.08.14.
 */
public interface EJBConfiguration {

    EJBType getBeanType();
    String getName();

    Collection<EJBRef> getEJBRefs();

    Map<String,String> getEnvEntries();
    String getDescription(String envEntry);
    SessionType getSessionType();
    TransactionType getTransactionType();

}
