package com.HDFC;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class HDFC_NetBanking {
	ChromeDriver driver;

	@Test
	public void EnterCustomerID() throws InterruptedException {
		driver.switchTo().frame("login_page");
		//driver.switchTo().frame(0);
		driver.findElementByXPath("//input[@name='fldLoginUserId']").sendKeys("1000");
		
		//XPaths written by the trainer on his own.
		//driver.findElementByXPath("//img[@alt='continue']").click();
//		driver.findElementByXPath("//table[@class='lForm']//img").click();
		
		driver.findElementByXPath("//tbody/tr/td/a/img[1]").click(); //XPath using SelectorHub.
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(1);
		driver.findElementByLinkText("Terms and Conditions").click();
	}

	// Pre-Condition
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		// This will store or remember the cookies or navigation in terms of back and forward
		driver.navigate().to("https://netbanking.hdfcbank.com/netbanking/");
	}

	// Post Condition
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}
