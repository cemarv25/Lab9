package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestHtml2ApplicationTests {
	private static WebDriver driver;
	
	@BeforeEach
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Users\\Cesar\\Desktop (HDD)\\Tec de Mty\\6 Semestre\\Pruebas\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php");
		String title = driver.getTitle();
		
		// Then
		assertEquals("My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=myaccount");
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("cesaringo24.martinez@gmail.com");
		
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("Hello123");
		
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		String title = driver.getTitle();
		
		// Then
		assertEquals("My account - My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=myaccount");
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("cesaringo24.martinez@gmail.com");
		
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("wrongpassword");
		
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		String title = driver.getTitle();
		
		// Then
		assertEquals("Login - My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=myaccount");
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("cesaringo24.martinez@gmail.com");
		
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("wrongpassword");
		
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		String errorMessage = alert.getText();
		
		// Then
		assertEquals("Authentication failed.", errorMessage);
		
		
	}
	
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php");
		
		WebElement searchField = driver.findElement(By.id("search_query_top"));
		searchField.sendKeys("not existing product");
		
		WebElement searchButton = driver.findElement(By.name("submit_search"));
		searchButton.click();
		
		WebElement alert = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
		String alertMessage = alert.getText();
		
		// Then
		assertEquals("No results were found for your search \"not existing product\"", alertMessage);
	}
	
	@Test
	public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php");
		
		WebElement searchButton = driver.findElement(By.name("submit_search"));
		searchButton.click();
		
		WebElement alert = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
		String alertMessage = alert.getText();
		
		// Then
		assertEquals("Please enter a search keyword", alertMessage);
	}
	
	@Test
	public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed() throws Exception {
		// When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=myaccount");
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("cesaringo24.martinez@gmail.com");
		
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("Hello123");
		
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='logout']"));
		logoutButton.click();
		
		String title = driver.getTitle();
		
		// Then
		assertEquals("Login - My Store", title);
	}
}
