package d20_09_2022_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenuPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public TopMenuPage(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	public WebElement getWomenLink () {
		return driver.findElement(By.xpath("//ul[contains(@class, 'sf-menu')]/li[1]"));
	}
	public WebElement getDressesLink () {
		return driver.findElement(By.xpath("//ul[contains(@class, 'sf-menu')]/li[2]"));
	}
	public WebElement getTShirtsLink () {
		return driver.findElement(By.xpath("//ul[contains(@class, 'sf-menu')]/li[3]"));
	}
	public WebElement getWomanDropdownVisibility() {
		return driver.findElement(By.xpath("//*[contains(@class, 'menu-content')]/li[1]/ul"));
	}
	public void waitForSubMeniWomen () {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Women']//ul[contains(@class, 'submenu-container')]")));
	}
	public WebElement getDressesDropdownVisibility() {
		return driver.findElement(By.xpath("//*[contains(@class, 'menu-content')]/li[2]/ul"));
	}
	public void waitForSubMeniDresses () {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Dresses']//ul[contains(@class, 'submenu-container')]")));
	}
}
