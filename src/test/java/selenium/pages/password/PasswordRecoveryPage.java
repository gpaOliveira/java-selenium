package selenium.pages.password;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.context.Context;
import selenium.pages.Page;

public class PasswordRecoveryPage extends Page{

	@FindBy(how = How.CSS, using = "button[data-autotest-id=\"mr-link-back-to-signin-1\"]")
	public WebElement backToLogin;
	
	@FindBy(how = How.CSS, using = "a[data-autotest-id=\"mr-link-signup-1\"]")
	public WebElement signup;
	
	@FindBy(how = How.CSS, using = "button[data-autotest-id=\"mr-form-recovery-btn-send-1\"]")
	public WebElement continueButton;
	
	@FindBy(how = How.ID, using = "email")
	public WebElement email;
	
	public PasswordRecoveryPage(WebDriver webDriver) {
		super(webDriver);
	}
	  
	public PasswordRecoveryPage() {
		super();
	}
	  
	public PasswordRecoveryPage(int timeoutSeconds) {
		super(timeoutSeconds);
	}
	  
	public static PasswordRecoveryPage create() {
		return PageFactory.initElements(Context.getInstance().getDriver(), PasswordRecoveryPage.class);
	}
	  
	public Boolean isOnPage() {
		return this.getUrl().contains("recover");
	}
}
