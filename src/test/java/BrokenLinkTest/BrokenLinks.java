package BrokenLinkTest;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenLinks {
    WebDriver driver;

    @BeforeMethod
    public void lauchApp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://preprod.damacproperties.com/en/");
    }

    @Test
    public void menuTitleLink() {
        // Click on the menu icon to expand the menu
        driver.findElement(By.cssSelector("a[class*='desktopMenuIcon']")).click();

        // Find all menu titles
        List<WebElement> menuTitles = driver.findElements(By.cssSelector("ul[class*='mainMenu'] li a"));
        
        // Iterate over each menu title
        for (WebElement title : menuTitles) {
            // Get the URL of the menu item
            String url = title.getAttribute("href");
            
            // Open the link in a new tab using JavaScript
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", url);
        }

        // Switch to each newly opened tab and print the title
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            System.out.println(driver.getTitle());
        }
    }
    
    @AfterMethod
    public void closeApp() {
    	driver.quit();
    }
}
