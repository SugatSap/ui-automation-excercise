package com.automation.demo.simple_demo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
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
import com.automation.demo.utility_code.TakeScreenshot;



public class GoogleSearchTest extends ExtentManager{

	private static String baseUrl = "http://google.com";
	private static String searchText = "Application of Whole Exome Sequencing to Identify Disease-Causing Variants in Inherited Human Diseases";
	private static WebElement googlePage = null;
	private static WebElement journallinkText = null;

	/**
	 * Loads google baseUrl.
	 */
	@BeforeTest
	public void pageSetup() {
		extent = ExtentManager.GetExtent(); //Extent report is initialized here.
		watch = new StopWatch();
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver_win32/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
		driver.get(baseUrl);
	}

	
	/**
	 * A google search for a provided searchText is performed
	 * and second link within 'https://pubmed.ncbi.nlm.nih.gov/' in the search results page is clicked. 
	 * The title of journal is validated to make sure the article is loaded.
	 */
	@Test()
	public void searchSpecificJournalOnGoogle() {
		test = extent.createTest("verifySearchWorks"); //This name is displayed in report.
		googlePage = driver.findElement(By.name("q"));
		googlePage.sendKeys(searchText);
		googlePage.submit();
		journallinkText = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[contains(@href,'https://pubmed.ncbi.nlm.nih.gov')]/h3")));
		journallinkText.click();
		String pageTitle = driver.getTitle();
		Assert.assertEquals("Application of whole exome sequencing to identify disease-causing variants in inherited human diseases - PubMed", pageTitle);
	}
	
	
	/**
	 * Validates co-author exists and name gets outputed
	 */
	@Test(dependsOnMethods = "searchSpecificJournalOnGoogle")
	public void verifyPresenceOfCoAuthorInSearchedArticle() {
		test = extent.createTest("verifyPresenceOfCoAuthorInSearchedArticle");
		List<WebElement> authors = driver.findElements(By.xpath("(//div[@class='authors'])[1]/div/span/a"));	
		List<String> numberOfAuthors =  new ArrayList<String>();
		for(WebElement author : authors) {
			numberOfAuthors.add(author.getText());
			test.info("<pre>" + "Authors: " + author.getText() + "\n"); //Displaying all authors name in extent report.
		}	
		Assert.assertTrue(numberOfAuthors.size() > 1); //validating co-author exist.
		String coauthor = numberOfAuthors.get(1);
		System.out.println("Co-Author: "+coauthor);	//outputing the co-authors name.	
	}
	
	/**
	 * Verifying four items are displayed under Menu drop down.
	 * Second item(Books) is clicked and Screenshot is captured.
	 */	
	@Test(dependsOnMethods = "verifyPresenceOfCoAuthorInSearchedArticle")
	public void verifyFourItemsInDropdownMenu() throws IOException {
		test = extent.createTest("verifyFourItemsInDropdownMenu");
		driver.navigate().back();                                 //navigating back to google search page from pubmed journal
		(new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[contains(@href,'https://pubmed.ncbi.nlm.nih.gov')]/h3"))); //waiting until we are taken to previous search results page
		WebElement moreDropdownMenu = driver.findElement(By.xpath("//div[contains(text(), 'More')]"));
		moreDropdownMenu.click();
		List<WebElement> dropDownMenuItems = driver.findElements(By.xpath("(//g-menu[@role='menu'])[4]/g-menu-item"));	
		Assert.assertEquals(dropDownMenuItems.size(), 4); //validating presence of 4 dropdown menu items 
		dropDownMenuItems.get(1).click();
		TakeScreenshot.takeScreenshot(driver,"BooksPage"); //Taking screen capture. BooksPage is the prefix to the name of image file that gets created.
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
