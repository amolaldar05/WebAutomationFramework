package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPom extends Header {
	
	@FindBy(xpath="(//span[@class='nav-action-inner'][normalize-space()='Sign in'])[1]")
	WebElement logInButton;
	
	@FindBy(id="ap_email")
	WebElement emailMobNumField;

	@FindBy(xpath="//input[@id='continue']")
	WebElement continueButton;
	
	public LogInPom(WebDriver driver) {
		super(driver);
		this.setDriver(driver);// created setter and getter method in Header class
		PageFactory.initElements(driver, this);
	}

	public void submitLogIn(String mobNum) {
		goToAccountList();
		logInButton.click();
		emailMobNumField.sendKeys(mobNum);
		continueButton.click();
		
	}

}
