package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Login_DataProvider extends OrangeHRM_TestData {
	ChromeDriver driver;
	WebDriverWait wait;

	/*
	 * @BeforeTest public void beforeTest() {
	 * WebDriverManager.chromedriver().setup(); ChromeOptions options = new
	 * ChromeOptions(); options.setHeadless(false);
	 * 
	 * driver = new ChromeDriver(options); driver.manage().window().maximize();
	 * driver.navigate().to(
	 * "https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	 * 
	 * wait = new WebDriverWait(driver, 60); }
	 */
	
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

	@Test(dataProvider="Login")
	public void LoginToOrangeHRM(String Uname, String Upass) throws InterruptedException {
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		/*driver.findElement(By.name("txtUsername")).sendKeys(Uname);
		driver.findElement(By.name("txtPassword")).sendKeys(Upass);
		driver.findElement(By.id("btnLogin")).click();*/
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		  List<WebElement> elements =
			  wait.until(ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//input[@name='txtUsername']"), 1) );
		  elements.get(0).sendKeys(Uname);
		  
		  driver.findElementByCssSelector("#txtPassword").sendKeys(Upass);
		  driver.findElement(By.id("btnLogin")).click();
		  driver.findElement(By.linkText("Dashboard")).isDisplayed();
		  
		// Verify that Dashboard page displayed
		String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
		String ExpElement = "Dashboard";
		Assert.assertEquals(ActElement, ExpElement);
		System.out.println(ActElement);
		// ----------------To Verify Logout Function----------------

		/*
		 * driver.findElement(By.id("welcome")).click(); Thread.sleep(3000);
		 * driver.findElement(By.linkText("Logout")).click(); String ActLogpanel
		 * = driver.findElement(By.id("logInPanelHeading")).getText(); String
		 * ExpLogpanel="LOGIN Panel"; Assert.assertEquals(ActLogpanel,
		 * ExpLogpanel); System.out.println(ActLogpanel);
		 */
		// ----------------To Verify Logout Function without using
		// Assert----------------
		Thread.sleep(3000);
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}
}
