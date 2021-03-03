package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Delete_User_from_WebTable {
	ChromeDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@Test(priority = 1)
	// This is for Sign On Functionality
	public void Sign_On() throws InterruptedException {
		  wait = new WebDriverWait(driver, 60);
		  List<WebElement> elements = wait.until(
		  ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//input[@name='txtUsername']"), 1) ); elements.get(0).sendKeys("Admin");
		  
		  driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		  driver.findElement(By.id("btnLogin")).click();
		  driver.findElement(By.linkText("Dashboard")).isDisplayed();
	}

	@Test(priority = 2)
	public void AddUsers_Page() {
		WebElement admin = driver.findElementById("menu_admin_viewAdminModule");
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		
		WebElement usermanagement = driver.findElementByLinkText("User Management");
		action.moveToElement(usermanagement).build().perform();
		
		driver.findElementByLinkText("Users").click();
		driver.findElementById("searchBtn").click();
		driver.findElementById("btnAdd").isDisplayed();
	}

	@Test(priority = 3)
	public void VerifyAddedUser() throws InterruptedException {
		driver.findElementById("btnAdd").click();
//		Thread.sleep(2000);
		
		Select SelectPass = new Select(driver.findElementById("systemUser_userType"));
		SelectPass.selectByVisibleText("Admin");
		driver.findElementById("systemUser_employeeName_empName").sendKeys("Fiona Grace");

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		driver.findElementById("systemUser_userName").sendKeys("abhi" + randomInt);
		// String ExpUserName=driver.findElementById("systemUser_userName").getText();
		driver.findElementById("systemUser_password").sendKeys("admin123");
		driver.findElementById("systemUser_confirmPassword").sendKeys("admin123");
		Thread.sleep(3000);
		
		driver.findElementById("btnSave").click();
		
		// Verify the Added User in WebTable
		Thread.sleep(2000);
		String ExpUserName = "abhi" + randomInt;
		driver.navigate().refresh();
		Thread.sleep(1000);
		WebElement cellIneed = driver.findElementByXPath("//*[text()='" + ExpUserName + "']");
		String valueIneed = cellIneed.getText();
		System.out.println("Cell value is : " + valueIneed);
		Assert.assertEquals(ExpUserName, valueIneed);

		// Delete User from Search List
//		driver.findElementByXPath("//a[text()='" + ExpUserName + "']/parent::td/preceding-sibling::td/input").click();//Working
//		 driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td/input").click();//Working
//		 driver.findElementByXPath("//td/a[text()='"+EnteredValue+"']//parent::td/../td[text()='Admin']").isDisplayed();
		 driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td[text()='Admin']").isDisplayed();
		Thread.sleep(3000);
		driver.findElementById("btnDelete").click();
		driver.findElementById("dialogDeleteBtn").click();
	}
}