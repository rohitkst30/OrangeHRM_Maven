package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class OrangeHRM_NavigationCommand {
	ChromeDriver driver;
	WebDriverWait wait;
	
	@Test
	public void Login() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		List<WebElement> elements = wait.until( ExpectedConditions.numberOfElementsToBe(ByXPath.xpath("//input[@name='txtUsername']"), 1) );
		elements.get(0).sendKeys("Admin");
		
		driver.findElementByCssSelector("#txtPassword").sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		
		WebElement admin = driver.findElementById("menu_admin_viewAdminModule");

        Actions action = new Actions(driver);
        action.moveToElement(admin).build().perform();
        
        WebElement usermanagement = driver.findElementByLinkText("User Management");
        action.moveToElement(usermanagement).build().perform();
        
        Thread.sleep(1000);
        driver.findElementByLinkText("Users").click();
        
        //Press the back button
        Thread.sleep(3000);
        driver.navigate().back();
        String dashboardUrl = driver.getCurrentUrl();
        System.out.println(dashboardUrl);
        
        //Press the forward button
        Thread.sleep(3000);
        driver.navigate().forward();
        String usersUrl = driver.getCurrentUrl();
        System.out.println(usersUrl);
        
        Thread.sleep(1000);
        driver.navigate().refresh();
        String refreshUrl = driver.getCurrentUrl();
        System.out.println(refreshUrl);
	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}