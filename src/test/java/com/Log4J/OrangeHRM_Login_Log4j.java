package com.Log4J;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Log_Message;

public class OrangeHRM_Login_Log4j {
	WebDriver driver;
	// ChromeDriver driver;
	
	@BeforeTest
	public void LaunchBrowser() {
		Log_Message.startLog("Chrome Browser Launched");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
		Log_Message.endLog("Chrome Browser closed successfully");
	}

	@Test
	public void LoginToOrangeHRM() throws InterruptedException {
		Log_Message.debug("OpenHRM URL Lauched");
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		Thread.sleep(15000);
		
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		// Verify that Dashboard page displayed
		String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
		String ExpElement = "Dashboard";
		Assert.assertEquals(ActElement, ExpElement);
		System.out.println(ActElement);
		
		// ----------------To Verify Logout Function without using Assert----------------
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(5000);
		
		Log_Message.debug("User Clicked on Logout button");
		
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}
}
