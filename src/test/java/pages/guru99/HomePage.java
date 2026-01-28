package pages.guru99;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import uitestbase.BasePage;

import java.time.Duration;

import static uitestbase.config.Config.BASE_GURU_DEMO_URL;

public class HomePage extends BasePage {

    /// LOCATORS
    private final By iframeBanner = By.id("a077aa5e");
    private final By bannerLink = By.xpath("//a[contains(@href, 'live-selenium-project')]");

    private final By testingMenu = By.cssSelector("li.item118 > a.item");
    private final By seleniumLink = By.cssSelector("li.item118 .dropdown a[href*='selenium-tutorial']");

    //We use the id since the text and color is not the same as in the requirement
    private final By subscribeButton = By.id("submitBtn");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(BASE_GURU_DEMO_URL);
        return this;
    }

    public HomePage clickIframeBanner() {
        logger.info("Attempting to switch to iframe...");

        waitHelper.waitForFrameAndSwitch(iframeBanner);

        logger.info("Clicking banner image...");
        WebElement link = waitHelper.waitForElementVisible(bannerLink);
        link.click();

        driver.switchTo().defaultContent();
        return this;
    }

    public HomePage switchToNewTabAndVerifyTitle(String expectedTitle) {
        logger.info("Switching to new tab...");
        String originalWindow = driver.getWindowHandle();

        waitHelper.waitForNewWindow(2);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        logger.info("Verifying Title: " + expectedTitle);

        // We use 'contains' because titles often have extra whitespace or suffixes
        waitHelper.waitForTitleContains(expectedTitle);

        driver.close();
        driver.switchTo().window(originalWindow);

        return this;
    }

    public HomePage navigateToSeleniumViaMenu() {
        logger.info("Navigating to Selenium menu...");

        WebElement parentMenu = waitHelper.waitForElementVisible(testingMenu);

        // Element is not visible so use findElement
        WebElement subLink = driver.findElement(seleniumLink);

        new Actions(driver)
                .moveToElement(parentMenu)
                .pause(Duration.ofMillis(500))
                .moveToElement(subLink)
                .click()
                .build()
                .perform();

        return this;
    }

    //TODO: Verify if this is the correct button and if not change this functionality
    public HomePage verifySubscribeButtonDisplayed() {
        logger.info("Verifying Subscribe button...");

        WebElement button = waitHelper.waitForElementVisible(subscribeButton);

        Assertions.assertTrue(button.isDisplayed(), "Button with ID 'submitBtn' is not visible!");

        String buttonText = button.getText();
        if (buttonText.isEmpty()) {
            buttonText = button.getAttribute("value");
        }

        logger.info("Found Button Text: " + buttonText);

        Assertions.assertTrue(buttonText.contains("Subscribe for Free"),
                "Button ID matched, but text was wrong! Found: " + buttonText);

        return this;
    }
}