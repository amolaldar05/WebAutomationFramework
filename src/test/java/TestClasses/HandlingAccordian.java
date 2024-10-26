package TestClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingAccordian {

    WebDriver driver;

    @BeforeTest
    public void presetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stg.damacproperties.com/en-gb/projects/damac-tower-nine-elms-london/for-sale/properties/2-apartment/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 0)
    public void test() throws InterruptedException {
        // Click on 'Select Unit' button
        driver.findElement(By.xpath("//button[text()='Select Unit']")).click();

        // Locate the list of available units
        List<WebElement> availableUnitsTitles = driver.findElements(By.cssSelector(
                "div.relative div.hidden.md\\:block div[class*='grid units-grid'] div"));

        // Iterate through the available units to find the one with the specified size
        for (int i = 0; i < availableUnitsTitles.size(); i++) {
            if (availableUnitsTitles.get(i).getText().equalsIgnoreCase("unit id")) {
                // Locate the corresponding unit elements
                List<WebElement> availableUnits = driver.findElements(By.cssSelector(
                        "div.grid.group.units-grid > div.font-secondary.text-s:nth-child(" + (i + 1) + ")"));

                // Print all available unit texts
                availableUnits.stream().map(WebElement::getText).forEach(System.out::println);

                // Find and click the specific unit
                availableUnits.stream()
                    .filter(availableUnit -> availableUnit.getText().equalsIgnoreCase("AYK/4/408"))
                    .findFirst()
                    .ifPresent(unit -> handleUnitClick(unit, availableUnits));
            }
        }
    }

    private void handleUnitClick(WebElement unit, List<WebElement> availableUnits) {
        // Click the unit to make the "Reserve now" button visible
        unit.click();
        int index = availableUnits.indexOf(unit);

        // Locate the "Reserve now" button that becomes visible after clicking the unit
        WebElement reserveButton = driver.findElements(By.xpath("//button[text()='Reserve now']")).get(index + 1);

        // Scroll to the "Reserve now" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reserveButton);

        // Click the "Reserve now" button if it's displayed
        if (reserveButton.isDisplayed()) {
            reserveButton.click();
        }
    }

    @Test(priority = 1, enabled = false)
    public void goToPropType() {
        // Placeholder for another test method
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
