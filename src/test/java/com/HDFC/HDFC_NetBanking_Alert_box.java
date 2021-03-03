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

public class HDFC_NetBanking_Alert_box {
	ChromeDriver driver;

	@Test
	public void EnterCustomerID() throws InterruptedException {
		driver.switchTo().frame("login_page");
		driver.findElementByXPath("//tbody/tr/td/a/img[1]").click();//XPath using SelectorHub.
		
		Thread.sleep(2000);
		String Errormsg=driver.switchTo().alert().getText();
		String ExpErrormsg="Customer ID  cannot be left blank.";
		Assert.assertEquals(Errormsg, ExpErrormsg);
		
		driver.switchTo().alert().accept();// Switch to the alert box & click OK on it.
		driver.switchTo().defaultContent();
	}

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://netbanking.hdfcbank.com/netbanking/");
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}
