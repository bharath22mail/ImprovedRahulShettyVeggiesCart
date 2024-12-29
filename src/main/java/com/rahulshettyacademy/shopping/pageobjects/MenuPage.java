package com.rahulshettyacademy.shopping.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.shopping.AbstractComponents.AbstractComponents;

public class MenuPage extends AbstractComponents {

	WebDriver driver;

	public MenuPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartIcon;
	
	@FindBy(css="button[routerlink*='myorder']")
	WebElement orderIcon;
	
	By cartButton=By.cssSelector("button[routerlink*='cart']");
	
	public CartPage clickCartIcon() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementToAppear(cartButton);	
		clickElement(cartIcon);
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage clickOrdersIcon() {
		clickElement(orderIcon);
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
