package selenium.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;

import selenium.actions.LoginActions;
import selenium.actions.PasswordActions;
import selenium.actions.SignupActions;
import selenium.base.TestBase;
import selenium.config.TestConfig;
import selenium.pages.dashboard.DashboardPage;
import selenium.pages.login.LoginPage;
import selenium.pages.password.PasswordRecoveryPage;

public class LoginTest extends TestBase {
	
	@Test
	public void testLoginSuccessfull() {
		
		LoginActions loginActions = new LoginActions();
		
		loginActions.useGoodCredentials();
		
		assertFalse(
			"Problems found with good credentials",
			loginActions.isOnLoginPageWithErrors()
		);
		assertTrue(
			"No Problems found with good credentials but we are not on Dashboard",
			loginActions.isOnDashboard()
		);
		
		loginActions.logout();
		
		assertTrue(
			"Logout didn't lead us to the login page", 
			loginActions.isOnLoginPage(3)
		);
	}
	
	@Test
	public void testLoginWithError() {
		
		LoginActions loginActions = new LoginActions();
		
		loginActions.useBadCredentials();
		
		assertTrue(
			"Error did not show with bad credentials", 
			loginActions.isOnLoginPageWithErrors()
		);
		assertFalse(
			"No Problems found with BAD credentials, we are not on Dashboard :O",
			loginActions.isOnDashboard(1)
		);
	}
	
	@Test
	public void testNavigateAround() {
		
		PasswordActions passwordActions = new PasswordActions();
		
		passwordActions.forgotPasswordFromLogin();
		
		assertTrue(
			"We didn't navigate to the Password Recovery screen", 
			passwordActions.isOnPasswordRecovery()
		);
		
		SignupActions signupActions = passwordActions.forgotPasswordToSignup();
		
		assertTrue(
			"We didn't navigate to the Signup screen", 
			signupActions.isOnSignup()
		);
		
		boolean backStatus = signupActions.backToLogin();
		
		assertTrue(
			"We didn't navigate back to the Login screen", 
			backStatus
		);
	}
	
	@Test
	public void testSignupPasswordStrength() {
		
		SignupActions signupActions = new SignupActions();
		
		signupActions.signupFromLogin();
		
		assertTrue(
			"We didn't navigate to the Signup screen", 
			signupActions.isOnSignup()
		);
		
		assertEquals(
			"Apparently the hint bar is visible",
			SignupActions.HintStates.NOT_VISIBLE,
			signupActions.getHintBarState()
		);
		
		signupActions.fillBasicInfo();
		
		signupActions.fillPassword("abc");
		
		assertEquals(
			"We didn't get the proper small password text", 
			"Please use 8+ characters for secure password.", //TODO: why is this the only one ending with "."?
			signupActions.getPasswordHintText()
		);
		
		assertEquals(
			"We didn't get the proper small password hint",
			SignupActions.HintStates.WEAK,
			signupActions.getHintBarState()
		);
		
		signupActions.fillPassword("abc12345");
		
		assertEquals(
			"We didn't get the proper soso password text", 
			"Weak password",
			signupActions.getPasswordHintText()
		);
		
		assertEquals(
			"We didn't get the proper soso password hint",
			SignupActions.HintStates.SOSO,
			signupActions.getHintBarState()
		);
		
		signupActions.fillPassword("abc12345@#$%^a");
		
		assertEquals(
			"We didn't get the proper good password text", 
			"Good password", 
			signupActions.getPasswordHintText()
		);
		
		assertEquals(
			"We didn't get the proper good password hint",
			SignupActions.HintStates.GOOD,
			signupActions.getHintBarState()
		);
		
		signupActions.fillPassword("abc12345@#$%^ABCb");
		
		assertEquals(
			"We didn't get the proper great password text", 
			"Great password", 
			signupActions.getPasswordHintText()
		);
		
		assertEquals(
			"We didn't get the proper great password hint",
			SignupActions.HintStates.GREAT,
			signupActions.getHintBarState()
		);
		
		boolean backStatus = signupActions.backToLogin();
		
		assertTrue(
			"We didn't navigate back to the Login screen", 
			backStatus
		);
	}
	
	@Test
	public void testForgotPassword() {
		
		PasswordActions passwordActions = new PasswordActions();
		
		passwordActions.forgotPasswordFromLogin();
		
		assertTrue(
			"We didn't navigate to the Password Recovery screen", 
			passwordActions.isOnPasswordRecovery()
		);
		
		passwordActions.proceed();
		
		assertTrue(
			"We didn't navigate to the Password Recovery Success screen", 
			passwordActions.isOnPasswordRecoverySuccess()
		);
		
		assertEquals(
			"We didn't get the proper title on Password Recovery Success screen", 
			"Thank you!", 
			passwordActions.getPasswordRecoveryTitle()
		);
		
		boolean backStatus = passwordActions.backToLoginFromSucessPage();
		
		assertTrue(
			"We didn't navigate back to the Login screen", 
			backStatus
		);
		
	}
}
