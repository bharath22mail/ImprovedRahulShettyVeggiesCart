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

import com.rahulshettyacademy.shopping.pageobjects.CartPage;
import com.rahulshettyacademy.shopping.pageobjects.CheckoutPage;
import com.rahulshettyacademy.shopping.pageobjects.ConfirmationPage;
import com.rahulshettyacademy.shopping.pageobjects.LandingPage;
import com.rahulshettyacademy.shopping.pageobjects.MenuPage;
import com.rahulshettyacademy.shopping.pageobjects.ProductCatalog;

public class SubmitOrderTestCopy {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.gotoUrl();
		ProductCatalog productCatalog =landingPage.loginApplication("kushal@udemy.com", "Welcome@123");

		productCatalog.addProductToCart(productName);

		MenuPage menuPage = new MenuPage(driver);

		CartPage cartPage = menuPage.clickCartIcon();
	
		
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		//driver.quit();

	}

}
