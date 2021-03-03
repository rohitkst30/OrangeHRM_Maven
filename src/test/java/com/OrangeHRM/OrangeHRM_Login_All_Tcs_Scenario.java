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

public class OrangeHRM_Login_All_Tcs_Scenario extends OrangeHRM_TestData {
	ChromeDriver chDriver;

	@Test(dataProvider = "LoginScenario")
	public void LoginAllCases(String uName, String uPassword, String expectedMessage) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(chDriver, 60);
		  List<WebElement> elements =
			  wait.until(ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//input[@name='txtUsername']"), 1) );
		  chDriver.findElement(By.name("txtUsername")).clear();
		  elements.get(0).sendKeys(uName);
		  
		chDriver.findElement(By.name("txtPassword")).clear();
		chDriver.findElement(By.name("txtPassword")).sendKeys(uPassword);
		chDriver.findElement(By.name("Submit")).click();

		try {
			Thread.sleep(3000);
			Boolean boolDisplayed = chDriver.findElement(By.linkText("Dashboard")).isDisplayed();

			if (boolDisplayed) {
				System.out.println("Successfull Login");
				String strCurrDashboardLabel = chDriver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]"))
						.getText();
				Assert.assertEquals(expectedMessage, strCurrDashboardLabel);
				
				// Logout
				chDriver.findElement(By.partialLinkText("Welcome")).click();
				Thread.sleep(3000);
				
				chDriver.findElement(By.linkText("Logout")).isDisplayed();
				chDriver.findElement(By.linkText("Logout")).click();
				chDriver.findElement(By.name("Submit")).isDisplayed();
			} else {
				System.out.println("Unsuccessfull Login");
				String strInvalidLoginMessage = chDriver.findElement(By.id("spanMessage")).getText();
				Assert.assertEquals(expectedMessage, strInvalidLoginMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		chDriver = new ChromeDriver();
		chDriver.manage().window().maximize();
		chDriver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@AfterTest
	public void CloseBrowser() {
		chDriver.quit();
	}
}
