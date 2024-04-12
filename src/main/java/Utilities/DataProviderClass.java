package Utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="testData")
	public Object[][] getData() {
		return new Object[][] {{"8888888888"},{"testuser@gmail.yahoo"}};
	}

}
