package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class LoginPage extends BasePage{
    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /// type in username, password and click submit
    public void login(String username, String password) {
        logger.info("Logging in with credentials {} {}",username,password);
        waitHelper.waitForElementVisible(usernameField).sendKeys(username);
        waitHelper.waitForElementVisible(passwordField).sendKeys(password);
        waitHelper.waitForElementClickable(loginButton).click();
    }
}