package com.automation.demo.simple_demo;


import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.demo.utility_code.ExtentManager;



public class GoogleSearchTest extends ExtentManager{

	public static StopWatch watch;
	public static WebDriver driver;
	private static String baseUrl = "http://google.com";
	private static String expectedTitle = "Google";
	private static WebElement element = null;
	private static WebElement linkText = null;

	/**
	 * Set up headless chrome driver and load the provided baseUrl.
	 * Used ChromeOptions to convert chromedriver to headless mode.
	 */
	@BeforeTest
	public void pageSetup() {
		extent = ExtentManager.GetExtent();
		watch = new StopWatch();
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver_win32/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");            //running chrome in headless mode
        options.addArguments("window-size=1200x600");
        driver = new ChromeDriver(options);
		driver.get(baseUrl);
	}

	
	/**
	 * Verify Google Page title once google loads
	 */
	@Test()
	public void verifyGooglePageTitle() {
		test = extent.createTest("verifyGooglePageTitle");
		String actualTitle = driver.getTitle();
		Assert.assertTrue(actualTitle.contentEquals(expectedTitle));
	}

	
	/**
	 * A google search for word "selenium" is performed
	 * and first link from the search results page is clicked 
	 * and verified it's correct page title
	 */
	@Test(dependsOnMethods = "verifyGooglePageTitle")
	public void verifySearchWorks() {
		test = extent.createTest("verifySearchWorks");
		element = driver.findElement(By.name("q"));
		element.sendKeys("selenium");
		element.submit();
		linkText = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[contains(text(),'Selenium - Web Browser Automation')]")));
		linkText.click();
		String pageTitle = driver.getTitle();
		Assert.assertEquals("Selenium - Web Browser Automation", pageTitle);
	}
    
	
	/**
	 * Tear down method.Closes selenium webdriver session.
	 * Extent report writes report at the end. 
	 */
	@AfterTest
	public void tearDownClass() throws Exception {
		driver.close();
		extent.flush();
	}

}
