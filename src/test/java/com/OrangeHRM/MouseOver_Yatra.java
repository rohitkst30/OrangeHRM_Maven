package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class MouseOver_Yatra {
	//TODO: Do this for Yatra. Mouse over the My Account & click on Login and this should take us to the login page.
	ChromeDriver driver;
	WebDriverWait wait;
	
	@Test
	public void Login() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//		List<WebElement> elements =
//			wait.until( ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//li[@class='list-dropdown']//a[@class='dropdown-toggle']"), 1) );
		/*
		 * elements.get(0).sendKeys("Admin");
		 * 
		 * driver.findElementByCssSelector("#txtPassword").sendKeys("admin123");
		 * driver.findElement(By.id("btnLogin")).click();
		 * driver.findElement(By.linkText("Dashboard")).isDisplayed();
		 */
		
		WebElement admin = driver.findElementByLinkText("My Account");
//				XPath("//li[@class='list-dropdown']//a[@class='dropdown-toggle']");
//				Id("menu_admin_viewAdminModule");
        Actions action = new Actions(driver);
        
        action.moveToElement(admin).build().perform();
        
        WebElement usermanagement = driver.findElementByLinkText("User Management");
        action.moveToElement(usermanagement).build().perform();
        
        Thread.sleep(1000);
        driver.findElementByLinkText("Users").click();
	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.yatra.com/");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}