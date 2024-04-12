package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataBaseConnectionTest {

	public static void main(String[] args) throws SQLException {
		String host = "localhost";
		String port = "3306";
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/Credentials", "root",
				"Mysql@7799");
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("Select * from Login where AccountNmame='Amazon'");
		while (rs.next()) {
			System.out.println(rs.getString("AccountNmame"));
			System.out.println(rs.getString("userName"));
			System.out.println(rs.getString("Password"));
			WebDriver driver = new ChromeDriver();
			driver.get(
					"https://www.amazon.com/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Flog%2Fs%3Fk%3Dlog%2Bin%26ref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
			driver.findElement(By.id("ap_email")).sendKeys(rs.getString("Password"));

		}

	}

}
