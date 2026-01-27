package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import uitestbase.BasePage;

public class CheckoutInfoPage extends BasePage {
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void enterInformation(String fName, String lName, String zip) {
        logger.info("Entering checkout info: {} {}, {}", fName, lName, zip);

        //Need to wait to be able to click it. Click into it since for some reason it won't register the typing.
        WebElement firstName = waitHelper.waitForElementClickable(firstNameInput);
        firstName.click();
        firstName.sendKeys(fName);

        driver.findElement(lastNameInput).sendKeys(lName);
        driver.findElement(postalCodeInput).sendKeys(zip);
    }

    // Navigates to the NEXT page
    public CheckoutOverviewPage clickContinue() {
        logger.info("Clicking Continue button...");
        waitHelper.waitForElementClickable(continueButton).click();
        return new CheckoutOverviewPage(driver);
    }
}
