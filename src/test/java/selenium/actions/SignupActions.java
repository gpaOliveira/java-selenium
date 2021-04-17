package selenium.actions;

import org.openqa.selenium.WebDriver;

import selenium.context.Context;
import selenium.pages.login.LoginPage;
import selenium.pages.password.PasswordRecoveryPage;
import selenium.pages.signup.SignupPage;

public class SignupActions {
	
	protected SignupPage signupPage = null;
	
	public enum HintStates {
		NONE("none"),
		NOT_VISIBLE("not-visible"),
	    WEAK(""),
	    SOSO("state-soso"),
	    GOOD("state-good"),
		GREAT("state-great");

	    private String text;

	    HintStates(String text) {
	        this.text = text;
	    }

	    public String getText() {
	        return this.text;
	    }

	    public static HintStates fromString(String text) {
	        for (HintStates b : HintStates.values()) {
	            if (b.text.equalsIgnoreCase(text)) {
	                return b;
	            }
	        }
	        return null;
	    }
	}
	
	public SignupActions(SignupPage signupPage) {
		this.signupPage = signupPage;
	}

	public SignupActions() {
		
	}

	private WebDriver driver() {
		return Context.getInstance().getDriver();
	}
	
	private void log(String message) {
		Context.getInstance().getLogger().info(message);
	}
	
	private void debug(String message) {
		Context.getInstance().getLogger().debug(message);
	}
	
	public SignupPage getSignupPage() {
		return signupPage == null? new SignupPage(3) : signupPage;
	}
	
	public SignupPage signupFromLogin() {
		LoginActions loginActions = new LoginActions();
		LoginPage loginPage = loginActions.visit();
		loginPage.signup.click();
		signupPage = new SignupPage(3); 
		return signupPage;
	}
	
	public boolean isOnSignup() {
		Boolean isOnSignup = this.getSignupPage().isOnPage();
		this.log(String.format("isOnSignup = %b", isOnSignup));
		return isOnSignup;
	}

	public boolean backToLogin() {
		this.getSignupPage().backToLogin.click();
		this.signupPage = null;
		this.log("backToLogin OK");
		return new LoginActions().isOnLoginPage(1);
	}
	
	public String getPasswordHintText() {
		return this.getSignupPage().getPasswordHintText();
	}

	public void fillBasicInfo() {
		signupPage = null; //some weird timing issue happening, let's refresh the page here
		SignupPage page = this.getSignupPage();
		page.name.sendKeys("Someone");
		page.email.sendKeys("someone@email.com");
		page.password.clear();
		page.termsClick();
		page.subscribeClick();
		this.log("fillBasicInfo OK");
	}

	public void fillPassword(String password) {
		SignupPage page = this.getSignupPage();
		page.typePassword(password);
		this.log("fillPassword OK");
	}

	public HintStates getHintBarState() {
		SignupPage page = this.getSignupPage();
		if (!page.isHintBarVisible())
			return HintStates.NOT_VISIBLE;
		return HintStates.fromString(page.getHintBarState());
	}
	
}
