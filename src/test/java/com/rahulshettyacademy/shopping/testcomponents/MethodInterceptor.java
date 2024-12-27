package com.rahulshettyacademy.shopping.testcomponents;

import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class MethodInterceptor implements IMethodInterceptor {
 List<IMethodInstance> result = new ArrayList<>();
 //Retry retry=new Retry();
	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		// TODO Auto-generated method stub
		
		int methodsCount=methods.size();
		for (int i=0; i< methodsCount;i++) {
			
			System.out.println("MethodName ->:"+methods.get(i).getMethod().getMethodName().toString());
			if(methods.get(i).getMethod().getMethodName().toString().equalsIgnoreCase("methodInterceptorTest")) {
				
				// The below statement describes the method "methodInterceptorTest will execute 3 times"
				methods.get(i).getMethod().setInvocationCount(3);
				result.add(methods.get(i));
				System.out.println("The context->"+context.getName());
			}
			if(methods.get(i).getMethod().getMethodName().toString().equalsIgnoreCase("methodInterceptorRetryTest")) {
				
				// The below statement describes if the method fails "methodInterceptorRetryTest will execute * times as mentioned in Retry.java"
				methods.get(i).getMethod().setRetryAnalyzerClass(Retry.class);
				result.add(methods.get(i));
				System.out.println("The context::"+context.getName());
			}
		}
		//methodName;
		return result;
	}
	

}
