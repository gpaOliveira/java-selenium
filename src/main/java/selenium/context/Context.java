package selenium.context;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import selenium.config.ITestConfig;

public final class Context {

    private static final Context INSTANCE = new Context();
    private static ITestConfig CONFIG = null;
    protected WebDriver driver;
	protected static Logger logger = null;

    private Context() {}

    public static Context getInstance() {
        return INSTANCE;
    }
    
    public static ITestConfig getConfig(){
		if (CONFIG == null) {
			CONFIG = ConfigFactory.create(ITestConfig.class);
		}
		return CONFIG;
    }
    
    public void setDriver(WebDriver driver) {
    	this.driver = driver;
    }
    
    public WebDriver getDriver() {
    	return this.driver;
    }
    
    public void setLogger(Logger logger) {
    	this.logger = logger;
    }
    
    public Logger getLogger() {
    	return this.logger;
    }
}