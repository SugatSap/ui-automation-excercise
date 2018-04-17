/* Configurations for the Extent reports and path for the reports are configured  */

package com.automation.demo.utility_code;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;


/**
 * The Class ExtentManager.
 */
public class ExtentManager {

	
	/** The extent. */
	public static ExtentReports extent;
	
	/** The test. */
	public static ExtentTest test;
	
	/** The html reporter. */
	private static ExtentHtmlReporter htmlReporter;

	/**
	 * Gets the extent.
	 *
	 * @return the extent reports
	 */
	public static ExtentReports GetExtent() {
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	/**
	 * Gets the html reporter.
	 *
	 * @return the html reporter
	 */
	/*
	 * Extent report configurations like report name,title,encoding and excel
	 * file path are added here
	 */
	public static ExtentHtmlReporter getHtmlReporter() {

		/*
		 * String timeStamp = new
		 * SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance()
		 * .getTime());
		 */
		String fileName = "Report.html";
		String filePath = Constants.Extent_Report + fileName;
		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.setAppendExisting(false);
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("MIP Dashboard Automation Report");
		htmlReporter.config().setReportName("Regression Suite");
		return htmlReporter;
	}

	/**
	 * Creates the test.
	 *
	 * @param name the name
	 * @param description the description
	 * @return the extent test
	 */
	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name, description);
		return test;
	}
}
