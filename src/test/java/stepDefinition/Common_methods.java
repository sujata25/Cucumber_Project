package stepDefinition;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Common_methods {
	public static WebDriver driver;
	Connection con;
	static ResultSet rst;
	ResultSet resultSet,resultSet1;
	static Statement stat;
	String host="10.0.1.86",databaseName="tatoc", username="tatocuser", password="tatoc01";
	String windowHandle,query,foundid,foundname, foundpass,cookie_value;
	
	public void connectToDataBase(String host, String databaseName, String username, String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+databaseName, username, password);
			stat = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getResultSetOnExecutingASelectQuery(String query){
		   try {
			   	rst = stat.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return rst;
	}
	
	
}
