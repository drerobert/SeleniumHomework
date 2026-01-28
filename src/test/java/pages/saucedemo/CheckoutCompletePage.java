package pages.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class CheckoutCompletePage extends BasePage {

    private final By completeHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public void verifyCompleteMessage(String expectedMessage) {
        logger.info("Verifying complete message is: '{}'", expectedMessage);

        String actualMessage = waitHelper.waitForElementVisible(completeHeader).getText();

        Assertions.assertEquals(expectedMessage, actualMessage,
                "The checkout complete message did not match!");
    }
}
