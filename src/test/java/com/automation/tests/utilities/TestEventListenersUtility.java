package com.automation.tests.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestEventListenersUtility implements ITestListener {
	private static ExtentReportsUtility extentUtilityObject;
	@Override
	public void onTestStart(ITestResult result) { // before every @Test method called this method is executed
		extentUtilityObject.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentUtilityObject.logTestpassed(result.getMethod().getMethodName()+" is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentUtilityObject.logTestFailed(result.getMethod().getMethodName()+" is failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		extentUtilityObject=ExtentReportsUtility.getInstance();
		extentUtilityObject.startExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentUtilityObject.endReport();
	}

	

}
