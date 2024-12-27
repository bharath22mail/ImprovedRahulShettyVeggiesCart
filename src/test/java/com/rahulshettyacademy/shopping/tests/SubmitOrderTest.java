package com.rahulshettyacademy.shopping.tests;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rahulshettyacademy.shopping.pageobjects.CartPage;
import com.rahulshettyacademy.shopping.pageobjects.CheckoutPage;
import com.rahulshettyacademy.shopping.pageobjects.ConfirmationPage;
import com.rahulshettyacademy.shopping.pageobjects.LandingPage;
import com.rahulshettyacademy.shopping.pageobjects.MenuPage;
import com.rahulshettyacademy.shopping.pageobjects.OrderPage;
import com.rahulshettyacademy.shopping.pageobjects.ProductCatalog;
import com.rahulshettyacademy.shopping.testcomponents.BaseTest;
import com.rahulshettyacademy.shopping.testcomponents.Retry;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";


	@Test(dataProvider = "UserCartConfirmationTest", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws InterruptedException {
		
		landingPage = new LandingPage(driver);
		landingPage.gotoUrl();

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));

		productCatalog.addProductToCart(input.get("product"));

		MenuPage menuPage = new MenuPage(driver);

		CartPage cartPage = menuPage.clickCartIcon();

		boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrderTest" }, groups={"History"} )
	public void orderHistoryTest() {
		
		landingPage = new LandingPage(driver);
		landingPage.gotoUrl();
		
		landingPage.loginApplication("kushal@udemy.com", "Welcome@123");
		MenuPage menuPage = new MenuPage(driver);
		OrderPage orderPage = menuPage.clickOrdersIcon();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

//	@DataProvider
//	public String[][] getData() {
//		return new String[][] { { "kushal@udemy.com", "Welcome@123", "ZARA COAT 3" },
//				{ "bharath22@gmail.com", "Welcome@123", "IPHONE 13 PRO" } };
//	}

	/*
	 * @DataProvider public Object[][] getData(){
	 * 
	 * List<HashMap<String, String>> data =
	 * getJsonDataMap(System.getProperty("user.dir") +
	 * "//src//test//java//com//rahulshettyacademy//shopping//data//PurchaseOrder.json"
	 * );
	 * 
	 * 
	 * 
	 * return new Object[][] {{data.get(0)},{data.get(1)}};
	 * 
	 * }
	 */
	
	@DataProvider(name="UserCartConfirmationTest")//,parallel = true)
	public Object[][] getData(){
		
		List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir")
				+ "//src//test//java//com//rahulshettyacademy//shopping//data//PurchaseOrder.json");

		Object[][] dataObject = new Object[data.size()][1];

	
		for(int i=0;i<data.size();i++) {
			System.out.println("it came here::"+i);
			dataObject[i][0]=data.get(i);
			
		}
		System.out.println("I am done");
		return dataObject;
		
		
 }
	@Test//(retryAnalyzer = Retry.class)
	public void methodInterceptorTest() {
		System.out.println("Executing methodInterceptorTest");
	}
	@Test//(retryAnalyzer = Retry.class)
	public void methodInterceptorRetryTest() {
		System.out.println("Executing methodInterceptorRetryTest");
		Assert.assertTrue(false);
		
	}
}
