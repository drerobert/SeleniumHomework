package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class CheckoutOverviewPage extends BasePage {
    /// LOCATORS
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    // Navigates to the NEXT page
    public CheckoutCompletePage clickFinish() {
        logger.info("Clicking Finish button on Overview page...");
        waitHelper.waitForElementClickable(finishButton).click();
        return new CheckoutCompletePage(driver);
    }
}
