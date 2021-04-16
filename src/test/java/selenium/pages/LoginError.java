package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.context.Context;

public class LoginError extends Page {
	@FindBy(how = How.CLASS_NAME, using = "signup__error-item")
	public WebElement error;
	
	public static LoginError create() {
		return PageFactory.initElements(Context.getInstance().getDriver(), LoginError.class);
	}
	
	public Boolean isOnPage() {
		return this.getUrl().contains("login");
	}
	  
	public boolean visible() {
		try {
			this.waitForElementVisible(error, 3);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
