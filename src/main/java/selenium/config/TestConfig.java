package selenium.config;

import org.aeonbits.owner.ConfigFactory;

public final class TestConfig{
    
	private static ITestConfig INSTANCE = null;
    
    private TestConfig() {}

    public static ITestConfig getConfig(){
		if (INSTANCE == null) {
			INSTANCE = ConfigFactory.create(ITestConfig.class);
		}
		return INSTANCE;
    }
}