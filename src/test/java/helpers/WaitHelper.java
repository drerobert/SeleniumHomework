package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class WaitHelper {
    private static WebDriver driver = null;
    private static final Logger logger = LoggerFactory.getLogger(WaitHelper.class);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public WaitHelper(WebDriver driver) {
        WaitHelper.driver = driver;
    }

    /**
     * Waits for visibility with the default timeout (10 seconds).
     */
    public static WebElement waitForElementVisible(By locator) {
        return waitForElementVisible(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for visibility with a custom timeout.
     */
    public static WebElement waitForElementVisible(By locator, Duration timeout) {
        logger.debug("Waiting for element to be visible: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable with the default timeout.
     */
    public WebElement waitForElementClickable(By locator) {
        return waitForElementClickable(locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits for an element to be clickable with a custom timeout.
     */
    public WebElement waitForElementClickable(By locator, Duration timeout) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for a new window with default timeout.
     */
    public void waitForNewWindow(int expectedNumberOfWindows) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
    }

    /**
     * Waits for an iframe and switches with default timeout.
     */
    public void waitForFrameAndSwitch(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        // ExpectedConditions.frameToBeAvailableAndSwitchToIt is highly reliable
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    /**
     * Waits for the title text that contains the titlefraction with default timeout.
     */
    public void waitForTitleContains(String titleFraction) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.titleContains(titleFraction));
    }
}