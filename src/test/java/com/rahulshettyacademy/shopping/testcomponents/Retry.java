package com.rahulshettyacademy.shopping.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count=0;
	int noOfIterations=3;
	@Override
	public boolean retry(ITestResult result) {
		if(count<noOfIterations) {
			count++;
			return true;
		}
		return false;
	}

}
