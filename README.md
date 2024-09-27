# Selenium Web Testing Project

## Contents 

- [Overview](#overview)
- [Setup Instructions](#setup-instructions)
- [Framework Architecture](#framework-architecture)
- [Features, Scenarios, and Defects](#features-scenarios-and-defects)
- [Test Metrics](#test-metrics)

## Overview

This is a web testing project for testing the functionality of the [Sauce Demo](https://www.saucedemo.com/) made-for-testing e-commerce site, created by Owen Graham. 
The project uses Cucumber for BDD and Selenium for web test automation. 
The purpose of the project is to demonstrate my ability to write test cases, automate them in Selenium, integrate them into a CI pipeline, and unit test helper methods using Mockito.
For further information contact the owner of this repository.

## Setup Instructions

In order to download the project and run the tests follow these instructions:

1. Clone this repo to your local machine by running `git clone https://github.com/OwenGraham/selenium-demo.git`
2. Update Chrome to the latest version on your machine.
3. Download the latest stable release of ChromeDriver from [here](https://googlechromelabs.github.io/chrome-for-testing/).
4. Extract the folder into `src/test/resources/webdrivers` and rename it to `webdrivers`

### To run the tests:
1. Open a new terminal and navigate to the root of the repository. 
2. Run the command `mvn test -Dtest="com.github.owengraham.selenium_project.runners.*Test"`.
3. A html Cucumber report with the test results will be generated at `cucumber-report/cucumber.html`.

### Running in suites

Tests are organised into suites using junit tag annotations, such as @functional and @negative. 
To run the tests in suites, add the name of the ga, without the "@", in quotation marks to the `@IncludeTags` annotation in `src/test/java/com/github/owengraham/selenium_project/runners/RunCucumberTest.java`.

#### Example
```java
@Suite
//Add Tests to run here by their tags
@IncludeTags({
        "functional",
        "negative"
})
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "com.github.owengraham.selenium_project.stepdefinitions")
//To run tests with combinations of tags, uncomment this line and add the desired tags to the value string
//@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@functional and @negative")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "html:cucumber-report/cucumber.html")
public class RunCucumberTest {

}
```

### Running in headed mode

To see the tests running on a screen remove the following line from the method `getChromeOptions()` in `src/test/java/com/github/owengraham/selenium_project/utils/DriverManager.java`:
```java
options.addArguments("headless"); 
```

## Framework Architecture

The framework is written in Java, uses Maven for dependency, and build management predominantly built around Cucumber and Selenium.
Tests are written in Gherkin features and scenarios, which can be found at `src/test/resources/features`, and are linked to the step definitions in `src/test/java/com/github/owengraham/selenium_project/stepdefinitions` via `src/test/java/com/github/owengraham/selenium_project/runners/RunCucumberTest.java`.
Step definitions are split into a class per feature, and those which are used in multiple features are in `CommonStepDefs.java`.

Selenium automates the interactions with the website by controlling a WebDriver object. The setup of the WebDriver, including setting options such as running in headed/headless mode, and setting the window size, is in `src/test/java/com/github/owengraham/selenium_project/utils/DriverManager.java`.
The WebDriver is passed between different step definition classes using a Cucumber PicoContainer, which is initialised and configured in `src/test/java/com/github/owengraham/selenium_project/utils/PicoContainerConfig.java`.

`@Before` and `@After` methods in `src/test/java/com/github/owengraham/selenium_project/stepdefinitions/Hooks.java` manage resetting the state of the WebDriver between tests.  

The framework uses the Page Object Model to improve extensibility and maintainability, with a class for each page of the website, and some page elements (such as products shown on the inventory page), containing locators for page elements and methods for interactions for use in step definitions. These classes can be found in `src/test/java/com/github/owengraham/selenium_project/pages`. 
All methods within the page classes have been unit tested using Junit and Mockito, ensuring they work in edge cases such as when there are no products in the website's inventory. These tests are integrated into a CI pipeline for quality control of the framework itself.
The unit tests can be found in `src/test/java/com/github/owengraham/selenium_project/junit`, and can be run by navigating to the repository root in a terminal and running the command `mvn test -Dtest="com.github.owengraham.selenium_project.junit.*Test"`.

### CI Pipeline

The GitHub repository for the project includes a CI pipeline using GitHub action workflows for building, testing, and branch protection.
The workflow files for the CI pipeline can be found in `.github/workflows`.

The workflow in `unit_tests.yml` runs on pushes and pull requests to the branch `test` and has two jobs:

1. Building the project with Maven
2. Running the unit tests for the methods within the page classes

These jobs are enforced as checks in the branch protection rules of the `test` branch, to stop bugs in the framework from reaching it.

The workflow in `cucumber_tests.yml` runs on pushes and pull requests to the `main` branch. This workflow enforces that all the Cucumber tests must pass for code to be pushed up to this branch.
This is so that if the code for the website itself were in the same project as this test framework, bugs would be caught before reaching the live website.

### Maintenance

The framework's dependencies should be updated to the latest versions in the properties of `POM.xml`:
```xml
<properties>
    <maven.compiler.source>22</maven.compiler.source>
    <maven.compiler.target>22</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <cucumber.version>7.18.1</cucumber.version>
    <selenium.version>4.25.0</selenium.version>
    <junit.version>5.11.0</junit.version>
    <mockito.version>5.12.0</mockito.version>
    <jmeter.version>5.6.3</jmeter.version>
</properties>
```

## Features, Scenarios, and Defects

| Feature                                                                                       | Scenario                                                                       | Tags                              | Defect                                                                                                                                                              |
|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|-----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Cart Page](https://github.com/OwenGraham/selenium-demo/issues/10)                            | Removing a product from the cart as standard_user                              | @cart, @functional, @positive     |                                                                                                                                                                     |
|                                                                                               | Viewing products in the cart as standard_user                                  | @cart, @functional, @positive     |                                                                                                                                                                     |
|                                                                                               | Using Continue Shopping button to return to inventory page as standard_user    | @cart, @functional                |                                                                                                                                                                     |
|                                                                                               | Removing a product from the cart as error_user                                 | @cart, @functional, @negative     |                                                                                                                                                                     |
|                                                                                               | Removing a product from the cart as error_user                                 | @cart, @functional, @negative     |                                                                                                                                                                     |
|                                                                                               | Viewing products in the cart as error_user                                     | @cart, @functional, @negative     | [Add to cart button not working for some products on inventory page as error_user](https://github.com/OwenGraham/selenium-demo/issues/13#issue-2524871010)          |
|                                                                                               | Using Continue Shopping button to return to inventory page as error_user       | @cart, @functional                |                                                                                                                                                                     |
| Checkout Process                                                                              | Initiating checkout process as standard_user                                   | @functional, @positive, @checkout |                                                                                                                                                                     |
|                                                                                               | Filling out checkout information with valid data as standard_user              | @functional, @positive, @checkout |                                                                                                                                                                     |
|                                                                                               | Submitting checkout information with missing first name as standard_user       | @functional, @negative, @checkout |                                                                                                                                                                     |
|                                                                                               | Submitting checkout information with missing last name as standard_user        | @functional, @negative, @checkout |                                                                                                                                                                     |
|                                                                                               | Submitting checkout information with missing postcode as standard_user         | @functional, @negative, @checkout |                                                                                                                                                                     |
|                                                                                               | Viewing the order summary with item, tax, and total price as standard_user     | @functional, @positive, @checkout | [Tax not calculated correctly](https://github.com/OwenGraham/selenium-demo/issues/16)                                                                               |
|                                                                                               | Completing the checkout process and viewing thank you message as standard_user | @functional, @positive, @checkout |                                                                                                                                                                     |
|                                                                                               | Initiating checkout process as error_user                                      | @functional, @positive, @checkout |                                                                                                                                                                     |
|                                                                                               | Filling out checkout information with valid data as error_user                 | @functional, @positive, @checkout |                                                                                                                                                                     |
|                                                                                               | Submitting checkout information with missing first name as error_user          | @functional, @negative, @checkout |                                                                                                                                                                     |
|                                                                                               | Submitting checkout information with missing last name as error_user           | @functional, @negative, @checkout | [Last name field on checkout page one not working for error_user](https://github.com/OwenGraham/selenium-demo/issues/17)                                            |
|                                                                                               | Submitting checkout information with missing postcode as error_user            | @functional, @negative, @checkout |                                                                                                                                                                     |
|                                                                                               | Viewing the order summary with item, tax, and total price as error_user        | @functional, @positive, @checkout | [Tax not calculated correctly](https://github.com/OwenGraham/selenium-demo/issues/16)                                                                               |
|                                                                                               | Completing the checkout process and viewing thank you message as error_user    | @functional, @positive, @checkout | ["Finish" button on checkout step two page not working for error_user](https://github.com/OwenGraham/selenium-demo/issues/18#issue-2533308216)                      |
| [Inventory Page Functionality](https://github.com/OwenGraham/selenium-demo/issues/7)          | Viewing the inventory page with available products as standard_user            | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Sorting products by price (low to high) as standard_user                       | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Sorting products by price (high to low) as standard_user                       | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Adding a product to the cart and checking cart number as standard_user         | @functional, @positive, @cart     |                                                                                                                                                                     |
|                                                                                               | Adding a product to the cart and checking cart as standard_user                | @functional, @positive, @cart     |                                                                                                                                                                     |
|                                                                                               | Removing a product from the cart as standard_user                              | @functional, @positive, @cart     |                                                                                                                                                                     |
|                                                                                               | Adding multiple products to the cart and checking cart as standard_user        | @functional, @positive, @cart     |                                                                                                                                                                     |
|                                                                                               | Adding multiple products to the cart and checking cart number as standard_user | @functional, @positive@cart       |                                                                                                                                                                     |
|                                                                                               | Sorting products by name (A to Z) as standard_user                             | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Sorting products by name (Z to A) as standard_user                             | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Viewing product details from the inventory page as standard_user               | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Viewing the inventory page with available products as error_user               | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Sorting products by price (low to high) as error_user                          | @functional, @positive            | [Product sorting functionality not working as error_user](https://github.com/OwenGraham/selenium-demo/issues/14#issue-2524900975)                                   |
|                                                                                               | Sorting products by price (high to low) as error_user                          | @functional, @positive            | [Product sorting functionality not working as error_user](https://github.com/OwenGraham/selenium-demo/issues/14#issue-2524900975)                                   |
|                                                                                               | Adding a product to the cart and checking cart number as error_user            | @functional, @positive, @cart     |                                                                                                                                                                     |
|                                                                                               | Adding a product to the cart and checking cart as error_user                   | @functional, @positive, @cart     |                                                                                                                                                                     |
|                                                                                               | Removing a product from the cart as error_user                                 | @functional, @positive, @cart     | [Remove button on some products on inventory page not working when logged in as error_user](https://github.com/OwenGraham/selenium-demo/issues/12#issue-2524828069) |
|                                                                                               | Adding multiple products to the cart and checking cart as error_user           | @functional, @positive, @cart     | [Add to cart button not working for some products on inventory page as error_user](https://github.com/OwenGraham/selenium-demo/issues/13#issue-2524871010)          |
|                                                                                               | Adding multiple products to the cart and checking cart number as error_user    | @functional, @positive, @cart     | [Add to cart button not working for some products on inventory page as error_user](https://github.com/OwenGraham/selenium-demo/issues/13#issue-2524871010)          |
|                                                                                               | Sorting products by name (A to Z) as error_user                                | @functional, @positive            |                                                                                                                                                                     |
|                                                                                               | Sorting products by name (Z to A) as error_user                                | @functional, @positive            | [Product sorting functionality not working as error_user](https://github.com/OwenGraham/selenium-demo/issues/14#issue-2524900975)                                   |
|                                                                                               | Viewing product details from the inventory page as error_user                  | @functional, @positive            |                                                                                                                                                                     |
| [Login Functionality](https://github.com/OwenGraham/selenium-demo/issues/1#issue-2492022579)  | Successful login with valid credentials                                        | @functional, @happy_path          |                                                                                                                                                                     |
|                                                                                               | Unsuccessful login with incorrect credentials                                  | @functional, @invalid_login       |                                                                                                                                                                     |
|                                                                                               | Unsuccessful login with empty username                                         | @functional, @invalid_login       |                                                                                                                                                                     |
|                                                                                               | Unsuccessful login with empty password                                         | @functional, @invalid_login       |                                                                                                                                                                     |
|                                                                                               | Unsuccessful login with locked-out user                                        | @functional, @locked_out_user     |                                                                                                                                                                     |

## Test Metrics

<div class="XR3QM0DC8dUJX1FPQBu_" style="
    background-color: var(--cucumber-panel-accent-color, #e8e8e8);
    border: 1px solid rgba(0, 0, 0, 0);
    display: block;
    unicode-bidi: isolate;
    --cucumber-background-color: #1d1d26;
    --cucumber-text-color: #c9c9d1;
    --cucumber-anchor-color: #4caaee;
    --cucumber-keyword-color: #d89077;
    --cucumber-parameter-color: #4caaee;
    --cucumber-tag-color: #85a658;
    --cucumber-docstring-color: #66a565;
    --cucumber-error-background-color: #cf6679;
    --cucumber-error-text-color: #222;
    --cucumber-code-background-color: #282a36;
    --cucumber-code-text-color: #f8f8f2;
    --cucumber-panel-background-color: #282a36;
    --cucumber-panel-accent-color: #313442;
    --cucumber-panel-text-color: #f8f8f2;
    font-size: 14px;
    font-family: ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, Roboto, Arial, sans-serif;
    color: var(--cucumber-text-color, #222);
">
    <dl class="jjG3rnQfHnX9wBgX_idP" style="display: grid;
    grid-template-columns: repeat(12, 1fr);
    grid-template-rows: 1fr 1fr;
    gap: 1px;
    margin: 0;
    text-align: center;">
        <div class="rfv832DGvJuGyzFuMjXC" style="    grid-column-end: span 4; display: flex; flex-direction: column-reverse; justify-content: center; padding: 1.5em;">
            <dt class="xmSDOQ_c8MimWxT035vV" style="font-size: 1em; margin-top: .25em;">
                47 executed
            </dt>
            <dd class="OFz0nWrlbIgqinwc4usO" style="font-weight: bold; font-size: 1.25em;">77% passed</dd>
        </div>
        <div class="rfv832DGvJuGyzFuMjXC" style="    grid-column-end: span 4; display: flex; flex-direction: column-reverse; justify-content: center; padding: 1.5em;">
            <dt class="xmSDOQ_c8MimWxT035vV" style="    margin-top: .25em; margin-top: .25em;">last run</dt>
            <dd class="OFz0nWrlbIgqinwc4usO" style="font-weight: bold; font-size: 1.25em;">26/09/24 13:30</dd>
        </div>
        <div class="rfv832DGvJuGyzFuMjXC" style="    grid-column-end: span 4; display: flex; flex-direction: column-reverse; justify-content: center; padding: 1.5em;">
            <dt class="xmSDOQ_c8MimWxT035vV" style="    color: var(--cucumber-code-text-color, #666); margin-top: .25em;">duration</dt>
            <dd class="OFz0nWrlbIgqinwc4usO" style="font-weight: bold; font-size: 1.25em;">3 minutes 14 seconds</dd>
        </div>
        <div class="rfv832DGvJuGyzFuMjXC" style="    grid-column-end: span 4; display: flex; flex-direction: column-reverse; justify-content: center; padding: 1.5em;">
            <dt class="xmSDOQ_c8MimWxT035vV" style=" margin-top: .25em;">Windows 11</dt>
            <dd class="OFz0nWrlbIgqinwc4usO" style="font-weight: bold; font-size: 1.25em;">
                <svg viewBox="0 0 128 128"><path fill="#00ADEF" d="M126 1.637l-67 9.834v49.831l67-.534zM1.647 66.709l.003 42.404 50.791 6.983-.04-49.057zM58.467 67.389l.094 49.465 67.376 9.509.016-58.863zM1.61 19.297l.047 42.383 50.791-.289-.023-49.016z"></path></svg>
            </dd>
        </div>
        <div class="rfv832DGvJuGyzFuMjXC" style="    grid-column-end: span 4; display: flex; flex-direction: column-reverse; justify-content: center; padding: 1.5em;">
            <dt class="xmSDOQ_c8MimWxT035vV" style=" margin-top: .25em;">OpenJDK 64-Bit Server VM 22.0.1+8-16</dt>
            <dd class="OFz0nWrlbIgqinwc4usO" style="font-weight: bold; font-size: 1.25em;"><svg viewBox="0 0 128 128"><path fill="#0074BD" d="M47.617 98.12s-4.767 2.774 3.397 3.71c9.892 1.13 14.947.968 25.845-1.092 0 0 2.871 1.795 6.873 3.351-24.439 10.47-55.308-.607-36.115-5.969zm-2.988-13.665s-5.348 3.959 2.823 4.805c10.567 1.091 18.91 1.18 33.354-1.6 0 0 1.993 2.025 5.132 3.131-29.542 8.64-62.446.68-41.309-6.336z"></path><path fill="#EA2D2E" d="M69.802 61.271c6.025 6.935-1.58 13.17-1.58 13.17s15.289-7.891 8.269-17.777c-6.559-9.215-11.587-13.792 15.635-29.58 0 .001-42.731 10.67-22.324 34.187z"></path><path fill="#0074BD" d="M102.123 108.229s3.529 2.91-3.888 5.159c-14.102 4.272-58.706 5.56-71.094.171-4.451-1.938 3.899-4.625 6.526-5.192 2.739-.593 4.303-.485 4.303-.485-4.953-3.487-32.013 6.85-13.743 9.815 49.821 8.076 90.817-3.637 77.896-9.468zM49.912 70.294s-22.686 5.389-8.033 7.348c6.188.828 18.518.638 30.011-.326 9.39-.789 18.813-2.474 18.813-2.474s-3.308 1.419-5.704 3.053c-23.042 6.061-67.544 3.238-54.731-2.958 10.832-5.239 19.644-4.643 19.644-4.643zm40.697 22.747c23.421-12.167 12.591-23.86 5.032-22.285-1.848.385-2.677.72-2.677.72s.688-1.079 2-1.543c14.953-5.255 26.451 15.503-4.823 23.725 0-.002.359-.327.468-.617z"></path><path fill="#EA2D2E" d="M76.491 1.587S89.459 14.563 64.188 34.51c-20.266 16.006-4.621 25.13-.007 35.559-11.831-10.673-20.509-20.07-14.688-28.815C58.041 28.42 81.722 22.195 76.491 1.587z"></path><path fill="#0074BD" d="M52.214 126.021c22.476 1.437 57-.8 57.817-11.436 0 0-1.571 4.032-18.577 7.231-19.186 3.612-42.854 3.191-56.887.874 0 .001 2.875 2.381 17.647 3.331z"></path></svg></dd>
        </div>
        <div class="rfv832DGvJuGyzFuMjXC" style="    grid-column-end: span 4; display: flex; flex-direction: column-reverse; justify-content: center; padding: 1.5em;">
            <dt class="xmSDOQ_c8MimWxT035vV" style=" margin-top: .25em;">cucumber-jvm 7.18.1</dt>
            <dd class="OFz0nWrlbIgqinwc4usO" style="font-weight: bold; font-size: 1.25em;"><svg viewBox="0 0 40.59 46.31"><g><path fill="#23d96c" fill-rule="evenodd" d="M30.283 3.645q-.528-.317-1.08-.593a16.164 16.164 0 00-1.154-.518c-.124-.052-.247-.1-.372-.149-.343-.127-.689-.268-1.042-.371a19.427 19.427 0 10-9.792 37.51v5.56c11.676-1.753 22.016-10.979 22.787-23.093.459-7.289-3.193-14.73-9.347-18.346z"></path><path fill="#173647" d="M15.787 46.307v-5.935A20.472 20.472 0 1126.959 1.015c.274.08.557.187.832.291l.248.093c.165.064.291.113.417.167.348.137.739.313 1.208.543q.589.295 1.153.633c6.393 3.756 10.354 11.518 9.857 19.316-.763 12-10.722 22.122-23.679 24.067zm4.8-44.214h-.026a18.366 18.366 0 00-3.524 36.408l.85.165v5.18c11.392-2.224 20.009-11.272 20.686-21.922.448-7.033-3.1-14.018-8.83-17.383l-.008-.005A14.691 14.691 0 0027.654 3.5a5.74 5.74 0 00-.344-.138l-.27-.1a9.49 9.49 0 00-.708-.249 18.425 18.425 0 00-5.743-.92z"></path><path fill="#173647" fill-rule="evenodd" d="M16.666 10.58a1.8 1.8 0 011.583.608 4.184 4.184 0 01.728 1.107c.645 1.422 1.027 3.461.23 4.605a6.334 6.334 0 01-3.981-3.087 3.236 3.236 0 01-.347-1.339 1.957 1.957 0 011.787-1.894zm-5.683 8.025a7.742 7.742 0 001.218.737 5.789 5.789 0 004.883-.138 6.116 6.116 0 00-3.345-3.45 3.664 3.664 0 00-1.442-.321 1.884 1.884 0 00-.319 0 1.766 1.766 0 00-.995 3.172zm6.1 3.433c-.777-.518-2.379-.309-3.312-.292a4.416 4.416 0 00-1.666.352 3.5 3.5 0 00-1.218.738 1.817 1.817 0 001.409 3.171 3.3 3.3 0 001.442-.321c1.436-.62 3.141-2.32 3.346-3.648zm2.61 2a6.556 6.556 0 00-3.724 3.506 3.091 3.091 0 00-.321 1.314 1.907 1.907 0 003.3 1.346 7.422 7.422 0 00.7-1.218c.621-1.333.866-3.72.046-4.948zm2.557-7.167a5.941 5.941 0 003.7-3.167 3.243 3.243 0 00.319-1.346 1.915 1.915 0 00-1.794-1.954 1.832 1.832 0 00-1.6.641 7.382 7.382 0 00-.705 1.218c-.62 1.434-.842 3.48.081 4.603zm4.208 12.115a3.244 3.244 0 00-.321-1.345 5.869 5.869 0 00-3.554-3.269 5.386 5.386 0 00-.226 4.711 4.147 4.147 0 00.7 1.121c1.133 1.23 3.505.32 3.402-1.218zm4.2-6.28a7.466 7.466 0 00-1.217-.7 4.425 4.425 0 00-1.666-.352 6.4 6.4 0 00-3.188.555 5.959 5.959 0 003.316 3.386 3.672 3.672 0 001.442.32 1.8 1.8 0 001.31-3.209z"></path></g></svg></dd>
        </div>
    </dl>
</div>

## Defects 

| Defect          | Remove button on some products on inventory page not working when logged in as error_user                                                                                                         |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Date            | 13/09/2024                                                                                                                                                                                        |
| Details         | When logged in as error_user, the remove from cart button for some items on the inventory page has no effect                                                                                      |
| Expected Result | Clicking remove from cart on an item should make the button change to say "add to cart", the number next the the cart should decrement by one, and the item should not be listed on the cart page |
| Actual Result   | Clicking the remove from cart button has no effect                                                                                                                                                |
| Test Data       | Item: Sauce Labs Backpack                                                                                                                                                                         |
| Environment     | OS: Widows 11 Home x64, Browser: Chrome Version 128.0.6613.137                                                                                                                                    |
| Future Steps    |                                                                                                                                                                                                   | 

| Defect          | Product sorting functionality not working as error_user                                                                                         |
|-----------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| Date            | 13/09/2024                                                                                                                                      |
| Details         | When logged in as error_user, the sorting functionality on the inventory page does't work                                                       |
| Expected Result | Selecting an option from sort by dropdown menu on the inventory page should sort the products accordingly                                       |
| Actual Result   | When an option is selected from the dropdown, an alert appears with the message "Sorting is broken! This error has been reported to Backtrace." |
| Test Data       |                                                                                                                                                 |
| Environment     | OS: Windows 11 Home x64, Browser: Chrome Version 128.0.6613.137                                                                                 |
| Future Steps    |                                                                                                                                                 |

| Defect          | Add to cart button not working for some products on inventory page as error_user                                                                                                                                 |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Date            | 13/09/2024                                                                                                                                                                                                       |
| Details         | When logged in as error_user, the add to cart button on some products on the inventory page does not work                                                                                                        |
| Expected Result | clicking add to cart button on any inventory page item should change the button to remove from cart, the number next to the cart icon should increment by one, and the product should be listed on the cart page |
| Actual Result   | clicking add to cart button has no effect                                                                                                                                                                        |
| Test Data       | product: Sauce Labs Bolt T-Shirt                                                                                                                                                                                 |
| Environment     | OS: Windows 11 Home x64, Browser: Chrome Version 128.0.6613.137                                                                                                                                                  |
| Future Steps    |                                                                                                                                                                                                                  |

| Defect          | Add to cart button not working for some products on inventory page as error_user                                                                                                                                 |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Date            | 13/09/2024                                                                                                                                                                                                       |
| Details         | When logged in as error_user, the add to cart button on some products on the inventory page does not work                                                                                                        |
| Expected Result | clicking add to cart button on any inventory page item should change the button to remove from cart, the number next to the cart icon should increment by one, and the product should be listed on the cart page |
| Actual Result   | clicking add to cart button has no effect                                                                                                                                                                        |
| Test Data       | product: Sauce Labs Bolt T-Shirt                                                                                                                                                                                 |
| Environment     | OS: Windows 11 Home x64, Browser: Chrome Version 128.0.6613.137                                                                                                                                                  |
| Future Steps    |                                                                                                                                                                                                                  |

| Defect          | Last name field on checkout page one not working for error_user                                                                                                                                                                                                                                                           |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Date            | 18/09/2024                                                                                                                                                                                                                                                                                                                |
| Details         | When logged in as error_user, the last name field on checkout page one does not accept any input (clicking the field and entering keys does not alter the text displayed in the field). The form then accepts the data and allows the user to continue with the checkout process despite the last name field being blank. |
| Expected Result | Selecting the Last Name field on the checkout page one and entering keys should cause the entered keys to display in the input field. Clicking the continue button without a last name being in the input field should not navigate to the checkout step two page and an error message should be displayed instead.       |
| Actual Result   | Entering keys with the last name field selected does not change text displayed in field from "Last Name"                                                                                                                                                                                                                  |
| Test Data       |                                                                                                                                                                                                                                                                                                                           |
| Environment     | OS: Windows Home x64, Browser: Chrome version 128.0.6613.138                                                                                                                                                                                                                                                              |
| Future Steps    |                                                                                                                                                                                                                                                                                                                           |

| Defect          | Tax not calculated correctly                                                                                                                                                                                              |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Date            | 18/09/2024                                                                                                                                                                                                                |
| Details         | When logged in as standard_user, the amount of tax displayed on the checkout step two is not consistent with the current UK VAT rate (20%), and in fact the tax rate seems to vary depending on the products in the cart. |
| Expected Result | Tax should be $6.00                                                                                                                                                                                                       |
| Actual Result   | Tax is $2.40                                                                                                                                                                                                              |
| Test Data       | Product in cart is x1 Sauce Labs Backpack                                                                                                                                                                                 |
| Environment     | OS: Windows Home x64, Browser: Chrome version 128.0.6613.138                                                                                                                                                              |
| Future Steps    |                                                                                                                                                                                                                           |
