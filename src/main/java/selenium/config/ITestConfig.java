package selenium.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
    "system:properties",
    "system:env",
    "classpath:application.properties"})
public interface ITestConfig extends Config{
    @Key("timeout")
    @DefaultValue("3")
    int timeout();

    @Key("headless")
    @DefaultValue("true")
    Boolean headless();
}
