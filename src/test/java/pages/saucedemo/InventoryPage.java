package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class InventoryPage extends BasePage {

    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addItemToCart(String itemName) {
        // Dynamic locator to find the specific "Add to cart" button for a named item
        // This makes it so it can find any item
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName);
        logger.info("Adding item to cart: {}", itemName);
        waitHelper.waitForElementClickable(By.xpath(xpath)).click();
    }

    public int getCartBadgeCount() {
        String count = waitHelper.waitForElementVisible(cartBadge).getText();
        return Integer.parseInt(count);
    }

    public  CartPage goToCart() {
        logger.info("Navigating to Cart...");
        waitHelper.waitForElementClickable(cartLink).click();
        return new CartPage(driver);
    }
}
