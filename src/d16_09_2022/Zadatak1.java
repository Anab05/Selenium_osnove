package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
		// Napisati program koji ima:
//		Podesava:
//			implicitno cekanje za trazenje elemenata od 10s
//			implicitno cekanje za ucitavanje stranice od 10s
//			eksplicitno cekanje podeseno na 10s
//			Podaci:
//			Potrebno je u projektu ukljuciti 4 slike.
//			Imenovanje slika neka bude po pravilu pozicija_ime_prezime_polaznika.ekstenzija
//			Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg …
//			Koraci:
//			Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//			Maksimizuje prozor
//			Selektuje Image - Front klikom na tu karticu u dnu ekrana
//			Klik na add photo iz levog navigacionog menia
//			Upload slike. Upload preko File objekta koristeci getAbsolutePath
//			Sacekati da broj prikazanih slika u donjem uglu navigacije bude za 1 veca
//			Kliknuti na poslednje dodatu sliku kako bi bila izabrana za postavljanje
//			Ceka da dijalog bude vidljiv
//			Klik na Use One Side Only dugme
//			Ceka da se pojavi dijalog sa slikom
//			Klik na Done
//			Ponoviti proces za Left, Right i Back
//			Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//			Kliknuti na Add To Cart dugme
//			Verifikovati postojanje greske Oops! It looks like you`ve not added an text to this field, please add one before continuing.
//			Xpath: //*[@action='error']
//			Zatvorite pretrazivac

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		File frontPicture = new File("C:\\Users\\Dusan\\Desktop\\Slike projekat\\Đoša.jpeg");
		File leftPicture = new File("C:\\Users\\Dusan\\Desktop\\Slike projekat\\tika-spic.png");
		File rigthPicture = new File("C:\\Users\\Dusan\\Desktop\\Slike projekat\\Zitko.jpg");
		File backPicture = new File("C:\\Users\\Dusan\\Desktop\\Slike projekat\\Deda Tripko.jpg");
		
		driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
		driver.manage().window().maximize();
		
		// ukupno imamo 4 slike
		
		for (int i = 1; i <=4 ; i++) {
			if (i==1) {
				frontPicture=frontPicture;
			}else if (i==2) {
				frontPicture=leftPicture;
			} else if (i==3) {
				frontPicture=rigthPicture;
			} else if (i==4) {
				frontPicture=backPicture;
			}
		
		
		driver.findElement(By.xpath("//img[@alt='image " +i+ "']")).click();
		driver.findElement(By.xpath("//img[@alt='+ Add photo']")).click();
		driver.findElement(By.id("imageUpload")).sendKeys(frontPicture.getAbsolutePath());
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//img[@loading='lazy']"), i));
		driver.findElement(By.xpath("//img[@loading='lazy']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Use One Side Only']")));
		driver.findElement(By.xpath("//*[text()='Use One Side Only']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Done']")));
		driver.findElement(By.xpath("//*[text()='Done']")).click();
		
		}
		
		Random r = new Random();
		int konfete = r.nextInt(2);
		
		driver.findElement(By.xpath("//div[@name='"+ konfete + "']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@action='error']")));
		System.out.println("Oops! It looks like you`ve not added an text to this field, please add one before continuing.");
		
		Thread.sleep(3000);
		driver.quit();
	}

}
