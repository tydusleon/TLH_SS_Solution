package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class JanuaryPageTestcase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://guarded-hamlet-55092.herokuapp.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJanuaryPageTestcase() throws Exception {
    // This is the Shoe Store Test Suite
    // 1) Homepage Test
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Home")).click();
        assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    // Loop through List of Shoes where label value is parameterized
    new Select(driver.findElement(By.id("brand"))).selectByVisibleText("Andre Assous");
    driver.findElement(By.id("search_button")).click();
    
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    // Enhancement 1 - "Back" link needed to return home

// 2) Shoe Month Test
// Loop through List of Months where link value is parameterized
driver.findElement(By.linkText("January")).click();
// Warning: assertTextPresent may require manual changes
assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
driver.findElement(By.id("remind_email_input")).clear();
driver.findElement(By.id("remind_email_input")).sendKeys("tydusleon$yahoo.com");
driver.findElement(By.id("remind_email_submit")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
driver.findElement(By.id("remind_email_input")).clear();
driver.findElement(By.id("remind_email_input")).sendKeys("tydusleon@gmail.com");
driver.findElement(By.id("remind_email_submit")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
driver.findElement(By.id("promo_code_input")).clear();
driver.findElement(By.id("promo_code_input")).sendKeys("A12345");
driver.findElement(By.id("promo_code_submit")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
// Defect 1 - Valid  Promotional Code Format Unknown
driver.findElement(By.linkText("Problem Definition")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
driver.findElement(By.linkText("Report An Issue")).click();
driver.findElement(By.id("issue_title")).clear();
driver.findElement(By.id("issue_title")).sendKeys("My New Issue");
driver.findElement(By.id("issue_description")).clear();
driver.findElement(By.id("issue_description")).sendKeys("The Promotional Code valid format is unknown...  Please advise.");
driver.findElement(By.id("issue_title")).clear();
driver.findElement(By.id("issue_title")).sendKeys("Valid Promotional Code?");
new Select(driver.findElement(By.id("issue_severity"))).selectByVisibleText("Medium");
driver.findElement(By.id("issue_submit")).click();
driver.findElement(By.linkText("Issues")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
// if  Notify assertElementPresnt = true then loop through number of instances
// ERROR: Caught exception [Error: locator strategy either id or name must be specified explicitly.]
driver.findElement(By.cssSelector("form.notification_email_form > input[name=\"email\"]")).clear();
driver.findElement(By.cssSelector("form.notification_email_form > input[name=\"email\"]")).sendKeys("tydusleon$yahoo.com");
driver.findElement(By.cssSelector("input.notification_email_submit")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
// Defect 2 - Invalid email format processed as valid
driver.findElement(By.cssSelector("form.notification_email_form > input[name=\"email\"]")).clear();
driver.findElement(By.cssSelector("form.notification_email_form > input[name=\"email\"]")).sendKeys("tydusleon@gmail.com");
driver.findElement(By.cssSelector("input.notification_email_submit")).click();

assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
// Enhancement 2 - "Back" link needed to return home
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
