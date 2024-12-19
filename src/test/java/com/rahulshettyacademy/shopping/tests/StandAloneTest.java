package com.rahulshettyacademy.shopping.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("kushal@udemy.com");
		driver.findElement(By.id("userPassword")).sendKeys("Welcome@123");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> elements = driver.findElements(By.cssSelector(".mb-3"));
		Thread.sleep(2000);
		WebElement productItem = elements.stream().filter(product->product.findElement(By.cssSelector("h5 b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
		Thread.sleep(2000);
		System.out.println(productItem.getText());
		
		productItem.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		Thread.sleep(2000);
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean anyMatch = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(anyMatch);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Thread.sleep(2000);
		
		Actions actions= new Actions(driver);
		
		actions.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".btnn")).click();
		
		String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(actualText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		driver.quit();
		
	}

}
