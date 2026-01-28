package uitestbase;

import helpers.WaitHelper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ui test base page abstract class
 * Functionality:
 * Setup logger,
 * Setup waitHelper
 * for every page
 */
public class BasePage {
    protected static WebDriver driver;
    protected static WaitHelper waitHelper;

    ///LOCATORS
    private final By footerCopy = By.className("footer_copy");

    // Dynamically assign the logger to the child class,
    // so in the logs it will say on which class what is happening
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        waitHelper = new WaitHelper(driver);
    }

    public void verifyFooter(String expectedText) {
        logger.info("Verifying footer...");

        String footerText = waitHelper.waitForElementVisible(footerCopy).getText();

        Assertions.assertEquals(expectedText, footerText, "Footer text did not match!");
    }
}
