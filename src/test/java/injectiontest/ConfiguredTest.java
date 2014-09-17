package injectiontest;

import org.jboss.weld.bootstrap.api.CDI11Bootstrap;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.WeldSEProvider;
import org.junit.Test;

import javax.enterprise.inject.spi.CDI;

/**
 * Created by Anatole on 08.09.2014.
 */
public class ConfiguredTest{

    @Test
    public void testInjection(){
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        ConfiguredClass item = container.instance().select(ConfiguredClass.class).get();
        System.out.println("********************************************");
        System.out.println(item);
        System.out.println("********************************************");
        weld.shutdown();
    }

}
