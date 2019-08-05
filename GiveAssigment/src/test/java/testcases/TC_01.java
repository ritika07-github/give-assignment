package testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import javax.imageio.ImageIO;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.SetUpSuite;


public class TC_01 extends SetUpSuite {
	
	//@Test
	public void verifyExternalLinks() throws InterruptedException{
		
		List<WebElement> ext_links = driver.findElements(By.xpath("//span[@id='External_links']//parent::h2//following-sibling::ul//li//a[@href]"));
		for(WebElement link : ext_links){
			Actions act = new Actions(driver);
			act.moveToElement(link)
			   .keyDown(Keys.SHIFT)
			   .click(link)
			   .perform();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		}
		Assert.assertEquals(driver.getWindowHandles().size(), 8, "All external links verified.");	
	}
	
	//@Test
	public void verifyFeaturedArticleOxygen(){
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.findElement(By.xpath("//div[@aria-labelledby='Periodic_table_(Large_cells)']//a[@title='Oxygen']")).click();;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='mw-indicator-featured-star']")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='mw-indicator-featured-star']//a[@href]")).isDisplayed(), true); 
	}
	
	//@Test
	public void takeScreenShotOfElementProperties() throws IOException{
		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		WebElement info_box = driver.findElement(By.xpath("//table[@class='infobox']"));
		BufferedImage full_img = ImageIO.read(screenshot);
		Point point = info_box.getLocation();
		int box_width = info_box.getSize().getWidth();
		int box_height = info_box.getSize().getHeight();
		BufferedImage box_img= full_img.getSubimage(point.getX(), point.getY(), box_width, box_height);
		ImageIO.write(box_img, "png", screenshot);
		File screenshot_loc = new File("C:\\Users\\ritika.agrawal\\Desktop\\GiveAssigment\\test-output\\info_box.png");
		FileUtils.copyFile(screenshot, screenshot_loc);
	}
	
	//@Test
	public void verifyPlutoniumSuggestion(){
		
		driver.findElement(By.xpath("//input[@id='searchInput']")).sendKeys("Pluto");
		List<WebElement> result = driver.findElements(By.xpath("//div[@class='suggestions-results']//a"));
		Assert.assertEquals(result.get(1).getAttribute("title"), "Plutonium");
	}
	
	@Test
	public void countOfPdfLinksInReferences(){
		
		String pdf_links_xpath = "//ol[@class='references']//a[contains(@href,'.pdf')]";
		List<WebElement> pdf_links = driver.findElements(By.xpath(pdf_links_xpath));
		System.out.println("Number of pdf links on page are "+pdf_links.size());
	}
	
	
}
