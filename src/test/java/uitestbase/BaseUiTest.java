package uitestbase;

import helpers.credentialHelpers.CredentialDTO;
import helpers.credentialHelpers.JsonCredentialsParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ui test base class
 * Functionality:
 * Driver setup and teardown
 * Setup chrome
 */
public class BaseUiTest {
    protected WebDriver driver;

    protected static final Logger logger = LoggerFactory.getLogger(BaseUiTest.class);

    //parse the JSON for credentials
    protected static final List<CredentialDTO> loginCredentials = JsonCredentialsParser.getCachedCredentials();

    @BeforeEach
    public void initializeDriver() {
        logger.info("Initializing WebDriver for UI Test...");

        ChromeOptions options = getChromeOptions();

        // WebDriverManager ensures the correct chromedriver binary is present
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);

        // Maximize the window so every element can be seen
        driver.manage().window().maximize();

        // Wait a bit so the driver can be set up
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("WebDriver initialized successfully.");
    }

    @NotNull
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        //Store preferences
        Map<String, Object> prefs = new HashMap<>();

        //Disable chrome pop ups
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-notifications"); // Blocks "Show Notifications" popups
        return options;
    }

    protected static CredentialDTO createUser(String user, String pass) {
        return new CredentialDTO(user, pass);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            logger.info("Webdriver teardown initiated");
            driver.quit();
            driver = null;
            logger.info("Webdriver teardown successfull");
        }
    }
}
