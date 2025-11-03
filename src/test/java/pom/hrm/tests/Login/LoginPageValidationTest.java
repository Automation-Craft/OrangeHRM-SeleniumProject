package pom.hrm.tests.Login;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.hrm.base.basePage;
import pom.hrm.dataProvider.LoginTestDataProvider;
import pom.hrm.pages.Login.LoginPage;
import pom.hrm.pages.Dashboard.SideMenu.SideMenu;
import pom.hrm.utils.ConfigReader;

public class LoginPageValidationTest extends basePage{


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

   
    @Test(priority = 1, dataProvider = "inValidLoginData", dataProviderClass=LoginTestDataProvider.class)
    public void verifyLoginPageValidations(String username, String password) {
    	loginPage.performLoginPage(true, username, password,false);
    	
    	loginPage.verifyLoginPageLabels(false,true);
    }
    
    }