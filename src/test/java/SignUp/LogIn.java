package SignUp;

import org.testng.annotations.Test;

import AmazonPom.LogInPom;

import org.testng.annotations.Test;

import TestComponents.BaseClass;
import Utilities.DataProviderClass;

public class LogIn extends BaseClass {

	// positive scenario

	@Test(dataProvider = "testData", dataProviderClass = DataProviderClass.class)
	public void logInTest(String mobNum) {
		LogInPom logInPom = new LogInPom(driver);
		logInPom.submitLogIn(mobNum);
		//checking webhook
	}
}
