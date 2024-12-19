package com.rahulshettyacademy.shopping.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.rahulshettyacademy.shopping.pageobjects.CartPage;
import com.rahulshettyacademy.shopping.pageobjects.MenuPage;
import com.rahulshettyacademy.shopping.pageobjects.ProductCatalog;
import com.rahulshettyacademy.shopping.testcomponents.BaseTest;
import com.rahulshettyacademy.shopping.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidations() {
		
		landingPage.loginApplication("kushal@udemy.com", "Welcome@1234");
		landingPage.getErrorMessage();
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); //--> Correct
		Assert.assertEquals("Incorrect email and password.", landingPage.getErrorMessage());  //--> InCorrect
	}
	@Test
	public  void productErrorValidation() throws InterruptedException {
		
		ProductCatalog productCatalog =landingPage.loginApplication("kushal@udemy.com", "Welcome@123");
		productCatalog.addProductToCart(productName);
		
		MenuPage menuPage = new MenuPage(driver);
		CartPage cartPage = menuPage.clickCartIcon();
			
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
