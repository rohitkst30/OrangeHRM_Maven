package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Login_Xpath_CSS_Example {
	ChromeDriver driver;
	
  @Test
  public void Login() throws InterruptedException{
	Thread.sleep(8000);
//	driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	driver.findElementByXPath("//input[@name='txtUsername']").sendKeys("Admin");
//	driver.findElement(By.id("txtPassword")).sendKeys("admin123");
	driver.findElementByCssSelector("#txtPassword").sendKeys("admin123");
	driver.findElement(By.id("btnLogin")).click();
	driver.findElement(By.linkText("Dashboard")).isDisplayed();
  }
  
  @Test(priority = 1)
  public void Logout() throws InterruptedException {
	  driver.findElementById("welcome").click();
	  Thread.sleep(2000);
	  driver.findElementByLinkText("Logout").click();
	  
	  //To get the Text and verify
	  String ActText =
  		driver.findElementById("logInPanelHeading").getText();
	  String ExpText="LOGIN Panel";
	  Assert.assertEquals(ExpText, ActText);
	  
	  //To get the currentURL and Verify
	  String ActURL=driver.getCurrentUrl();
	  String ExpURL="https://opensource-demo.orangehrmlive.com/index.php/auth/login";
	  Assert.assertEquals(ExpURL, ActURL);
	  
	  //To Verify the Title
	  String ActTitle=driver.getTitle();
	  String ExpTitle="OrangeHRM";
	  Assert.assertEquals(ExpTitle, ActTitle);
  }
  
  @BeforeTest
  public void beforeTest() {
	// Launch the browser
	WebDriverManager.chromedriver().setup(); // Downloads the right driver version for the right browser.
	driver = new ChromeDriver(); // Reference to the browser.
	driver.manage().window().maximize();
	driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
  }

  @AfterTest
  public void afterTest() {
	driver.quit(); // Close all the browsers opened by Selenium.
  }

}
