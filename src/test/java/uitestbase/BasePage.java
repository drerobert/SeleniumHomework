package uitestbase;

import helpers.WaitHelper;
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

    // Dynamically assign the logger to the child class,
    // so in the logs it will say on which class what is happening
    protected final  Logger logger = LoggerFactory.getLogger(getClass());

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        waitHelper = new WaitHelper(driver);
    }
}
