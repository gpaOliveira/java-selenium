package selenium.config;

import org.aeonbits.owner.ConfigFactory;

public class TestConfig{
    public static ITestConfig getConfig(){
        return ConfigFactory.create(ITestConfig.class);
    }
}