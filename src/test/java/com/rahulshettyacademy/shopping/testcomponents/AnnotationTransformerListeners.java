package com.rahulshettyacademy.shopping.testcomponents;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.IListenersAnnotation;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformerListeners implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub

		if (testMethod.getName().toString().equalsIgnoreCase("submitOrderTest")) {

			// The below statement describes the method "methodInterceptorTest will execute
			// 3 times"
			annotation.setEnabled(false);
		}
		if (testMethod.getName().toString().equalsIgnoreCase("orderHistoryTest")) {

			// The below statement describes the method "methodInterceptorTest will execute
			// 3 times"
			annotation.setEnabled(false);
		}
		if (testMethod.getName().toString().equalsIgnoreCase("methodInterceptorTest")) {

			// The below statement describes the method "methodInterceptorTest will execute
			// 3 times"
			annotation.setEnabled(false);
		} 
		if (testMethod.getName().toString().equalsIgnoreCase("methodInterceptorRetryTest")) {

			// The below statement describes the method "methodInterceptorTest will execute
			// 3 times"
			annotation.setEnabled(true);
			annotation.setInvocationCount(1);
			annotation.setRetryAnalyzer(Retry.class);
		}
		if (testMethod.getName().toString().equalsIgnoreCase("shouldAnswerWithTrue")) {

			// The below statement describes the method "methodInterceptorTest will execute
			// 3 times"
			annotation.setEnabled(true);
			annotation.setInvocationCount(1);
			annotation.setRetryAnalyzer(Retry.class);
		}

	}

	@Override
	public void transform(IConfigurationAnnotation annotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		// TODO Auto-generated method stub
		IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
	}

	@Override
	public void transform(IDataProviderAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
		IAnnotationTransformer.super.transform(annotation, method);
	}

	@Override
	public void transform(IFactoryAnnotation annotation, Method method) {
		// TODO Auto-generated method stub
		IAnnotationTransformer.super.transform(annotation, method);
	}

	@Override
	public void transform(IListenersAnnotation annotation, Class<?> testClass) {
		// TODO Auto-generated method stub
		IAnnotationTransformer.super.transform(annotation, testClass);
	}

}
