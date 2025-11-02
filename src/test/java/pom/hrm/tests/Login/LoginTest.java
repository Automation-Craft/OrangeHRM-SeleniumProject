package pom.hrm.tests.Login;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import pom.hrm.base.basePage;
import pom.hrm.dataProvider.LoginTestDataProvider;
import pom.hrm.pages.Login.LoginPage;
import pom.hrm.pages.dashboard.SideMenu.SideMenu;
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
    	
    	test = extent.createTest("loginWithConfigCredentials");
    	test.info("Launching browser and navigating to login page");
        loginPage.performLoginPage(true,username,password,false);
        
        test.pass("Login test executed successfully");

    } 

    @Test(enabled = false)
    public void navigateToForgetPassword() {
    	
    	loginPage.performLoginPage(false,null,null,true);
    }
    
    @Test(priority = 2)
    public void verifySideMenu(){
    	test = extent.createTest("verifySideMenu");
    	test.info("Verify and navigation of sidemenu functions");
    	sideMenuPage.performSideMenu(true, true, true, true, true);
    	
    	test.pass("Verification and Navigation of Side Menu completed Successfully");
    }
    
//  String actualError = loginPage.getErrorMessage();
//  String expectedError = "Invalid credentials";
//  Assert.assertEquals(actualError, expectedError, "Error message did not match!"); 
}