package selenium.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;

import selenium.actions.LoginActions;
import selenium.base.TestBase;
import selenium.config.TestConfig;
import selenium.pages.DashboardPage;
import selenium.pages.LoginPage;

public class LoginTest extends TestBase {
	
	@Test
	public void testLoginSuccessfull() {
		
		LoginActions loginActions = new LoginActions();
		
		loginActions.useGoodCredentials();
		
		assertFalse(
			"Probleams found with good credentials", 
			loginActions.isOnLoginPageWithErrors()
		);
		assertTrue(
			"No Probleams found with good credentials but we are not on Dashboard", 
			loginActions.isOnDashboard()
		);
		
		loginActions.logout();
		
		assertTrue(
			"Logout didn't lead us to the login page", 
			loginActions.isOnLoginPage(3)
		);
	}
}
