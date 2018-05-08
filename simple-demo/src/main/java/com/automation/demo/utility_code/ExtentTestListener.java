package com.automation.demo.utility_code;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.demo.simple_demo.GoogleSearchTest;
import com.aventstack.extentreports.Status;


public class ExtentTestListener extends ExtentManager implements ITestListener {
	

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Before starting all tests, below method runs.
	public void onStart(ITestContext iTestContext) {
		System.out.println("Test " + iTestContext.getName() + " started");
		iTestContext.setAttribute("WebDriver", driver);
	}

	// After ending all tests, below method runs.
	public void onFinish(ITestContext iTestContext) {
		System.out.println("Test " + iTestContext.getName() + " finished");

	}

	/*
	 * Watch is setup to get the time taken for each method and this time is
	 * written to the report
	 */
	public void onTestStart(ITestResult iTestResult) {
		watch.start();
		System.out.println("Method " + getTestMethodName(iTestResult) + " started");
		// Start operation for extent reports.

	}

	public void onTestSuccess(ITestResult testResult) {
		double seconds = (double) watch.getTime() / 1000.0;
		System.out.println("Method " + getTestMethodName(testResult) + " succeeded");
		test.log(Status.PASS,
				"<b>" + testResult.getMethod().getMethodName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2").toUpperCase()
						+ "</b>" + " completed in " + seconds + " seconds");
		watch.reset();
	}

	/*
	 * Screenshots are captured at the end of each method on failure of the
	 * testcase and writing it to the Extent report
	 */
	public void onTestFailure(ITestResult testResult) {
		System.out.println("Method " + getTestMethodName(testResult) + " failed");
		watch.reset();
		try {
			String path = TakeScreenshot.takeScreenshot(driver, testResult.getMethod().getMethodName());
			test.addScreenCaptureFromPath(path).fail("<b>"
					+ testResult.getMethod().getMethodName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2").toUpperCase()
					+ "</b>" + " failed<br>" +testResult.getThrowable());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult testResult) {
		System.out.println("Method " + getTestMethodName(testResult) + " skipped");
		watch.reset();
		test.skip("<b>" + testResult.getMethod().getMethodName().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2").toUpperCase()
				+ "</b>" + " skipped<br>" +testResult.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(testResult));
	}

}
