import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class HandlingAccordian {

	public static void main(String[] args) {
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
				.findElements(By.cssSelector("div.relative div.hidden.md\\:block div[class*='grid units-grid']"));

		// Loop through the list to find the desired Unit ID
		for (int i = 0; i < availableUnitsTitles.size(); i++) {
			WebElement availableUnitsTitle = availableUnitsTitles.get(i);

			// Check if the unit ID matches the desired one
			if (availableUnitsTitle.getText().equalsIgnoreCase("unit ID")) {
				// Find the corresponding unit by index
				WebElement availableUnit = driver.findElement(By.cssSelector(
						"div.grid.group.units-grid > div.font-secondary.text-s:nth-child(" + (i + 1) + ")"));
				System.out.println("Unit found: " + availableUnit.getText());
				// Perform your actions here on the found unit...
				break; // Exit the loop once the desired unit is found
			}
		}

		// Close the browser after operations
		driver.quit();
	}
}
