package com.rahulshettyacademy.shopping.resources;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static void intiReport() {
		File htmlReport = new File(System.getProperty("user.dir") + "//reports//index.html");
		if(htmlReport.exists()) {
			File folder = new File(System.getProperty("user.dir") + "//reports");
			if(folder.exists() && folder.isDirectory()) {
				folder.delete();
				System.out.println("Reports folder deleted::");
			}
	        //htmlReport.delete(); // Attempt to delete the file
	    }
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//index.html");
		try {
			extentSparkReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"//Resources//spark-config.xml"));	
			extentSparkReporter.config().setReportName("Practice Automation Demo Results");
			extentSparkReporter.config().setDocumentTitle("Testing Results");
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);
			extentReports.setSystemInfo("Automation Tester", "Bharath");
			System.out.println("File created::");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public static void createTestCaseReport(String testCaseName) {
		ExtentTest extentTest = extentReports.createTest(testCaseName);
		test.set(extentTest);
		//return extentTest;
	}
	public static ExtentTest getTestCase() {
		ExtentTest extentTest = test.get();
		return extentTest;
	}
	public static void finishReport() {
		if(extentReports!=null) {
			extentReports.flush();
		}
		extentReports.flush();
	}
	public static String getScreeshot(WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		return ts.getScreenshotAs(OutputType.BASE64);
		
	}
}
