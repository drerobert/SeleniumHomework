package uitests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.guru99.HomePage;
import uitestbase.BaseUiTest;

public class GuruTests extends BaseUiTest {
    /// LOCATORS
    //TODO: Also verify requirement
    private static final String expectedTabTitle = "Selenium Live Project for Practice";

    /// TESTS
    /**
     * Case 4.
     * Open the Rich text editor site
     * Write in Automation Test Example
     * Automation with bold
     * Test with underline
     * Validate the result
     */
    @Test
    @DisplayName("Case_4: Tab and I frame handling")
    public void case4_verifyIframesTabsAndButton() {
        new HomePage(driver)
                .open()
                .clickIframeBanner()
                .switchToNewTabAndVerifyTitle(expectedTabTitle)
                .navigateToSeleniumViaMenu()
                .verifySubscribeButtonDisplayed();
    }
}
