package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class OrangeHRM_AddUser {
	ChromeDriver driver;
	WebDriverWait wait;
	
	/*
	 * @Test(priority=1) public void Login() { wait = new WebDriverWait(driver, 60);
	 * List<WebElement> elements = wait.until(
	 * ExpectedConditions.numberOfElementsToBe(ByXPath.xpath(
	 * "//input[@name='txtUsername']"), 1) ); elements.get(0).sendKeys("Admin");
	 * 
	 * driver.findElement(By.id("txtPassword")).sendKeys("admin123");
	 * driver.findElement(By.id("btnLogin")).click();
	 * driver.findElement(By.linkText("Dashboard")).isDisplayed(); }
	 */

	@Test(priority=2)
	// This is for Add User Functionality
	public void AddUser() throws InterruptedException {
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		Thread.sleep(2000);
		
		WebElement admin = driver.findElementById("menu_admin_viewAdminModule");
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		Thread.sleep(1000);
		
		WebElement usermanagement = driver.findElementByLinkText("User Management");
		action.moveToElement(usermanagement).build().perform();
		Thread.sleep(2000);
		
		driver.findElementByLinkText("Users").click();
		driver.findElementById("btnAdd").click();
		
		Select userrole = new Select (driver.findElementById("systemUser_userType"));//Identify a drop down
		userrole.selectByVisibleText("Admin");//Select the drop down option by visible text
		//userrole.selectByValue("1");//Select the drop down option by value
		//userrole.selectByIndex(0);//Select the drop down option by index. We can use this when we don't know what values will come.
		driver.findElementById("systemUser_employeeName_empName").sendKeys("Fiona Grace");
		
		//Using Random class, we can generate Random number between given value
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		WebElement UserNameInputbox = driver.findElement(By.id("systemUser_userName"));
		UserNameInputbox.sendKeys("sivaharsha" + randomInt);
		String EnteredValue = UserNameInputbox.getAttribute("value");
		System.out.println("EnteredValue: " + EnteredValue);
		
		driver.findElementById("systemUser_password").sendKeys("selenium");
		driver.findElementById("systemUser_confirmPassword").sendKeys("selenium");
		driver.findElementById("btnSave").click();
		Thread.sleep(2000);
//		driver.findElement(By.id("btnSave")).click();
		
		Thread.sleep(4000);
  		driver.findElementByName("btnDelete").isDisplayed();
	}
	
	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterTest
	public void afterTest() {
//		driver.quit();
	}
}

//TODO: Try all the actions (radio button, check box, select drop down, double click, etc.) with each having its own method in https://www.testandquiz.com/selenium/testing.html