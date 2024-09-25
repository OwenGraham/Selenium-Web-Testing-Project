# Selenium Web Testing Project

## Contents 

- [Overview](#overview)
- [Setup Instructions](#setup-instructions)

## Overview

This is a web testing project for testing the functionality of the [Sauce Demo](https://www.saucedemo.com/) made-for-testing e-commerce site, created by Owen Graham. 
The project uses Cucumber for BDD and Selenium for web test automation. 
The purpose of the project is to demonstrate my ability to write test cases, automate them in Selenium, integrate them into a CI pipeline, and unit test helper methods using Mockito.
For further information contact the owner of this repository.

## Setup Instructions

In order to download the project and run the tests follow these instructions:

- Clone this repo to your local machine 

`git clone https://github.com/OwenGraham/selenium-demo.git`

- Download the latest stable release of ChromeDriver from [here](https://googlechromelabs.github.io/chrome-for-testing/).
- Extract the folder and move the executable `chromedriver.exe` to `src/test/resources/webdrivers`
- To run the tests:
  - open a new terminal and navigate to the root of the repository.
  - run the command `mvn test -Dtest="com.github.owengraham.selenium_project.runners.*Test"`.
- A html Cucumber report with the test results will be generated at `cucumber-report/cucumber.html`