package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class CheckoutCompletePage extends BasePage {

    private final By completeHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteMessage() {
        logger.info("Checking complete message header...");
        return waitHelper.waitForElementVisible(completeHeader).getText();
    }
}
