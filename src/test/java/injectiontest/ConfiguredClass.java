package injectiontest;

import javax.config.Configured;
import javax.inject.Singleton;
import java.math.BigDecimal;

/**
 * Created by Anatole on 08.09.2014.
 */
@Singleton
public class ConfiguredClass{

    @Configured
    private String testProperty;

    @Configured("a.b.c.key1")
    private String value1;

    @Configured({"foo", "a.b.c.key2"})
    private String value2;

    @Configured
    private String runtimeVersion;

    @Configured(defaultValue = "${java.version}")
    private String javaVersion2;

    @Configured
    private Integer int1;

    @Configured
    private int int2;

    @Configured
    private boolean booleanT;

    @Configured("BD")
    private BigDecimal bigNumber;

    public String toString(){
        return super.toString() + ": testProperty="+testProperty+", value1="+value1+", value2="+value2
                +", int1="+int1+", int2="+int2+", booleanT="+booleanT+", bigNumber="+bigNumber
                +", runtimeVersion="+runtimeVersion+", javaVersion2="+javaVersion2;
    }

}
