package pom.hrm.pages.Login;

import java.time.Duration;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utilities.WaitUtils;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	// To allow the page object to use the same WebDriver instance
	// Maintains browser state, enables POM structure
	// Use the same driver instance to perform actions in the same browser session.

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators
	private By usernameInputField = By.name("username");
	private By passwordInputField = By.name("password");
	private By loginButton = By.cssSelector("button[type='submit']");
	public By loginPanelTitle = By.cssSelector("h5.orangehrm-login-title");
	public By usernameLabel = By.xpath("//label[text()=\"Username\"]");
	public By passwordLabel = By.xpath("//label[text()=\"Password\"]");
	public By forgetPasswordLabel = By.cssSelector("p.orangehrm-login-forgot-header");
	By usernameRequiredLabel = By
			.xpath("//input[@name='username']/ancestor::div/following-sibling::span[text()='Required']");
	By passwordRequiredLabel = By
			.xpath("//input[@name='password']/ancestor::div/following-sibling::span[text()='Required']");
	By incorrectCredentials = By.xpath("//p[contains(normalize-space(),'Invalid credentials')]");
    By forgetPassword = By.linkText("Forgot your password?");
	
	
	// Actions
	public void performLoginPage(
			boolean isLogin, 
			String username,
			String password,
			boolean isforgetPassword) { // maintaining code, by calling
																						// single line we can minimize
																						// the lengthy codes

		if (isLogin) {
			WaitUtils waitUtils = new WaitUtils(driver);
			waitUtils.waitForVisibility(loginPanelTitle);

			enterUsername(username);
			enterPassword(password);
			clickLoginButton();
		}
		if(isforgetPassword) {
			
			clickForgetPassword();
		}

	}

	// Hard Assert:
	// If an assertion fails, test execution stops immediately at that line.
	// Remaining code in that test will not run.
//	I use Hard Assert for critical checkpoints where further steps depend on that condition. 
//	I use Soft Assert for UI verification or multiple validations in a single test case, 
//	so I get a full defect report in one run.    
	// Soft Assert:
	// Even if an assertion fails, test execution continues. At the end, you call
	// softAssert.assertAll() to report all failures together.

	public void verifyLoginPageLabels(boolean isLoginPage, boolean isNegativeVerification) {

		WaitUtils waitUtils = new WaitUtils(driver);
		waitUtils.waitForVisibility(loginPanelTitle);
		SoftAssert softAssert = new SoftAssert();

		// Hard Assert example
//    	Assert.assertEquals(driver.findElement(loginPanelTitle).getText(), "Login", "❌ Title not verified");    	
		// Soft Assert example

		if (isLoginPage) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Login title
			String loginText = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPanelTitle)).getText();
			System.out.println("DEBUG >>> Login title = '" + loginText + "'");
			softAssert.assertEquals(loginText.trim(), "Login", "❌ Login title mismatch");

			// Username label
			String userLabel = driver.findElement(usernameLabel).getText();
			System.out.println("DEBUG >>> Username label = '" + userLabel + "'");
			softAssert.assertEquals(userLabel.trim(), "Username", "❌ Username label mismatch");

			// Password label
			String passLabel = driver.findElement(passwordLabel).getText();
			System.out.println("DEBUG >>> Password label = '" + passLabel + "'");
			softAssert.assertEquals(passLabel.trim(), "Password", "❌ Password label mismatch");

			// Forgot Password label
			String forgotLabel = driver.findElement(forgetPasswordLabel).getText();
			System.out.println("DEBUG >>> Forgot password label = '" + forgotLabel + "'");
			softAssert.assertEquals(forgotLabel.trim(), "Forgot your password?", "❌ Forgot password label mismatch");

		}

		if (isNegativeVerification) {

			clickLoginButton();
			
			//soft assert
			String usernameRequiredValidation = driver.findElement(usernameRequiredLabel).getText();
			softAssert.assertEquals(usernameRequiredValidation.trim(), "Required",
					"❌ Username Mandatory Validation mismatch");

			String passwordRequiredValidation = driver.findElement(passwordRequiredLabel).getText();
			softAssert.assertEquals(passwordRequiredValidation.trim(), "Required",
					"❌ Password Mandatory Validation mismatch");

			// Report all results
			softAssert.assertAll();

			// hard assert
			Assert.assertEquals(driver.findElement(incorrectCredentials).getText(), "Invalid credentials",
					"❌ Validation not verified");

		}

	}

	public void enterUsername(String username) {
		driver.findElement(usernameInputField).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordInputField).sendKeys(password);
	}

	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}

	public String getLoginTitle() {
		return driver.findElement(loginPanelTitle).getText();
	}
	public void clickForgetPassword() {
		driver.findElement(forgetPassword).click();
	}

}
