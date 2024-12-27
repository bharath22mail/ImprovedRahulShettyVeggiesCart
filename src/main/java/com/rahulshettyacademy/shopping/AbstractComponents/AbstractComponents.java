package com.rahulshettyacademy.shopping.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.rahulshettyacademy.shopping.resources.ExtentReporterNG;

public class AbstractComponents extends Generic{

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		ExtentReporterNG.getTestCase().pass("Waiting for locator to appear:\t"+driver.findElement(findBy).getAccessibleName(), MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
	}
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		ExtentReporterNG.getTestCase().pass("Waiting for element to appear:\t"+element.getAccessibleName() , MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToDisapper(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		ExtentReporterNG.getTestCase().pass("Waiting for element to disapper:\t"+element.getAccessibleName(), MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void enterTextBoxData(WebElement element, String inputData) {
		element.sendKeys(inputData);		
		ExtentReporterNG.getTestCase().pass("Data entered in TextBox:\t"+element.getAccessibleName()+"->\t"+inputData, MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
	}
	public void clickElement(WebElement element) {
		ExtentReporterNG.getTestCase().pass("Performed click operation on:\t"+element.getAccessibleName() , MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
		element.click();
	}
	public void gotoUrl() {
		driver.get("https://rahulshettyacademy.com/client");
		ExtentReporterNG.getTestCase().pass("Web URL launched:\t"+driver.getCurrentUrl(), MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
	}
	public String getText(WebElement element) {
		ExtentReporterNG.getTestCase().pass("Text of the Webment:\t"+element.getText(), MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());
		return element.getText();
	}
}
