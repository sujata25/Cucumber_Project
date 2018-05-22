package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class Test_Basic_Steps extends Common_methods{
	public static WebDriver driver;
	String box1Color,box2Color,windowHandle,token_value,cookie_value;
	
	@Given("^User is on tatoc Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver","D:\\sujata\\SUJATA\\Software\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
        driver.get("http://10.0.1.86/tatoc");
	}
 
	@When("^User selects Basic Course$")
	public void user_Selects_Basic_Course() throws Throwable {
		driver.findElement(By.xpath("//a[contains(@href,'tatoc/basic')]")).click();
		driver.findElement(By.xpath("//*[@class='greenbox']")).click();; 	
	}
 
	 
	@When("^Color of boxes should be same$")
	public void click_repaintLink() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='main']")));
		box1Color = driver.findElement(By.xpath("//*[@id='answer' and text()='Box 1']")).getCssValue("background-color");
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='child']")));
		box2Color = driver.findElement(By.xpath("//*[@id='answer' and text()='Box 2']")).getCssValue("background-color");

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='main']")));
		if(box1Color.equals(box2Color)){
			driver.findElement(By.xpath("//a[contains(@onclick, 'gonext')]")).click();
		}else{
			driver.findElement(By.xpath("//a[contains(text(), 'Repaint Box 2')]")).click();
			click_repaintLink();
		}
	}
	
	@When("^User drag box in drop box$")
	public void drag_Drop() {
		Actions hoveraction = new Actions(driver);
		hoveraction.dragAndDrop(driver.findElement(By.xpath("//*[@id='dragbox' and @class='ui-draggable' and text()='DRAG ME']")), driver.findElement(By.xpath("//*[@id='dropbox' and text()='DROPBOX']"))).build().perform();
		driver.findElement(By.xpath("//a[contains(@onclick, 'gonext') and text()='Proceed']")).click();
	}
	
	@Then("^Page displayed with Title Popup Windows$")
	public void dungeon_page_displayed_Successfully() throws Throwable {
		driver.findElement(By.xpath("//h1")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Popup Windows");
		System.out.println("Login Successfully");
	}

	@Given("^User is on Launch Window Page$")
	public void user_is_on_Launch_Page() {
		System.out.println("Launch Page");
	}
	
	@When("^User selects Launch Window$")
	public void user_selects_Launch_Button() {
		driver.findElement(By.xpath("//a[contains(@onclick, 'launchwindow') and text()='Launch Popup Window']")).click();
	}
		
	@When("^User will submit name$")
	public void user_submit_name() {
		switchToNewWindow();
		driver.findElement(By.xpath("//*[@id='name']")).sendKeys("Sujata");
		driver.findElement(By.xpath("//*[@id='submit']")).click();

	}
	
	@Then("^User will click on Proceed button$")
	public void user_click_proceed() {
		driver.switchTo().window(windowHandle);
		driver.findElement(By.xpath("//a[contains(@onclick, 'gonext') and text()='Proceed']")).click();
	}
	
	@Given("^User is on Cookie Handling Page$")
	public void user_is_on_Cookie_Page() {
		System.out.println("Cookie Page");
	}
	
	@When("^User will Generate Token$")
	public void user_generate_token() {
		driver.findElement(By.xpath("//a[contains(@onclick, 'generateToken') and text()='Generate Token']")).click();
		cookie_value = getelementText("//*[@id='token']");
		set_cookies("Token",cookie_value);
	}
	
	
	@Then("^Basic Task completed$")
	public void task_completed() {
		driver.findElement(By.xpath("//a[contains(@onclick, 'gonext') and text()='Proceed']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='finish' and text()='End']")).getText(), "End");
	}
	
	@Then("^Quit Browser$")
	public void close() {
		driver.quit();
	} 
	
	public void switchToNewWindow() {
		System.out.println(driver.getWindowHandle());
		windowHandle = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);

		}
	}
	protected String getelementText(String elementToken) throws NoSuchElementException {
		String elem = null;
		String cookie_value = null ;
		try {
			elem = driver.findElement(By.xpath(elementToken)).getText();
			String splitFeild1[] = elem.split(":");
			cookie_value = splitFeild1[1].trim();
		} catch (NoSuchElementException excp) {
		} 
		return cookie_value;
	}
	   
	protected Cookie set_cookies(String key,String cookieValue) {
			org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie("Token", cookie_value);
			driver.manage().addCookie(cookie);
			return cookie;
	}
		
}