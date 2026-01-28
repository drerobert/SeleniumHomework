package uitests;


import helpers.credentialHelpers.CredentialDTO;
import helpers.credentialHelpers.JsonCredentialsParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.saucedemo.LoginPage;
import uitestbase.BaseUiTest;

import java.time.Year;

import static uitestbase.config.Config.BASE_SAUCE_DEMO_URL;

public class SauceDemoTests extends BaseUiTest {
    /// CONSTANTS
    private final static String EXPECTED_THANK_YOU_MESSAGE = "Thank you for your order!";
    private final static String ITEM_BACKPACK = "Sauce Labs Backpack";
    private final static String ITEM_FLEECE_JACKET = "Sauce Labs Fleece Jacket";
    private final static String ERROR_USERNAME_EMPTY = "Epic sadface: Username is required";
    private final static String ERROR_PASSWORD_EMPTY = "Epic sadface: Password is required";
    private final static String ERROR_WRONG_USERNAME_AND_PASSWORD = "Epic sadface: Username and password do not match any user in this service";
    private final static String EXPECTED_FOOTER_TEXT = "© " + Year.now().getValue() + " Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";


    /// TESTS
    /**
     * Case 1.
     * Login
     * Add items
     * Checkout
     * Assert finish text
     * Data: performance_glitch_user is used
     */
    @Test
    @DisplayName("Case_1: Purchase Process Test")
    public void case1_PurchaseProcessTest() {
        //Setup user
        CredentialDTO glitchUser = JsonCredentialsParser.getCredential("performance_glitch_user");

        driver.get(BASE_SAUCE_DEMO_URL);

        new LoginPage(driver)
                .open()
                .login(glitchUser)
                .addItems(ITEM_BACKPACK, ITEM_FLEECE_JACKET)
                .verifyCartBadgeCount(2)
                .goToCart()
                .clickCheckout()
                .enterInformation("John", "Doe", "1235")
                .clickContinue()
                .clickFinish()
                .verifyCompleteMessage(EXPECTED_THANK_YOU_MESSAGE);
    }

    /**
     * Case 2.
     * Verify login errors
     * Then Login
     * Validate footer
     * Data: standard_user is used
     */
    @Test
    @DisplayName("Case_2: Verify Login mandatory field errors")
    public void case2_verifyLoginMandatoryFieldErrors() {
        CredentialDTO standardUser = JsonCredentialsParser.getCredential("standard_user");

        //Create users for validation
        CredentialDTO emptyUser = createUser("","");
        CredentialDTO onlyUsername = createUser("a", "");
        CredentialDTO onlyPassword = createUser("", "a");
        CredentialDTO wrongCredentials = createUser("a", "a");

        new LoginPage(driver)
                .open()
                .tryLoginFailure(emptyUser)
                .verifyLoginError(ERROR_USERNAME_EMPTY)
                //Try with username only
                .tryLoginFailure(onlyUsername)
                .verifyLoginError(ERROR_PASSWORD_EMPTY)
                .tryLoginFailure(onlyPassword)
                .verifyLoginError(ERROR_USERNAME_EMPTY)
                .tryLoginFailure(wrongCredentials)
                .verifyLoginError(ERROR_WRONG_USERNAME_AND_PASSWORD)
                //Second part the footer verification
                .login(standardUser)
                .verifyFooter(EXPECTED_FOOTER_TEXT);
    }
}
