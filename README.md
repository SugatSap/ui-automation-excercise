### Demo UI Test Project for MarkForged.
In this project we are running test against chrome browser. We are verifying below functionalities:
1) Search “Application of Whole Exome Sequencing to Identify Disease-Causing Variants in Inherited Human Diseases” on google and load the journal within https://pubmed.ncbi.nlm.nih.gov/ which has an author “Gerald Goh”. Verify if co-author exist and display the co-author's name.
2) Verify there are four menu items under google search result Menu dropdown. Select the 2nd item within the More drop-down list on the page and Output the Screen Capture . 



### Test Strategy and Framework:
This project is maven based java project. We are using TestNG as testing framework. Project has two packages:
1) simple_demo: This has test that runs against chrome browser.
2) utility_code: This has helper classes/methods like Extent Report class and Listener for Reporting purpose and TakeScreenshot Class for taking screenshots.


### How to run the test:
Download the project in your system. It is mavenized test project.Please run following command to run the tets.
 - mvn clean install (Build the test)
 - mvn -Dmaven.test.failure.ignore=true test -PGoogle_ChromeDriver surefire-report:report (runs the test)
 - Right click on the test name and execute Run As --> TestNG Test.(runs the test)


### Reporting:
I have used Extent Report Tool to ge nerate User friendly report. Report gets created and stored as "Report.html" in the current project diretory after test is complete. Associated screenshots will be in the current directly also.


