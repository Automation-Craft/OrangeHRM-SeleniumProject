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
    	
    	loginPage.verifyLoginPageLabels();
    	
        loginPage.performLoginPage(true, username, password);      

    }
    @Test(priority = 2)
    public void verifySideMenu(){
    	sideMenuPage.performSideMenu(true, true, true, true, true);
    }
    
    
//  String actualError = loginPage.getErrorMessage();
//  String expectedError = "Invalid credentials";
//  Assert.assertEquals(actualError, expectedError, "Error message did not match!");
    
}

