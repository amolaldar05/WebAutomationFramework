import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import TestComponents.BaseClass;

public class EmiratesSignUpTest extends BaseClass {

	@Test
	public void signUp() throws InterruptedException {

		driver.findElement(By.name("Departure airport")).clear();
		driver.findElement(By.name("Departure airport")).sendKeys("Du");
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		List<WebElement> depName = driver.findElements(By.cssSelector(".location__airport"));
		for(WebElement name:depName) {
			if(name.getText().contains("Dubai")) {
				name.click();
			}
		}
//		depName.stream().filter(name->name.getText().contains("Dubai")).findFirst().orElse(null);
//		depName.stream().map(WebElement::getText).forEach(System.out::println);
		
		Thread.sleep(5000);

	}
}
