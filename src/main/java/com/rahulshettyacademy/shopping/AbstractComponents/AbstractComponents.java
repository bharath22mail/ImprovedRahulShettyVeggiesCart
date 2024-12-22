package com.rahulshettyacademy.shopping.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.rahulshettyacademy.shopping.resources.ExtentReporterNG;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		ExtentReporterNG.getTestCase().log(Status.INFO, "Waited until ByLocater is visible: "+driver.findElement(findBy).getText());
	}
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		ExtentReporterNG.getTestCase().log(Status.INFO, "Waited until WebElement is visible: "+findBy.getText());
	}
	public void waitForElementToDisapper(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		ExtentReporterNG.getTestCase().log(Status.INFO, "Waited until WebElement is invisible:");
	}
	public void enterTextBoxData(WebElement element, String inputData) {
		element.sendKeys(inputData);		
		ExtentReporterNG.getTestCase().log(Status.INFO, "Input is successful on WebElement: "+element.getText());
	}
	public void clickElement(WebElement element) {
		element.click();
		ExtentReporterNG.getTestCase().log(Status.INFO, "Click operation is successful on WebElement: "+element.getText());
	}
}
