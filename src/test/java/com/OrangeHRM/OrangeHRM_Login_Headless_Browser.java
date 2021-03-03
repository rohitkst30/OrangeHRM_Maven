package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Login_Headless_Browser {
	WebDriver driver;
	
//	ChromeDriver driver;
//	EdgeDriver driver;
//	FirefoxDriver driver;
//	InternetExplorerDriver driver;
	WebDriverWait wait;
	
	@Test
	public void Login() throws InterruptedException {
		List<WebElement> elements = wait.until( ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//input[@name='txtUsername']"), 1) );
		elements.get(0).sendKeys("Admin");
		
		driver
//			.findElementByCssSelector("#txtPassword")
			.findElement(By.cssSelector("#txtPassword"))
			.sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
	}

	@Test(priority = 1)
	public void Logout() throws InterruptedException {
//		driver.findElementById("welcome").click();
		driver.findElement(By.id("welcome")).click();
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
		String actText = element.getText();
		element.click();

		// To get the Text and verify.
		String ActText = 
//			driver.findElementById("logInPanelHeading").getText();
			driver.findElement(By.id("logInPanelHeading")).getText();
		String ExpText = "LOGIN Panel";
		Assert.assertEquals(ExpText, ActText);

		// To get the currentURL and Verify.
		String ActURL = driver.getCurrentUrl();
		String ExpURL = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";
		Assert.assertEquals(ExpURL, ActURL);

		// To Verify the Title
		String ActTitle = driver.getTitle();
		String ExpTitle = "OrangeHRM";
		Assert.assertEquals(ExpTitle, ActTitle);
	}

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup(); // Downloads the right driver version for the right browser.
		ChromeOptions options = new ChromeOptions();
		
		driver = new ChromeDriver(options); // Reference to the browser.
		
//		WebDriverManager.edgedriver().setup();
//		EdgeOptions options = new EdgeOptions();
		
//		WebDriverManager.firefoxdriver().setup();
//		FirefoxOptions options = new FirefoxOptions();
				
//		WebDriverManager.iedriver().setup();
//		InternetExplorerOptions options = new InternetExplorerOptions();
		
		options.setHeadless(false);
		options.addArguments("incognito"); // For incognito mode.

//		driver = new EdgeDriver(options);
//		driver = new FirefoxDriver(options);
//		driver = new InternetExplorerDriver(options);
		
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
		wait = new WebDriverWait(driver, 60);
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}

//TODO: try all this for at least 2 browsers.
