package com.OrangeHRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SSL_Certificate_All_Browser_Example {
	/* Two way we can handle SSL Certificate
	 * 1. Using "capabilities.setAcceptInsecureCerts(true);"
	 * 2. Using capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	 */
	
	WebDriver driver;

	/*@SuppressWarnings("deprecation")
	@BeforeTest
	public void LaunchBrowserChrome() {
		 // Create object of DesiredCapabilities class
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set ACCEPT_SSL_CERTS  variable to true
		capabilities.setAcceptInsecureCerts(true);
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilities);  //this will merge the capabilities to the ChromeOptions
		// setting system property for Chrome browser
		WebDriverManager.chromedriver().setup();
		// create Google Chrome instance and maximize it
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
	}*/

	@BeforeTest
	public void LaunchBrowserFirefox() {
		// Create object of DesiredCapabilities class
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set ACCEPT_SSL_CERTS  variable to true
		capabilities.setAcceptInsecureCerts(true);
		
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("incognito");
		//options.setHeadless(true);
		
		options.merge(capabilities); // This will merge the capabilities to the ChromeOptions

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void openApplication() throws InterruptedException {
		System.out.println("Navigating to application");
		driver.get("https://expired.badssl.com/");
		Thread.sleep(40000);
		
		String ActTitle=driver.getTitle();
		String ExpTitle="expired.badssl.com";
		Assert.assertEquals(ActTitle, ExpTitle);
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}
