package AmazonPom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {

	@FindBy(id = "nav-link-accountList")
	WebElement accountList;

	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

	public Header(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	public void goToAccountList() {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(accountList).build().perform();
	}

	public void waitTillElementVisible(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
