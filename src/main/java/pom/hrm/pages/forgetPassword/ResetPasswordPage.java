package pom.hrm.pages.forgetPassword;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pom.hrm.base.basePage;

public class ResetPasswordPage extends basePage {

	// They’ll store the browser driver (like ChromeDriver) and an explicit wait

	WebDriver driver; // instance variables
	WebDriverWait wait; // instance variables

	// this.driver → refers to your class variable (declared at the top of the
	// class)
	// constructor method name should be same as class name

	// Constructor

	public ResetPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// locators
	By resetPasswordlabel = By.xpath("//h6[text()= \"Reset Password\"]");
	By subTitlelabel = By
			.xpath("//p[text()='Please enter your username to identify your account to reset your password']");
	By usernamelabel = By.xpath("//label[text()=\"Username\"]");
	By inputUsername = By.name("username");
	By resetPasswordButton = By.name("//button[text()=\" Reset Password \"]");
	By resetPasswordLinkLabel = By.xpath("//h6[text() = \"Reset Password link sent successfully\"]");
	By aResetPasswordLinkLabel = By.xpath("//p[text()='A reset password link has been sent to you via email.']");
	By youCanFollowLabel = By.xpath("//p[text()='You can follow that link and select a new password.']");
	By noteLabel = By.xpath("//p[text()= 'Note: ']");
	By ifTheEmailLabel = By
			.xpath("//p[text()='If the email does not arrive, please contact your OrangeHRM Administrator.']");

	// Page Methods
	public void performresetPassword(String newUsername) {

		this.enterNewUsername(newUsername);
		this.clickresetPasswordButton();

	}

	public void verifyResetPasswordPageLabels(boolean isBeforeResetPage, boolean isAfterResetRequest) {

		if (isBeforeResetPage) {
			Assert.assertEquals(driver.findElement(resetPasswordlabel).getText(), "Reset Password",
					"❌ Text not matching");
			Assert.assertEquals(driver.findElement(subTitlelabel).getText(),
					"Please enter your username to identify your account to reset your password",
					"❌ Text not matching");
			Assert.assertEquals(driver.findElement(usernamelabel).getText(), "Username", "❌ Text not matching");

			if (isAfterResetRequest) {

				Assert.assertEquals(driver.findElement(resetPasswordLinkLabel).getText(),
						"Reset Password link sent successfully", "❌ Text not matching");

				Assert.assertEquals(driver.findElement(aResetPasswordLinkLabel).getText(),
						"A reset password link has been sent to you via email.", "❌ Text not matching");

				Assert.assertEquals(driver.findElement(youCanFollowLabel).getText(),
						"You can follow that link and select a new password.", "❌ Text not matching");

				Assert.assertEquals(driver.findElement(noteLabel).getText(), "Note: ", "❌ Text not matching");

				Assert.assertEquals(driver.findElement(ifTheEmailLabel).getText(),
						"If the email does not arrive, please contact your OrangeHRM Administrator.",
						"❌ Text not matching");
			}

		}
	}

	
	
	public void enterNewUsername(String newUsername) {

		driver.findElement(inputUsername).sendKeys(newUsername);

	}

	public void clickresetPasswordButton() {

		driver.findElement(resetPasswordButton).click();
	}

}
