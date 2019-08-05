package selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class SetUpSuite {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser(){
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ritika.agrawal\\Downloads\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/wiki/Selenium");
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void closebrowser(){
		
		driver.quit();
	}
}
