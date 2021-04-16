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
    
    @Key("user_good_username")
    String user_good_username();
    
    @Key("user_good_password")
    String user_good_password();
    
    @Key("user_bad_username")
    @DefaultValue("I-should-not-exist@mydomain.com")
    String user_bad_username();
    
    @Key("user_bad_password")
    @DefaultValue("I-should-not-exist")
    String user_bad_password();
    
    @Key("url_login")
    @DefaultValue("https://miro.com/login/")
    String url_login();
}
