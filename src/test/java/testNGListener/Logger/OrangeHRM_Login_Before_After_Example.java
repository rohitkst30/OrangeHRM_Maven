package testNGListener.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Login_Before_After_Example {
	WebDriver driver;
	// ChromeDriver driver;
	
	@BeforeTest(description = "Chrome Browser Launched")
	public void LaunchBrowser() {
		Log.info("Chrome Browser Launched");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterTest(description = "Browser Closed Successfully")
	public void CloseBrowser() {
		// driver.close();//Close will close only current chrome browser
		driver.quit();
		Log.info("Chrome Browser closed successfully");
	}

	@Test(description = "Verify-post login user land to Dashboard page")
	public void LoginToOrangeHRM() throws InterruptedException {
		//Log.info("OpenHRM URL Lauched");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		Thread.sleep(60000);
		
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		// Verify that Dashboard page displayed
		String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
		String ExpElement = "Dashboard";
		Assert.assertEquals(ActElement, ExpElement);
		System.out.println(ActElement);
		
		// ----------------To Verify Logout Function without using Assert----------------
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(3000);
		
		//Log.info("User Clicked on Logout button");
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}
}
