package pom.hrm.pages.Login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitUtils;

public class LoginPage{

	WebDriver driver;
	WebDriverWait wait;
	
	//To allow the page object to use the same WebDriver instance
	//Maintains browser state, enables POM structure
	//Use the same driver instance to perform actions in the same browser session.
	
	// Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
	
	// Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By loginPanelTitle = By.xpath("//h5[text()='Login']");
    By errorMessage = By.id("spanMessage");  // OrangeHRM shows error here

   

    // Actions    
    public void performLoginPage(
    		boolean isLogin,
    		String username,
    		String password) {        //maintaining code, by calling single line we can minimize the lengthy codes
    	
    	if(isLogin) {
    		WaitUtils waitUtils = new WaitUtils(driver);
			waitUtils.waitForVisibility(loginPanelTitle);
			
    		enterUsername(username);
    		enterPassword(password);
    		clickLoginButton();
    	}
    	
    }
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getLoginTitle() {
        return driver.findElement(loginPanelTitle).getText();
    }
    
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
	

  }

