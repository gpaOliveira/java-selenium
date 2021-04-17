package selenium.actions;

import org.openqa.selenium.WebDriver;

import selenium.config.TestConfig;
import selenium.context.Context;
import selenium.pages.dashboard.DashboardPage;
import selenium.pages.login.LoginErrorPage;
import selenium.pages.login.LoginPage;

public class LoginActions {
	
	private WebDriver driver() {
		return Context.getInstance().getDriver();
	}
	
	private void log(String message) {
		Context.getInstance().getLogger().info(message);
	}
	
	private void debug(String message) {
		Context.getInstance().getLogger().debug(message);
	}
	
	public LoginPage visit() {
		this.log("navigating to login...");
		driver().navigate().to(TestConfig.getConfig().url_login());
		if (!(new LoginPage()).isOnPage()) {
			throw new IllegalStateException("This is not the login page");
		}
		this.log("visit to login OK");
		return LoginPage.create(); // now that we know we are on the page we need to make sure the selectors are searched again
	}
	
	private void useCredentials(LoginPage loginPage, String username, String password) {
		//this.log(username);
		//this.log(password);
		loginPage.email.sendKeys(username);
		loginPage.password.sendKeys(password);
		loginPage.signin.click();
	}
	
	public void useGoodCredentials() {
		this.log("using good credentials to login...");
		LoginPage loginPage = this.visit();
		this.useCredentials(
			loginPage, 
			TestConfig.getConfig().user_good_username(), 
			TestConfig.getConfig().user_good_password()
		);
		this.log("using good credentials OK");
	}
	
	public void useBadCredentials() {
		this.log("using bad credentials to login...");
		LoginPage loginPage = this.visit();
		this.useCredentials(
			loginPage, 
			TestConfig.getConfig().user_bad_username(), 
			TestConfig.getConfig().user_bad_password()
		);
		this.log("using bad credentials OK");
	}
	
	public void logout() {
		this.log("from dashboard, let's logout...");
		(new DashboardPage()).logout();
	}
	
	public boolean isOnLoginPage(int timeoutInSeconds) {
		LoginPage loginPage = new LoginPage(timeoutInSeconds);
		loginPage.waitForEmailField();
		Boolean isOnLoginPage = loginPage.isOnPage();
		this.log(String.format("isOnLoginPage = %b", isOnLoginPage));
		return isOnLoginPage;
	}
	
	public boolean isOnDashboard() {
		Boolean isOnDashboard = (new DashboardPage(3)).isOnPage();
		this.log(String.format("isOnDashboard = %b", isOnDashboard));
		return isOnDashboard;
		
	}
	
	public boolean isOnLoginPageWithErrors() {
		Boolean isOnLoginPageWithErrors = LoginErrorPage.create().isOnPage();
		this.log(String.format("isOnLoginPageWithErrors = %b", isOnLoginPageWithErrors));
		return isOnLoginPageWithErrors;
	}
}
