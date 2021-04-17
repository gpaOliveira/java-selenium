package selenium.pages.dashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.context.Context;
import selenium.pages.Page;

public class DashboardPage extends Page {
	
	@FindBy(how = How.CLASS_NAME, using = "user-profile")
	@CacheLookup
	public WebElement userProfile;
	
	@FindBy(how = How.CSS, using = ".user-profile__popup")
	@CacheLookup
	public WebElement userProfilePopup;
	
	@FindBy(how = How.CSS, using = ".user-profile__buttons:last-of-type .user-profile__button-item:last-of-type")
	@CacheLookup
	public WebElement logout;
	
	public DashboardPage(WebDriver webDriver) {
		super(webDriver);
	}
	  
	public DashboardPage() {
		super();
	}
	
	public DashboardPage(int timeoutSeconds) {
		super(timeoutSeconds);
	}
	
	public Boolean isOnPage() {
		return this.getUrl().contains("dashboard");
	}
	
	public void logout() {
		userProfile.click();
		waitForElementVisible(userProfilePopup, 3);
		logout.click();
	}
}
