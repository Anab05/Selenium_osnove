package d15_09_2022;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		//Napisati program koji:
//		Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//		Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao 
//		sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//		POMOC: Brisite elemente odozdo.
//		(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo
 
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get("https://s.bootsnipp.com/iframe/Dq2X");
		List<WebElement> iks = driver.findElements(By.className("close")); //ukupan broj x
		
		boolean elementPostoji = true;
		for (int i = iks.size(); i > 0 ; i--) {
			iks.get(i-1).click();

			try {
				driver.findElement(By.className("close"));
			} catch (NoSuchElementException error) {
				elementPostoji=false;
			}
		
			if (elementPostoji) {
				System.out.println("Element je obrisan.");
			} else {
				System.out.println("Element nije obrisan.");
			}
		}
		
		Thread.sleep(4000);
		driver.quit();
	}

}
