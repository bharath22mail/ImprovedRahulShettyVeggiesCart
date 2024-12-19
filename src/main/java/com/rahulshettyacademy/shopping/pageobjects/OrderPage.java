package com.rahulshettyacademy.shopping.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.shopping.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="table tr td:nth-child(3)")
	List<WebElement> productNames; 
	
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = productNames.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		return match;
		
	}
}