package Cross_Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallerTest {
    WebDriver driver;

    @Parameters("browserName")
    @BeforeMethod
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
            driver = new EdgeDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            // Safari does not support headless mode.
            driver = new SafariDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().window().maximize();
    }

    @Test
    public void openYouTube() {
        driver.get("https://www.youtube.com/");
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
