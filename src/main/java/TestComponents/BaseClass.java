package TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;
	private String url;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws IOException {
		initializeBrowser();
		getUrl();
	}

	private void initializeBrowser() throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Resources/browserAndUrlNames.properties");
		Properties prop = new Properties();
		prop.load(fis);

		String browserName = prop.getProperty("browserName");
		url = prop.getProperty("url");

		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser name provided in properties file: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	private void getUrl() {
		driver.get(url);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Getter method to provide access to WebDriver instance
	protected WebDriver getDriver() {
		return driver;
	}
}
