package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BuyBoxPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public BuyBoxPage(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	public WebElement getQuantityInput() {
		return driver.findElement(By.id("quantity_wanted"));
	}
	
	public WebElement getSizeSelect() {
		return driver.findElement(By.id("group_1"));
	}
	public WebElement getAddToCartButton () {
		return driver.findElement(By.name("Submit"));
	}
	public WebElement getWishlistButton() {
		return driver.findElement(By.id("wishlist_button"));
	}
	public WebElement getColorName(String color) {
		return driver.findElement(By.name(color));
	}
	public WebElement getPriceForOne() {
		return driver.findElement(By.id("our_price_display"));
	}
	public void scrolForBuyBoxPage () {
		new Actions(driver).scrollToElement(driver.findElement(By.id("wishlist_button"))).perform();
	}
}
