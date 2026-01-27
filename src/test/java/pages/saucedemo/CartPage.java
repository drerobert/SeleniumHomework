package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class CartPage extends BasePage {

    private static final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutInfoPage clickCheckout() {
        logger.info("Clicking Checkout button...");
        waitHelper.waitForElementClickable(checkoutButton).click();
        return new CheckoutInfoPage(driver);
    }
}