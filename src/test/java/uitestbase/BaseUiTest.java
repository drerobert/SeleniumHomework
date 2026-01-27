package uitestbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Ui test base class
 * Functionality:
 * Driver setup and teardown
 */
public class BaseUiTest {
    protected WebDriver driver;

    protected static final Logger logger = LoggerFactory.getLogger(BaseUiTest.class);

    @BeforeEach
    public void initializeDriver(){
        logger.info("Initializing WebDriver for UI Test...");
        // WebDriverManager ensures the correct chromedriver binary is present
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);

        // Maximize the window so every element can be seen
        driver.manage().window().maximize();

        // Wait a bit so the driver can be set up
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("WebDriver initialized successfully.");
    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            logger.info("Webdriver teardown initiated");
            driver.quit();
            driver = null;
            logger.info("Webdriver teardown successfull");
        }
    }
}
