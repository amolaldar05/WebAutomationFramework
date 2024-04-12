package UploadDownloadFileTest;

/*import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadFile {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("#downloadButton")).click();
		// here attribute value is file type so directly use pathe using sendkeys to
		// upload a file
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("/Users/catalinalive/Downloads/download.xlsx");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By toastLocator = By.xpath("//div[contains(text(),'Updated Excel Data Successfully.')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		//dynamic column with getattribute
		String fruitNameColumn=driver.findElement(By.xpath("//div[text()='Fruit Name']")).getAttribute("data-column-id");
		List<WebElement> fruitNameList = driver
				.findElements(By.cssSelector("div.sc-jsEeTM.itluUR.rdt_TableRow div#cell-"+fruitNameColumn+"-undefined"));
		String priceColumn=driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		List<WebElement> priceList = driver
				.findElements(By.cssSelector("div.sc-jsEeTM.itluUR.rdt_TableRow div#cell-"+priceColumn+"-undefined"));
		for (int i = 0; i < fruitNameList.size(); i++) {
			if (fruitNameList.get(i).getText().equalsIgnoreCase("Kivi")) {
				System.out.println(priceList.get(i).getText());
				break;
			}
		}

	}

}*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UploadDownload {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fruitName = "Apple";
		String updatedValue = "603";
		String fileName = "/Users/catalinalive/Downloads/download.xlsx";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		// download
		driver.findElement(By.cssSelector("#downloadButton")).click();
		// Edit excel - getColumnNumber of Price -getRownumber of APple-> update excel
		// with row,col
		int col = getColumnNumber(fileName, "price");
		int row = getRowNumber(fileName, "Apple");
		Assert.assertTrue(updateCell(fileName, row, col, updatedValue));
		// upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("/Users/catalinalive/Downloads/download.xlsx");
		// wait for success message to show up and wait for disappear
		By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		String toastText = driver.findElement(toastLocator).getText();
		System.out.println(toastText);
		Assert.assertEquals("Updated Excel Data Successfully.", toastText);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		// verify updated excel data showing in the web table
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
		System.out.println(actualPrice);
		Assert.assertEquals(updatedValue, actualPrice);
	}

	private static boolean updateCell(String fileName, int row, int col, String updatedValue) throws IOException {

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowField = sheet.getRow(row - 1);
		Cell cellField = rowField.getCell(col - 1);
		cellField.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;
	}

	private static int getRowNumber(String fileName, String text) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		// Identify Testcases coloum by scanning the entire 1st row
		Iterator<Row> rows = sheet.iterator();
		// sheet is collection of rows
		int k = 1;
		int rowIndex = -1;
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {
					rowIndex = k;
				}

			}
			k++;
		}
		return rowIndex;
	}

	private static int getColumnNumber(String fileName, String colName) throws IOException {

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		// Identify Testcases coloum by scanning the entire 1st row
		Iterator<Row> rows = sheet.iterator();
		// sheet is collection of rows
		Row firstrow = rows.next();
		Iterator<Cell> ce = firstrow.cellIterator();
		// row is collection of cells
		int k = 1;
		int coloumn = 0;
		while (ce.hasNext()) {
			Cell value = ce.next();
			if (value.getStringCellValue().equalsIgnoreCase(colName)) {
				coloumn = k;
			}
			k++;
		}
		System.out.println(coloumn);
		return coloumn;
	}
}
