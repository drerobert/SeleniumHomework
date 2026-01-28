package pages.saucedemo;

import helpers.credentialHelpers.CredentialDTO;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import uitestbase.BasePage;

public class LoginPage extends BasePage{
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    private final By errorContainer = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage login(CredentialDTO user) {
        enterCredentials(user);
        waitHelper.waitForElementVisible(By.id("inventory_container")); // Wait for success
        return new InventoryPage(driver);
    }

    //Negative path
    public LoginPage tryLoginFailure(CredentialDTO user) {
        enterCredentials(user);
        return this;
    }

    private void enterCredentials(CredentialDTO user) {
        logger.info("Attempting login with user: {}", user.getUsername());
        // Find the element first
        WebElement userElement = waitHelper.waitForElementVisible(usernameField);
        userElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        userElement.sendKeys(user.getUsername());

        WebElement passwordElement = waitHelper.waitForElementVisible(passwordField);
        passwordElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        passwordElement.sendKeys(user.getPassword());

        waitHelper.waitForElementClickable(loginButton).click();
    }


    public LoginPage verifyLoginError(String expectedMessage) {
        logger.info("Verifying login error message...");
        String actualMessage = waitHelper.waitForElementVisible(errorContainer).getText();

        Assertions.assertEquals(expectedMessage, actualMessage, "Login error message mismatch!");

        return this;
    }
}