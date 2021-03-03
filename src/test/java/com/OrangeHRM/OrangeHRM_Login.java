package com.OrangeHRM;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Login {

	//It behaves as the main method.
	@Test
	public void Login_Successful_Scenario() throws InterruptedException{
		// Launch the Chrome browser
		WebDriverManager.chromedriver().setup(); // Downloads the right driver version for the right browser
		ChromeDriver driver = new ChromeDriver(); // Reference to the browser

		// Launch the FF browser
//		WebDriverManager.firefoxdriver().setup();
//		WebDriver driver = new FirefoxDriver();
//		FirefoxDriver driver = new FirefoxDriver();
//		FF is working but, it's asking for credentials for Zscaler login and 
//		also, not inputting the user name & password automatically.
		
		// Launch the Edge browser
//		WebDriverManager.edgedriver().setup();
//		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		Thread.sleep(5000);
		
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		
//		driver.close(); // Close the current browser.
		driver.quit(); // Close all the browsers opened by Selenium.
	}

}
