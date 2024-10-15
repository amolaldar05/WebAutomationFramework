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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeadSubmission {
    WebDriver driver;

    @BeforeMethod
    public void lauchApp() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://promotions.damacproperties.com/uae/dubai-properties/en?goal=leadgen&digital_source=search-gsn&channelcluster=pull&keyword=damac%20properties&ad_id=671871465799&matchtype=p&network=g&utm_source=google&utm_medium=paid-search&utm_campaign=AEI-|-Performance-|-Brand-Pure-|.Dig-Search-Goog-UAE-Dub-ENG-Jul13&bidtype=cpc&campaign_id=a120700000KLryM&gad_source=1&gclid=CjwKCAjwvvmzBhA2EiwAtHVrbxSyqAEeF5TBlJ5U6Azqex8Hp4XkPyuF5VFQEINl308XM-0ye959oRoCFP8QAvD_BwE");
    }

    @Test
    public void menuTitleLink() throws InterruptedException {
    	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[id='element-__gpage-block-0e7h7uj034mp-2089'] span[class='x_045debbc x_52f6981f']"))));
    	driver.findElement(By.cssSelector("div[id='element-__gpage-block-0e7h7uj034mp-2089'] span[class='x_045debbc x_52f6981f']")).click();
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#element-__gpage-block-1j8l3co7njv-1557"))));
    	
    	System.out.println(driver.findElement(By.cssSelector("div[id='element-__gpage-block-0e7h7uj034mp-2089'] span[class='x_045debbc x_52f6981f']")).getText());
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        System.out.println("executed");
    }
    
    @AfterMethod
    public void tearDown() {
    	driver.quit();
    }
}
