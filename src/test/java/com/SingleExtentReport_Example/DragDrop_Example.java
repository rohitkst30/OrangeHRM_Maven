package com.SingleExtentReport_Example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragDrop_Example {
	ChromeDriver driver;
	WebDriverWait wait;
	
  @Test
  public void f() throws InterruptedException {
	Thread.sleep(3000);
	Actions act=new Actions(driver);//Create object of actions class.

	//Find element which we need to drag
	WebElement drag=driver.findElementById("draggable");
	
	//Find element which we need to drop
	WebElement drop=driver.findElementById("droppable");
	
	act.dragAndDrop(drag, drop).build().perform();//This will drag the element to destination.
	
	Thread.sleep(1000);
  }
 
	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
//		driver.navigate().to("https://jqueryui.com/resources/demos/droppable/default.html");// This will remember our cookies & history.
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");// We can use this instead of driver.navigate().to 
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
