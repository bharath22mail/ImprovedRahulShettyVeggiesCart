package com.rahulshettyacademy.shopping.testcomponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.rahulshettyacademy.shopping.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener,ISuiteListener{


	@Override
	public void onTestStart(ITestResult result) {
		ExtentReporterNG.createTestCaseReport(result.getMethod().getMethodName());
		ExtentReporterNG.getTestCase().log(Status.INFO, "Test case :"+result.getMethod().getMethodName()+" execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReporterNG.getTestCase().log(Status.PASS, "Test is Passed:");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		ExtentReporterNG.getTestCase().pass("Data entered in TextBox:\t"+result.getTestName()+"\t"+ExtentReporterNG.getTestCase().fail(result.getThrowable()), MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReporterNG.getScreeshot(driver)).build());

		
//		ExtentReporterNG.getTestCase().fail(result.getThrowable());
//		try {
//			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//String filePath=getScreeshot(result.getMethod().getMethodName(),driver);
		//ExtentReporterNG.getTestCase().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext  context) {
		
		//ExtentReporterNG.intiReport();
		//System.out.println("ExtentReporterNG.intiReport()::"+context.getName());
	}
	@Override
	public void onStart(ISuite suite) {
		
		ExtentReporterNG.intiReport();
		System.out.println("Suite Start::"+suite.getName());
	}
	@Override
	public void onFinish(ITestContext context) {
		ExtentReporterNG.finishReport();
	}
	@Override
	public void onFinish(ISuite suite) {
		
		ExtentReporterNG.finishReport();
		System.out.println("Suite finish::"+suite.getName());
	}
}
