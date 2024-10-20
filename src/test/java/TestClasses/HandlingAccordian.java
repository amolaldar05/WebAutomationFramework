package TestClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingAccordian {

	@Test
	public void test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get(
				"https://stg.damacproperties.com/en-gb/projects/damac-tower-nine-elms-london/for-sale/properties/2-apartment/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Click on 'Select Unit' button
		driver.findElement(By.xpath("//button[text()='Select Unit']")).click();

		// Locate the list of available units
		List<WebElement> availableUnitsTitles = driver
				.findElements(By.cssSelector("div.relative div.hidden.md\\:block div[class*='grid units-grid'] div"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", availableUnitsTitles.get(0));
		Thread.sleep(2000);
		for (int i = 0; i < availableUnitsTitles.size(); i++) {
			if (availableUnitsTitles.get(i).getText().equalsIgnoreCase("unit id")) {
				List<WebElement> availableUnits = driver.findElements(By.cssSelector(
						"div.grid.group.units-grid > div.font-secondary.text-s:nth-child(" + (i + 1) + ")"));

				availableUnits.stream().map(WebElement::getText).forEach(System.out::println);
				for (int j = 0; j < availableUnits.size(); j++) {

					if (availableUnits.get(j).getText().equalsIgnoreCase("AYK/5/501")) {
						availableUnits.get(i).click();
						driver.findElements(By.xpath("//button[text()='Reserve now']")).get(i + 1).click();
					}
				}

				
			}
		}

		driver.quit();

	}
}
