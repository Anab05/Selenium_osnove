package d15_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zadatak3 {

	public static void main(String[] args) throws InterruptedException {
		// 3.Zadatak (Za vezbanje)
//		Napisati program koji 
//		Ucitava https://seeds.sproutsocial.com/components/loader-button/
//		Odskrola do Loader buttons are used to display a loading indicator inside of a button. paragrafa. Koristan link
//		Klikce ne dugme 
//		Ceka da dugme zavrsi sa loadingom 
//		Ispisati poruku na ekranu
//		Zatvoriti pretrazivac
//		HINT: Koristite data-qa-button-isloading  atribut elementa za cekanje odredjenog stanja tog elementa
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://seeds.sproutsocial.com/components/loader-button/");
		
		WebElement loaderButton = driver.findElement(By.xpath("//p[contains(@class, 'Text-sc-cws339-0-p')]"));
		new Actions (driver).scrollToElement(loaderButton).perform();
		
		driver.findElement(By.xpath("//button[@data-qa-button-isloading = 'false']")).click();
		System.out.println("Data is loading");
		driver.findElement(By.xpath("//button[@data-qa-button-isloading = 'true']"));
		System.out.println("In progress...");
		driver.findElement(By.xpath("//button[@data-qa-button-isloading = 'false']"));
		System.out.println("Loading is finished.");
		
		Thread.sleep(2000);
		driver.quit();

	}

}
