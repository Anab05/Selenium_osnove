package d19_09_2022;

import java.time.Duration;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BootstrapTableTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "https://s.bootsnipp.com";
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}
	
	@Test (priority=10)
	public void editRow () {
		driver.get(baseUrl+"/iframe/K5yrx");
		Assert.assertEquals(driver.getTitle(),"Table with Edit and Update Data - Bootsnipp.com","Not on edit page.");
		
		driver.findElement(By.xpath("//tr[@id='d1']//button")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("up")));
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("fn")).sendKeys("Ana");
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("ln")).sendKeys("Brankovic");
		driver.findElement(By.id("mn")).clear();
		driver.findElement(By.id("mn")).sendKeys("Branko");
		driver.findElement(By.id("up")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("up")));
		
		Assert.assertEquals(driver.findElement(By.id("f1")).getText(), "Ana", "The first name cell is blank.");
		Assert.assertEquals(driver.findElement(By.id("l1")).getText(), "Brankovic", "The last name cell is blank.");
		Assert.assertEquals(driver.findElement(By.id("m1")).getText(), "Branko", "The middle name cell is blank.");

	}
	@Test (priority=20)
	public void deleteRow() {
		driver.get(baseUrl+"/iframe/K5yrx");
		
		Assert.assertEquals(driver.getTitle(),"Table with Edit and Update Data - Bootsnipp.com","Not on edit page.");
		
		int numberOfRows = driver.findElement(By.className("table"))
				.findElement(By.tagName("tbody"))
				.findElements(By.tagName("tr")).size();
		
		driver.findElement(By.xpath("//tr[@id='d1']//button[contains(@class, 'delete')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("del")));
		driver.findElement(By.id("del")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("del")));
		
		
		Assert.assertEquals(numberOfRows, 
				driver.findElement(By.className("table"))
				.findElement(By.tagName("tbody"))
				.findElements(By.tagName("tr")).size(),
						"The number of rows is not changed");

	}
	@Test (priority=30)
	public void takeAScreenshot() throws IOException {
		driver.get(baseUrl+"/iframe/K5yrx");
		Assert.assertEquals(driver.getTitle(),"Table with Edit and Update Data - Bootsnipp.com","Not on edit page.");
		
		TakesScreenshot srcShot = ((TakesScreenshot)driver);
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("‪C:\\Users\\Dusan\\Desktop\\selenium_osnove\\src\\d19_09_2022");
		FileHandler.copy(srcFile, destFile);

	}
	
	@AfterMethod
	public void afterMethod() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
