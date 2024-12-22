package com.rahulshettyacademy.shopping.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	  static ExtentSparkReporter extentSparkReporter;
	  static ExtentReports extentReports;
      static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static void intiReport() {
		extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//index.html");
		extentSparkReporter.config().setReportName("Practice Automation Demo Results");
		extentSparkReporter.config().setDocumentTitle("Testing Results");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Automation Tester", "Bharath");
		
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
	}
	
}
