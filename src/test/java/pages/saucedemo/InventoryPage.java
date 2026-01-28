package pages.saucedemo;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitestbase.BasePage;

public class InventoryPage extends BasePage {

    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage addItems(String... itemsToAdd) {

        if (itemsToAdd == null || itemsToAdd.length == 0) {
            throw new IllegalArgumentException("Test Error: You must provide at least one item.");
        }

        for (String itemName : itemsToAdd) {
            this.addItemToCart(itemName);
        }

        return this;
    }

    public CartPage goToCart() {
        logger.info("Navigating to Cart...");
        waitHelper.waitForElementClickable(cartLink).click();
        return new CartPage(driver);
    }

    public InventoryPage verifyCartBadgeCount(int expectedCount) {
        logger.info("Validating cart number should be: {}", expectedCount);

        String countText = waitHelper.waitForElementVisible(cartBadge).getText();
        int actualCount = Integer.parseInt(countText);

        Assertions.assertEquals(expectedCount, actualCount, "Cart badge count mismatch!");

        return this;
    }

    private void addItemToCart(String itemName) {
        logger.info("Adding item to cart: {}", itemName);

        // Find the text, goes up to the container, and finds the button
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName);


        waitHelper.waitForElementClickable(By.xpath(xpath)).click();
    }
}
