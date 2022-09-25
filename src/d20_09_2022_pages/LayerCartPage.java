package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LayerCartPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public LayerCartPage(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	public WebElement getContinueShoppingButton () {
		return driver.findElement(By.xpath("//*[text()='Continue shopping']"));
	}
	
	public WebElement getProceedToCheckoutButton () {
		return driver.findElement(By.xpath("//*[text()='Proceed to checkout']"));
	}
	public WebElement getAtributesForProduct () {
		return driver.findElement(By.id("layer_cart_product_attributes"));
	}
	public WebElement getQuantityForProduct () {
		return driver.findElement(By.id("layer_cart_product_quantity"));
	}
	public WebElement getTotalPrice () {
		return driver.findElement(By.id("layer_cart_product_price"));
	}
	public void waitForProductWindowToBeVisible () {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
	}
	public void waitForProductWindowToBeInvisible () {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("layer_cart")));
	}
	
}
