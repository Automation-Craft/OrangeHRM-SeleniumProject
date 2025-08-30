package pom.hrm.tests.Login;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.hrm.dataProvider.LoginTestDataProvider;
import pom.hrm.base.basePage;
import pom.hrm.pages.Login.LoginPage;
import pom.hrm.utils.ConfigReader;

public class LoginTest extends basePage {

    private LoginPage loginPage;

    @BeforeTest
    public void setUp() {
        initializeBrowser(ConfigReader.getProperty("browser")); // loads config inside PageBase
        loginPage = new LoginPage(getDriver());
    }

    @AfterTest
    public void cleanUp() {
        tearDown();        
    }

    @Test(priority = 1,dataProvider = "configLoginData", dataProviderClass = LoginTestDataProvider.class)
    public void loginWithConfigCredentials(String username, String password) {
        loginPage.performLoginPage(true, username, password);      

    }
    
//  String actualError = loginPage.getErrorMessage();
//  String expectedError = "Invalid credentials";
//  Assert.assertEquals(actualError, expectedError, "Error message did not match!");
    
}

