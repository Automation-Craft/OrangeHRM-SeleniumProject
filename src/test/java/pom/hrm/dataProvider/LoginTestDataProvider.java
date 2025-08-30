package pom.hrm.dataProvider;

import org.testng.annotations.DataProvider;

import pom.hrm.utils.ConfigReader;

public class LoginTestDataProvider {

	 @DataProvider(name = "validLoginData")
	    public Object[][] validLoginData() {
	        return new Object[][] {
	            {"Admin", "admin123"},
	            {"TestUser", "testpass"}
	        };
	    }
	 
	 
	 
	 @DataProvider( name = "configLoginData")
	 public Object[][] configLoginData(){
	
		 String username = ConfigReader.getProperty("dummyusername");
		 String password = ConfigReader.getProperty("dummypassword");
		 
		 return new Object[][]  {
			 
			 {username,password}
		 };
	 }
}
