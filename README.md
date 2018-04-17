Demo UI Test Project for MarkForged.
In this project we are running test against headless chrome browser. We are doing Google search and verifying below functionalities:
1) Load Google home page and verify page title shows correct title.
2) Do search, click on the first link that appears on the search results page and verify corect page title apears for the newly opened page.



Test Strategy and Framework:
This project is maven based java project. We are using TestNG as testing framework. Project has two packages:
1) simple_demo: This has test that runs against headless chrome browser.
2) utility_code: This has helper classes/methods like Extent Report class and Listener for Reporting purpose and TakeScreenshot Class for taking screenshots whenever failure occures.


How to run the test:
Download the project in your system. It is mavenized test project.Please run following command to run the tets.
 - mvn clean install (Build the test)
 - mvn -Dmaven.test.failure.ignore=true test -PGoogle_ChromeDriver surefire-report:report (runs the test)



Reporting:
I have used Extent Report Tool to ge nerate User friendly report. Report gets created and stored as "Report.html" in the current project diretory after test is complete. Associated failures screenshots will be in the current directly also. We will see those screenshots attached to report while viewing the report.


