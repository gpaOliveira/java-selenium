package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import selenium.config.TestConfig;
import selenium.context.Context;

public class LoginPage extends Page {

  @FindBy(how = How.ID, using = "email")
  public WebElement email;
  
  @FindBy(how = How.ID, using = "password")
  public WebElement password;
  
  @FindBy(how = How.CSS, using = "button[data-autotest-id=\"mr-form-login-btn-signin-1\"]")
  public WebElement signin;
  
  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }
  
  public LoginPage() {
	  super();
  }
  
  public LoginPage(int timeoutSeconds) {
	  super(timeoutSeconds);
  }
  
  public static LoginPage create() {
	  return PageFactory.initElements(Context.getInstance().getDriver(), LoginPage.class);
  }
  
  public Boolean isOnPage() {
	  return this.getUrl().contains("login");
  }
  
  public void waitForEmailField() {
	  this.waitForElementVisible(email, 3);
  }
  
}
