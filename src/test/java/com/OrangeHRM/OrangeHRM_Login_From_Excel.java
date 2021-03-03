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

public class OrangeHRM_Login_From_Excel extends OrangeHRM_TestData {
	ChromeDriver driver;
	WebDriverWait wait;

	@Test(dataProvider = "LoginExcelData")
	public void Sign_On(String uname, String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		  List<WebElement> elements =
			  wait.until(ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//input[@name='txtUsername']"), 1) );
		  driver.findElement(By.name("txtUsername")).clear();
		  elements.get(0).sendKeys(uname);
		  
		driver.findElement(By.name("txtPassword")).clear();
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}
