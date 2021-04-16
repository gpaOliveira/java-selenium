package selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.support.PageFactory;

import selenium.pages.HomePage;

public class SampleTest extends TestBase {

  private HomePage homepage;

  @Before
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test
  public void testHomePageHasAHeader() {
    //driver.get(baseUrl);
    driver.get("https://www.github.com");
    Assert.assertTrue(driver.getTitle().contains("GitHub"));
  }
}
