package pom.hrm.tests.forgetPassword;

import org.testng.annotations.Test;

import pom.hrm.base.basePage;
import pom.hrm.pages.forgetPassword.ResetPasswordPage;

public class ForgetPasswordPageVerifications extends basePage {

	private ResetPasswordPage resetPasswordPage;

	@Test(priority = 1)
	public void verifyResetPasswordLandingPagelabels() {

		resetPasswordPage.verifyResetPasswordPageLabels(true, false);

	}

	@Test(priority = 2)
	public void verifyWithNewUsername() {

		resetPasswordPage.performresetPassword("Admin789");
	}

	@Test(priority = 3)
	public void verifyResetPasswordsuccessRequestPagelabels() {

		resetPasswordPage.verifyResetPasswordPageLabels(false, true);
	}
}
