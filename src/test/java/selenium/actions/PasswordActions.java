package selenium.actions;

import org.openqa.selenium.WebDriver;

import selenium.context.Context;
import selenium.pages.dashboard.DashboardPage;
import selenium.pages.login.LoginPage;
import selenium.pages.password.PasswordRecoveryPage;
import selenium.pages.password.PasswordRecoverySuccessPage;
import selenium.pages.signup.SignupPage;

public class PasswordActions {
	
	protected PasswordRecoveryPage passwordRecovery = null;
	protected PasswordRecoverySuccessPage passwordRecoverySuccess = null;
	
	private WebDriver driver() {
		return Context.getInstance().getDriver();
	}
	
	private void log(String message) {
		Context.getInstance().getLogger().info(message);
	}
	
	private void debug(String message) {
		Context.getInstance().getLogger().debug(message);
	}
	
	public PasswordRecoveryPage forgotPasswordFromLogin() {
		LoginActions loginActions = new LoginActions();
		LoginPage loginPage = loginActions.visit();
		loginPage.forgotPassword.click();
		passwordRecovery = new PasswordRecoveryPage(3); 
		return passwordRecovery;
	}
	
	public SignupActions forgotPasswordToSignup() {
		PasswordRecoveryPage passwordRecovery = this.getPasswordRecovery();
		passwordRecovery.signup.click();
		SignupPage signupPage = new SignupPage(3);
		this.log("forgotPasswordToSignup OK");
		return new SignupActions(signupPage);
	}
	
	public PasswordRecoveryPage getPasswordRecovery() {
		return passwordRecovery == null? PasswordRecoveryPage.create() : passwordRecovery;
	}
	
	public PasswordRecoverySuccessPage getPasswordRecoverySucess() {
		return passwordRecoverySuccess == null? PasswordRecoverySuccessPage.create() : passwordRecoverySuccess;
	}
	
	public void proceed() {
		PasswordRecoveryPage passwordRecovery = this.getPasswordRecovery();
		passwordRecovery.email.sendKeys("abc@email.com");
		passwordRecovery.continueButton.click();
		this.passwordRecovery = null;
		this.passwordRecoverySuccess = new PasswordRecoverySuccessPage(3);
	}
	
	public boolean backToLoginFromSucessPage() {
		this.getPasswordRecoverySucess().backToLogin.click();
		this.passwordRecoverySuccess = null;
		return new LoginActions().isOnLoginPage(1);
	}
	
	public boolean isOnPasswordRecovery() {
		Boolean isOnPasswordRecovery = this.getPasswordRecovery().isOnPage();
		this.log(String.format("isOnPasswordRecovery = %b", isOnPasswordRecovery));
		return isOnPasswordRecovery;
	}
	
	public boolean isOnPasswordRecoverySuccess() {
		Boolean isOnPasswordRecoverySuccess = this.getPasswordRecoverySucess().isOnPage();
		this.log(String.format("isOnPasswordRecoverySuccess = %b", isOnPasswordRecoverySuccess));
		return isOnPasswordRecoverySuccess;
		
	}
	
	public String getPasswordRecoveryTitle() {
		return this.getPasswordRecoverySucess().getFormTitle();
	}
}
