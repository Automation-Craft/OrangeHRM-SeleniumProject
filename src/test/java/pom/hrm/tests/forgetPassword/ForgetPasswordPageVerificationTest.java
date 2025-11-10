package pom.hrm.tests.forgetPassword;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.hrm.base.basePage;
import pom.hrm.pages.Login.LoginPage;
import pom.hrm.pages.forgetPassword.ResetPasswordPage;
import pom.hrm.utils.ConfigReader;

public class ForgetPasswordPageVerificationTest extends basePage {

	private ResetPasswordPage resetPasswordPage;
	private LoginPage loginPage;

	@BeforeTest
	public void setUp() {
		initializeBrowser(ConfigReader.getProperty("browser")); // loads config inside PageBase
		resetPasswordPage = new ResetPasswordPage(getDriver());
	}

	@AfterTest
	public void cleanUp() {
		tearDown();
	}

	@Test(priority = 1)
	public void navigateToForgetPassword() {
		System.out.println("Click on Forget password button");
		loginPage.performLoginPage(false, null, null, true);
		System.out.println("Navigated to Forget Password page successfully");
	}

	@Test(priority = 1)
	public void verifyResetPasswordLandingPagelabels() {

		resetPasswordPage.verifyResetPasswordPageLabels(true, false);

	}

	@Test(priority = 2)
	public void verifyWithNewUsername() {
		System.out.println("Entering new Username");
		resetPasswordPage.performresetPassword("Admin789");
	}

	@Test(priority = 3)
	public void verifyResetPasswordsuccessRequestPagelabels() {
		
		resetPasswordPage.verifyResetPasswordPageLabels(false, true);
	}
}
