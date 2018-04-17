/* The class handles taking of screenshots on failure of a method */

package com.automation.demo.utility_code;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * The Class TakeScreenshot.
 */
public class TakeScreenshot {

	/* Configures the screenshot location and takes screenshots on failure */
	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
		fileName = fileName + "_" + timeStamp + ".png";
		String directory = Constants.Screenshot_Directory;
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		String destination = directory + fileName;
		return destination;
	}
}
