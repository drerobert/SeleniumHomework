
## Selenium Java Automation Framework - Genesys interview homework

This repository contains an automated test suite for various websites
The project was made for evaluation purposes for genesys
It is an implementation of the requirements from HomeWork.pdf

The project follows the Page Object Model (POM) design pattern

src/test/java

apitests – API test scenarios

helpers – Shared utilities (Credential parsing, WaitHelpers)

pages – Page Objects organized by domain (guru99, htmleditor, saucedemo)

uitestbase – Core framework logic (BasePage, BaseUiTest, config)

uitests – executable test classes (GuruTests, SauceDemoTests, RichTextEditorTests)

src/test/resources (credentials and requirements)

## Tech Stack
Language: Java 17

Core Framework: Selenium WebDriver 4

Test Runner: JUnit 5

Build Tool: Gradle

Data Parsing: Jackson 

Logging: SLF4J / Logback

Test data (credential.json) and project requirements (HomeWork.pdf)

## How to Run
Run all tests:
./gradlew test

Run a specific test case:
./gradlew test --tests "uitests.GuruTests.case4_verifyIframesTabsAndButton"

## Lessons learned/Thought process
[Notes.md](NOTES.md)

## Contact
Author: Drenyovszki Róbert - robert.drenyovszki@gmail.com
