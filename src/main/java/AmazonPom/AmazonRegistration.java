package AmazonPom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonRegistration extends Header {

	@FindBy(linkText = "Start here.")
	WebElement newCustomerFormLink;

	@FindBy(id = "ap_customer_name")
	WebElement userName;

	@FindBy(css = "div.a-fixed-left-grid-col.a-col-left")
	WebElement diallindCountry;

	@FindBy(css = "div.a-dropdown-common")
	WebElement dropDown;

	@FindBy(css = "li.a-dropdown-item a")
	List<WebElement> countryDropdownList;

	@FindBy(id = "ap_phone_number")
	WebElement mobNum;

	@FindBy(id = "ap_password")
	WebElement pass;

	@FindBy(name = "passwordCheck")
	WebElement reenterPass;

	@FindBy(id = "continue")
	WebElement contiButton;

	//private WebDriver driver;

	public AmazonRegistration(WebDriver driver) {
		super(driver);
		this.setDriver(driver);// created setter and getter method in Header class 
		PageFactory.initElements(driver, this);
	}

	public void submitSignUpForm(String name, String countryName, String mobileNumber, String password) {
		goToAccountList();
		newCustomerFormLink.click();
		userName.sendKeys(name);
		diallindCountry.click();
		waitTillElementVisible(dropDown);
		System.out.println(countryDropdownList.size());
		//countryDropdownList.stream().map(WebElement::getText).forEach(System.out::println);
		countryDropdownList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findAny()
				.ifPresent(WebElement::click);
		mobNum.sendKeys(mobileNumber);
		pass.sendKeys(password);
		reenterPass.sendKeys(password);
		contiButton.click();

	}
}
