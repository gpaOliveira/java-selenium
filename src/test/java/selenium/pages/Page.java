package selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.context.Context;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

	private final int poolingTimeSeconds = 1;
	protected Context context;

	public Page(WebDriver driver) {
		Context.getInstance().setDriver(driver);
		PageFactory.initElements(driver(), this);
	}
	
	public static Page create() {
		return PageFactory.initElements(driver(), Page.class);
	}
  
	public Page() {
		 this.context = Context.getInstance();
		 PageFactory.initElements(driver(), this);
	}
	
	public Page(int timeoutInSeconds) {
		 //https://www.toolsqa.com/selenium-webdriver/page-factory-in-selenium/
		 this.context = Context.getInstance();
		 PageFactory.initElements(new AjaxElementLocatorFactory(driver(), timeoutInSeconds), this);
	}
  
	public static WebDriver driver() {
		return Context.getInstance().getDriver();
	}

	public String getTitle() {
		return driver().getTitle();
	}
  
	public String getUrl() {
		return driver().getCurrentUrl();
	}
  
	public void log(String message) {
		context.getLogger().info(message);
	}
  
	public void waitForElementVisible(WebElement element, int timeouInSeconds) {
		new WebDriverWait(driver(), Duration.ofSeconds(timeouInSeconds), Duration.ofSeconds(poolingTimeSeconds)).
		until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickNotIntheCenter(WebElement elem) {
		// From https://stackoverflow.com/questions/40546546/how-to-click-on-a-specific-position-of-a-web-element-in-selenium/40571321
		int width = elem.getSize().getWidth();
	    Actions act = new Actions(driver());
	    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
	}
	
	public void focusOut() {
		Actions act = new Actions(driver());
	    act.moveByOffset(10, 10);
	}

}
