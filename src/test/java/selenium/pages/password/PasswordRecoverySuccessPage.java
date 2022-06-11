package selenium.pages.password;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.context.Context;
import selenium.pages.Page;

public class PasswordRecoverySuccessPage extends Page{
	
	@FindBy(how = How.CSS, using = "button[data-testid=\"mr-link-back-to-login-1\"]")
	public WebElement backToLogin;
	
	@FindBy(how = How.CSS, using = "button[data-testid=\"mr-link-signup-1\"]")
	public WebElement signup;
	
	@FindBy(how = How.CLASS_NAME, using = "signup__title-form")
	public WebElement title;
	
	public PasswordRecoverySuccessPage(WebDriver webDriver) {
		super(webDriver);
	}
	  
	public PasswordRecoverySuccessPage() {
		super();
	}
	  
	public PasswordRecoverySuccessPage(int timeoutSeconds) {
		super(timeoutSeconds);
	}
	  
	public static PasswordRecoverySuccessPage create() {
		return PageFactory.initElements(Context.getInstance().getDriver(), PasswordRecoverySuccessPage.class);
	}
	  
	public Boolean isOnPage() {
		return this.getUrl().contains("recover/success/");
	}
	
	public String getFormTitle() {
		return title.getText();
	}
}
