package SignUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPrac {

    // Define test data
    String[][] data = {{"abc", "xyz"}, {"jivan@gmail.com", "Jivan@9900"}};

    // Declare WebDriver and page title variable
    WebDriver driver;
    String pageTitle;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void loginWithCorrectCred() throws InterruptedException {
        // Open the login page
        driver.get("https://rahulshettyacademy.com/client/auth/login");

        // Iterate over the test data
        for (String[] testData : data) {
            String userEmail = testData[0];
            String password = testData[1];

            // Attempt login
            isLoginSuccessful(userEmail, password);

            // Check if login was successful
            if (pageTitle.toLowerCase().contains("shop")) {
                System.out.println("Login successful with user: " + userEmail);
                break; // Stop testing further credentials if login is successful
            } else {
                System.out.println("Login failed with user: " + userEmail);
            }
        }
    }

    public void isLoginSuccessful(String userEmail, String password) throws InterruptedException {
        // Clear any pre-filled values in the fields
        driver.findElement(By.id("userEmail")).clear();
        driver.findElement(By.id("userPassword")).clear();

        // Input credentials and click login
        driver.findElement(By.id("userEmail")).sendKeys(userEmail);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        // Wait for the page to load
        Thread.sleep(2000); // Use WebDriverWait in a real scenario for better synchronization

        // Get the page title
        pageTitle = driver.getTitle();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
