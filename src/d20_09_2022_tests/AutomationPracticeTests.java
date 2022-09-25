package d20_09_2022_tests;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import d20_09_2022_pages.BuyBoxPage;
import d20_09_2022_pages.HeaderPage;
import d20_09_2022_pages.LayerCartPage;
import d20_09_2022_pages.TopMenuPage;

public class AutomationPracticeTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "http://automationpractice.com/";
	private BuyBoxPage buyBoxPage;
	private HeaderPage headerPage;
	private LayerCartPage layerCartPage;
	private TopMenuPage topMenuPage;
	private SoftAssert softAssert;
	
	@BeforeClass 
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); 
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		buyBoxPage = new BuyBoxPage(driver, wait);
		headerPage = new HeaderPage(driver, wait);
		layerCartPage = new LayerCartPage(driver, wait);
		topMenuPage = new TopMenuPage(driver, wait);

	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}
	
	@Test (priority=10)
	public void addingProductToTheCart() {
		driver.get(baseUrl+"/index.php?id_product=1&controller=product");
		buyBoxPage.scrolForBuyBoxPage();
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("3");
		buyBoxPage.getSizeSelect().click();
		buyBoxPage.getSizeSelect().sendKeys("L");
		buyBoxPage.getColorName("Blue");
		buyBoxPage.getAddToCartButton().click();
		layerCartPage.waitForProductWindowToBeVisible();
		
		Assert.assertEquals(layerCartPage.getQuantityForProduct().getText(), "3", "The quantity is not maching");
		Assert.assertTrue(layerCartPage.getAtributesForProduct().getText().endsWith("L"), "The size is not changed.");
		Assert.assertTrue(layerCartPage.getAtributesForProduct().getText().startsWith("Blue"), "The color is not changed");
		String priceForOne = buyBoxPage.getPriceForOne().getText();
		int priceForOneProduct = Integer.parseInt(priceForOne);
		String totalPrice = layerCartPage.getTotalPrice().getText();
		int priceForTotal = Integer.parseInt(totalPrice);
		Assert.assertEquals(priceForTotal, priceForOneProduct*3, "The price is not maching.");
		layerCartPage.getContinueShoppingButton().click();
		layerCartPage.waitForProductWindowToBeInvisible();
		
		buyBoxPage.getQuantityInput().clear();
		buyBoxPage.getQuantityInput().sendKeys("1");
		buyBoxPage.getSizeSelect().click();
		buyBoxPage.getSizeSelect().sendKeys("S");
		buyBoxPage.getColorName("Orange");
		buyBoxPage.getAddToCartButton().click();
		layerCartPage.waitForProductWindowToBeVisible();
		
		layerCartPage.getProceedToCheckoutButton().click();
		
		Assert.assertEquals(driver.getTitle(), "Order - My Store", "Title is not maching.");
	}
	@Test (priority=20)
	public void topMenuMouseOver() {
		new Actions(driver).moveToElement(topMenuPage.getWomenLink()).perform();
		topMenuPage.waitForSubMeniWomen();
		softAssert.assertEquals(topMenuPage.getWomanDropdownVisibility().getAttribute("style"),"display: block;", "Error: Submenu should be visible");
		new Actions(driver).moveToElement(topMenuPage.getDressesLink()).perform();
		softAssert.assertEquals(topMenuPage.getDressesDropdownVisibility().getAttribute("style"),"display: block;", "Error: Submenu should be visible");
		new Actions(driver).moveToElement(topMenuPage.getTShirtsLink()).perform();
		softAssert.assertTrue(!topMenuPage.getWomanDropdownVisibility().isDisplayed(), "The submenu should be invisible");
		softAssert.assertTrue(!topMenuPage.getDressesDropdownVisibility().isDisplayed(), "The submenu should be invisible");
		softAssert.assertAll();
	}
	@Test (priority=30)
	public void phoneNumberVisibilityCheckOnResize() {
		driver.manage().window().maximize();
		softAssert.assertTrue(headerPage.getPhoneElement().isDisplayed(), "The element is not displayed");
		
		Dimension dimension = new Dimension(767, 700);
		driver.manage().window().setSize(dimension);
		softAssert.assertTrue(!headerPage.getPhoneElement().isDisplayed(), "The element is not displayed");
		
		dimension = new Dimension(768, 700);
		driver.manage().window().setSize(dimension);
		softAssert.assertTrue(headerPage.getPhoneElement().isDisplayed(), "The element is not displayed");
		
		driver.manage().window().maximize();
		softAssert.assertAll();
		
	}
	@Test (priority=40)
	public void headerLinksCheck() {
		headerPage.getContactUsLink().click();
		softAssert.assertTrue(driver.getTitle().equals("Contact us - My Store"), "You are not on good page.");
		headerPage.getSignInLink().click();
		softAssert.assertTrue(driver.getTitle().equals("Login - My Store"), "You are not on good page.");
		softAssert.assertAll();
	}
	@AfterMethod
	public void afterMethod() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
