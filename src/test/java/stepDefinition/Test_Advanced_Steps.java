package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Test_Advanced_Steps extends Common_methods {
	
	
	@Given("^User is on Tatoc Home Page Advanced$")
	public void user_is_on_Home_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver","D:\\sujata\\SUJATA\\Software\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
        driver.get("http://10.0.1.86/tatoc");
	}
 
	@When("^User selects advanced course$")
	public void user_Selects_Advanced_Course() throws Throwable {
		driver.findElement(By.xpath("//a[contains(@href,'tatoc/advanced')]")).click();
		driver.findElement(By.xpath("//*[@class='menutitle' and text()='Menu 2']")).click();	 
		driver.findElement(By.xpath("//*[contains(@onclick, 'gonext') and text()='Go Next']")).click();	 
	}
 
	@When("^User will enter db details$")
	public void fetch_DatabaseDetails() throws SQLException {
		connectToDataBase(host, databaseName, username, password);
		String value = driver.findElement(By.xpath("//*[@id='symboldisplay' and @name='symboldisplay']")).getText();
		resultSet = stat.executeQuery("select id from identity where symbol='" + value + "';");
		if(resultSet.next()){
		   	  foundid = resultSet.getString(1);
		}
		query = "select name,passkey FROM credentials where id='" + foundid + "'";
		resultSet1 = stat.executeQuery("select name,passkey FROM credentials where id='" + foundid + "';");
		if(resultSet1.next()){
		   	  foundname = resultSet1.getString(1);
		   	  foundpass = resultSet1.getString(2);
		}
		driver.findElement(By.xpath("//*[@id='name' and @type='text']")).sendKeys(foundname);	 
		driver.findElement(By.xpath("//*[@id='passkey' and @type='password']")).sendKeys(foundpass);	 
		Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='name' and @type='text']")).getText());
		Assert.assertNotNull(driver.findElement(By.xpath("//*[@id='passkey' and @type='password']")).getText());
	}
	
	
	@Then("^Task completed$")
	public void task_completed() {
		driver.findElement(By.xpath("//*[@id='submit' and @type='submit' and @value='Proceed']")).click();	 
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Ooyala Video Player']")).getText(), "Ooyala Video Player");
	}
	

	@Then("^Quit Chrome Browser$")
	public void close() {
		driver.quit();
	} 
	
	
}