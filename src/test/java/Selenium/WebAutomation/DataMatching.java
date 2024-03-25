package Selenium.WebAutomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataMatching {
	WebDriver driver;

	@BeforeMethod
	public void lauchApp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://uk.damacproperties.com/en/projects/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void dataMatch() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("span.toggleAvailableUnits-module--projectPageSwitchslider--64bcd")).click();
		driver.findElement(By.cssSelector("button.mapListSwitcher-module--mapListSwitcher--eab2e ")).click();

		List<WebElement> projectElements = driver
				.findElements(By.cssSelector("div.listingCard-module--title--d7bd7.fontWeight500"));
		List<WebElement> priceElements = driver
				.findElements(By.cssSelector("div.listingCard-module--price--17f13.fontWeight500"));

		// Extract project names from WebElements
		List<String> projectNames = projectElements.stream().
				map(WebElement::getText).collect(Collectors.toList());

		List<String> priceOnProjects = priceElements.stream().
				map(WebElement::getText).collect(Collectors.toList());
		
		List<String> elements = new ArrayList<>();

		
		 // Combine project names and prices into a single iteration
	    for (int i = 0; i < projectElements.size(); i++) {
	    	
	        String projectName = projectElements.get(i).getText();
	        String priceOnProject = priceElements.get(i).getText();
	        elements.add(projectName); 

	        System.out.println("Project Name: " + projectName+" " + priceOnProject);
	        
	    }
	    
	    
		// Print project names
		System.out.println("Project Names:");
		for (String projectName : projectNames) {
			System.out.println(projectName);
		}
		
		//print project price
		System.out.println("Project Names:");
		for (String priceOnProject : priceOnProjects) {
			System.out.println(priceOnProject);
		}

	}

}
