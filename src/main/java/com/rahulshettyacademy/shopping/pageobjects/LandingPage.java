package com.rahulshettyacademy.shopping.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.shopping.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement
	// userEmail=driver.findElement(By.id("userEmail"));//.sendKeys("kushal@udemy.com");
	// WebElement
	// userPassword=driver.findElement(By.id("userPassword"));//.sendKeys("Welcome@123");
	// WebElement login=driver.findElement(By.id("login"));//.click();

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;

	public ProductCatalog loginApplication(String name, String password) {
		enterTextBoxData(userEmail, name);
		enterTextBoxData(userPassword, password);
		clickElement(submit);
		//userEmail.sendKeys(name);
		//userPassword.sendKeys(password);
		//submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	public void gotoUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
