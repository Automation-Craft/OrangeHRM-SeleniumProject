package pom.hrm.tests.Login;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import pom.hrm.base.basePage;
import pom.hrm.dataProvider.LoginTestDataProvider;
import pom.hrm.pages.Login.LoginPage;
import pom.hrm.pages.Dashboard.SideMenu.SideMenu;
import pom.hrm.utils.ConfigReader;

public class LoginTest extends basePage {

    private LoginPage loginPage;
    private SideMenu sideMenuPage;

    @BeforeTest
    public void setUp() {
        initializeBrowser(ConfigReader.getProperty("browser")); // loads config inside PageBase
        loginPage = new LoginPage(getDriver());
        sideMenuPage = new SideMenu(getDriver());
    }

    @AfterTest
    public void cleanUp() {
        tearDown();        
    }

    @Test(priority = 1,dataProvider = "configLoginData", dataProviderClass = LoginTestDataProvider.class)
    public void loginWithConfigCredentials(String username, String password) {
    	
//    	loginPage.verifyLoginPageLabels();
    	
        loginPage.performLoginPage(true,username,password,false);      

    } 

    @Test
    public void navigateToForgetPassword() {
    	
    	loginPage.performLoginPage(false,null,null,true);
    }
    
    
//  String actualError = loginPage.getErrorMessage();
//  String expectedError = "Invalid credentials";
//  Assert.assertEquals(actualError, expectedError, "Error message did not match!");
    
}

