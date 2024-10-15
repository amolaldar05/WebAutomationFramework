import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import TestComponents.BaseClass;

public class RadioButton extends BaseClass {
	
	
public void launchApp() {
	WebDriver driver= new ChromeDriver();
	driver.get("https://demoqa.com/elements");
	driver.manage().window().maximize();
}
	public void radioButton() {
		List<WebElement> sidsebars=driver.findElements(By.cssSelector(".text"));
		for(WebElement sidebar: sidsebars) {
			if(sidebar.getText().contains("radio")) {
				sidebar.click();
			}	
		}
		List<WebElement> buttons=driver.findElements(By.cssSelector(".custom-control-label"));
		for(WebElement button: buttons) {
			if(button.getText().equalsIgnoreCase("Yes")) {
				button.click();
			}	
		}
		
		
	}
	
	public void tearDown() {
		driver.quit();
	}
}
