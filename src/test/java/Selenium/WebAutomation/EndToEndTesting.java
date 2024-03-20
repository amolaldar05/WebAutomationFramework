package Selenium.WebAutomation;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndTesting {
	SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "testData", enabled = false)
	public void loginSuccess(String userName, String Pass) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(Pass);
		driver.findElement(By.id("login-button")).click();

		softAssert.assertEquals(driver.findElement(By.cssSelector("div.error-message-container h3")).getText(),
				"Epic sadface: Password is required");
		driver.close();

	}

	@DataProvider
	public Object[][] testData() {
		return new Object[][] { { "standard_user", "secret_sauce" } };
	}

	@Test(dataProvider = "testData")
	public void endToEndTest(String userName, String Pass) {
		String addedProductName = null;
		String zipCode = "77777";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Login
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(Pass);
		driver.findElement(By.id("login-button")).click();
		// Add products to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String a[] = { "Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie" };
		// int productNeedTobeAdded = a.length;
		List<String> productsToAdd = Arrays.asList(a);
		Iterator<String> productListed = productsToAdd.iterator();
		List<WebElement> productLists = driver.findElements(By.cssSelector("div.inventory_item_name "));
		wait.until(ExpectedConditions.visibilityOf(productLists.get(1)));
//		System.out.println("A" + productLists.size());

		List<WebElement> addToCards = driver
				.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory "));
		wait.until(ExpectedConditions.visibilityOf(addToCards.get(1)));
//		System.out.println("B" + addToCards.size());
		while (productListed.hasNext()) {
			String prod = productListed.next();
			for (int i = 0; i < productLists.size(); i++) {

				if (productLists.get(i).getText().equalsIgnoreCase(prod)) {
					addedProductName = productLists.get(i).getText();
					addToCards.get(i).click();
					break;
				}
			}

		}
		// Verify products in cart
		driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
		List<WebElement> productsInCart = driver.findElements(By.cssSelector("div.inventory_item_name"));
		Assert.assertEquals(productsToAdd.size(), productsInCart.size());
		for (WebElement product : productsInCart) {
			Assert.assertTrue(productsToAdd.contains(product.getText()));
		}

		// Checkout
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.name("firstName")).sendKeys(userName);
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@placeholder='Zip/Postal Code']")).sendKeys(zipCode);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// ArrayList<Integer> al = new ArrayList<Integer>();
		int totalAddPrice = 0;
		List<WebElement> priceElements = driver.findElements(By.cssSelector("div.inventory_item_price"));

		for (WebElement priceElement : priceElements) {
			String priceText = priceElement.getText().split("$")[1].trim();
			double priceDouble = Double.parseDouble(priceText);
			System.out.println(priceDouble);
			totalAddPrice += priceDouble;

		}
		String totalPri = driver.findElement(By.cssSelector("div.summary_info_label.summary_total_label")).getText();
		System.out.println(totalPri);
		String total = totalPri.split("$")[1].trim();
//		String total = driver.findElement(By.cssSelector("div.summary_info_label.summary_total_label")).getText()
//				.split("$")[1].trim();
		double totalPrice = Double.parseDouble(total);
		softAssert.assertEquals(totalPrice, totalAddPrice);
		driver.findElement(By.id("finish")).click();
		String successMsg = driver.findElement(By.cssSelector("h2.complete-header")).getText();
		softAssert.assertEquals(successMsg, "Thank you for your order!");
		driver.findElement(By.xpath("//button[text()='Back Home']")).click();
		driver.close();
		System.out.println()
		;

	}

}

