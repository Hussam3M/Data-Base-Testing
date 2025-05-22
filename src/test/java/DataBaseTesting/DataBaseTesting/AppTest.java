package DataBaseTesting.DataBaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	Connection con;
	Statement stmt ;
	ResultSet rs;
	WebDriver driver =new ChromeDriver();
	String customerName;
	String customerLastName;
	String CustomerEmail;
    @BeforeTest
    public void setup() throws SQLException {
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "123456789");
    driver.get("https://smartbuy-me.com/ar/account/register");
    driver.manage().window().maximize();
   }

@Test (priority = 1)
public void InsertIntoDatabase() throws SQLException {
	String query="INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (113, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);";
stmt = con.createStatement();
int rowEfected = stmt.executeUpdate(query);
System.out.println(rowEfected);
}

@Test(priority = 2)
public void updateDataBase() throws SQLException {
	
	String query="UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999";
	stmt = con.createStatement();
	int rowEfected = stmt.executeUpdate(query);
	System.out.println(rowEfected);
} 

@Test(priority = 3)
public void ReadDataBase() throws SQLException {
	
	String query="SELECT * FROM customers WHERE customerNumber = 103;";
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);

while (	rs.next()) {
	customerName = rs.getNString("contactFirstName");
	System.out.println(customerName);
			
}
driver.findElement(By.id("customer[first_name]")).sendKeys(customerName);

}

@Test(priority = 4)
public void addLastName() throws SQLException {
	
	String query="SELECT * FROM customers WHERE customerNumber = 103;";
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);

while (	rs.next()) {
	customerLastName = rs.getNString("contactLastName");
	System.out.println(customerLastName);
			
}
driver.findElement(By.id("customer[last_name]")).sendKeys(customerLastName);

}

@Test(priority = 5)
public void addCustomerEmail() throws SQLException {
	
	String query="SELECT * FROM customers WHERE customerNumber = 103;";
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);

while (	rs.next()) {
	customerName = rs.getNString("contactFirstName");
	customerLastName = rs.getNString("contactLastName");
 CustomerEmail = customerName +customerLastName+"@gmail.com"; 
	System.out.println(CustomerEmail);
			
}
driver.findElement(By.id("customer[email]")).sendKeys(CustomerEmail);

}

@Test(priority = 6)
public void deleteCustomer() throws SQLException {
	
	String query = "DELETE FROM customers WHERE customerNumber = 113;";
	int rowDeleted = stmt.executeUpdate(query);
	System.out.println(rowDeleted);
}
}