package selenium.pages.signup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.context.Context;
import selenium.pages.Page;
import selenium.pages.password.PasswordRecoveryPage;

public class SignupPage extends Page {
	
	@FindBy(how = How.ID, using = "name")
	public WebElement name;
	
	@FindBy(how = How.ID, using = "email")
	public WebElement email;
	  
	@FindBy(how = How.ID, using = "password")
	public WebElement password;
	
	@FindBy(how = How.ID, using = "signup-terms")
	public WebElement terms;
	
	@FindBy(how = How.ID, using = "signup-subscribe")
	public WebElement subscribe;
	
	@FindBy(how = How.ID, using = "password-hint")
	public WebElement passwordHint;
	
	@FindBy(how = How.CSS, using = "a[data-autotest-id=\"mr-link-signin-1\"]")
	public WebElement backToLogin;
	
	@FindBy(how = How.CLASS_NAME, using = "signup__input-hint-bar-wrap")
	public WebElement hint;
	
	public SignupPage(WebDriver webDriver) {
		super(webDriver);
	}
	  
	public SignupPage() {
		super();
	}
	  
	public SignupPage(int timeoutSeconds) {
		super(timeoutSeconds);
	}
	  
	public static SignupPage create() {
		return PageFactory.initElements(Context.getInstance().getDriver(), SignupPage.class);
	}
	  
	public Boolean isOnPage() {
		return this.getUrl().contains("signup");
	}
	
	public String getPasswordHintText() {
		waitForElementVisible(passwordHint, 3);
		return passwordHint.getText();
	}
	
	public void termsClick() {
		// that input have an svg overlaping it in the center, so let's try to move the click around
		this.clickNotIntheCenter(terms);
	}
	
	public void subscribeClick() {
		// that input have an svg overlaping it in the center, so let's try to move the click around
		this.clickNotIntheCenter(subscribe);
	}
	
	public void typePassword(String passwordToType) {
		password.clear();
		password.sendKeys(passwordToType);
		this.focusOut();
	}
	
	public boolean isHintBarVisible() {
		return hint.isDisplayed();
	}
	
	public String getHintBarState() {
		String dom_classes = hint.getDomAttribute("class");
		if (!dom_classes.contains("--"))
			return "";
		String hintBarState = dom_classes.split("--")[1];
		this.log(String.format("hintBarState = %s", hintBarState));
		return hintBarState;
	}
}
