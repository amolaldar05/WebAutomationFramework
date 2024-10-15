package SignUp;

import org.testng.annotations.Test;

import AmazonPom.AmazonRegistration;

import org.testng.annotations.Test;

import TestComponents.BaseClass;

public class NewUser extends BaseClass {

	@Test
	public void signUpTest() {
		AmazonRegistration amazonRegistration = new AmazonRegistration(driver);
		amazonRegistration.submitSignUpForm("newUser", "India +91", "8888888888", "password");
	}

}
