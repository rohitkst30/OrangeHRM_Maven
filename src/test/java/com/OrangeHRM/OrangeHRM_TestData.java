package com.OrangeHRM;

import org.testng.annotations.DataProvider;

public class OrangeHRM_TestData {
	
	//It's recommended that all the data should come from a single file.
	
	@DataProvider(name = "Login")
	public Object[][] getDataforLogin() {
		
		return new Object[][] {
				{"Admin", "admin123" },
				{"kumar", "admin123" },
				 {"dixit", "admin123" } 
				};
	}
	
	  @DataProvider(name = "LoginScenario")
	  public Object[][] getDataforLoginDifferentScenarios() {
		  return new Object[][] {
			  //Negative scenarios.
			  { "admin", "", "Password cannot be empty"}, 
			  { "", "admin123", "Username cannot be empty" }, 
			  { "AdminWrong", "admin123", "Invalid credentials" }, 
			  { "admin", "admin", "Invalid credentials" }, 
			  { "admin", "admin123", "Dashboard" } //Positive scenario.
		  };
	  }
	  
	@DataProvider(name = "LoginExcelData")
	public Object[][] Authentication() throws Exception{
		ReadExcel excel = new ReadExcel();
		String RelativePath = System.getProperty("user.dir");// This gives the project's root location.
		//Object[][] testObjArray = excel.getExcelData("C:\\Users\\adixit\\git\\abhikdixit-Maven_Selenium_WebDriver_4\\Maven_Selenium_WebDriver_4\\OrangeHRM_TestData.xlsx","SignIn");
		Object[][] testObjArray = excel.getExcelData(RelativePath+"\\OrangeHRM_TestData.xlsx","SignIn");
		System.out.println(testObjArray);
		
		return testObjArray;
	}
}
