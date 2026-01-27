package uitests;


import helpers.credentialHelpers.CredentialDTO;
import helpers.credentialHelpers.JsonCredentialsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.saucedemo.*;
import uitestbase.BaseUiTest;

public class SauceDemoTests extends BaseUiTest {
    /// CONSTANTS
    private final static String BASE_URL = "https://www.saucedemo.com";
    private final static String THANK_YOU_MESSAGE = "Thank you for your order!";

    //parse the JSON for credentials
    CredentialDTO loginCredentials = JsonCredentialsParser.parseJson();

    ///TESTS
    @Test
    @DisplayName("Purchase Process Test")
    public void case1_PurchaseProcessTest() {
        // Open the browser
        driver.get(BASE_URL);
        
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login(loginCredentials.getUsername(), loginCredentials.getPassword());

        CheckoutInfoPage infoPage = addItemsAndCheckout("Sauce Labs Backpack", "Sauce Labs Fleece Jacket");

        infoPage.enterInformation("John", "Doe", "1234");
        CheckoutOverviewPage overviewPage = infoPage.clickContinue();
        CheckoutCompletePage completePage = overviewPage.clickFinish();

        // Validation
        Assertions.assertEquals(THANK_YOU_MESSAGE, completePage.getCompleteMessage());
    }

    /**
     * Adds custom number of items to cart and proceeds to check out.
     * Usage: addItemsAndCheckout("Item 1", "Item 2", "Item 3");
     */
    private CheckoutInfoPage addItemsAndCheckout(String... itemsToAdd) {
        // Professional safeguard: Ensure the test fails if no items are passed
        if (itemsToAdd == null || itemsToAdd.length == 0) {
            throw new IllegalArgumentException("Test Error: You must provide at least one item name to checkout.");
        }

        InventoryPage inventoryPage = new InventoryPage(driver);

        // Loop through every item passed to the method
        for (String item : itemsToAdd) {
            inventoryPage.addItemToCart(item);
        }

        // Navigate flow
        CartPage cartPage = inventoryPage.goToCart();
        return cartPage.clickCheckout();
    }
}
