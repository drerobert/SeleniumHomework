package pages.htmleditor;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import uitestbase.BasePage;

import static uitestbase.config.Config.BASE_ONLINE_HTML_EDITOR_URL;

public class HtmlEditorPage extends BasePage {

    private final By editorInput = By.cssSelector(".ck-editor__editable");

    public HtmlEditorPage(WebDriver driver) {
        super(driver);
    }

    public HtmlEditorPage open() {
        driver.get(BASE_ONLINE_HTML_EDITOR_URL);
        return this;
    }

    public HtmlEditorPage setContentWithInlineCss(String htmlContent) {
        logger.info("Setting text content... ");

        WebElement editor = waitHelper.waitForElementVisible(editorInput);

        // CKEditor 5 attaches the instance to the DOM element as a property '.ckeditorInstance'
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "const domEditableElement = arguments[0];"
                        + "domEditableElement.ckeditorInstance.setData(arguments[1]);"
                ,
                editor, htmlContent
        );

        return this;
    }

    public HtmlEditorPage verifyEditorText(String expectedText) {
        logger.info("Verifying editor text matches: '{}'", expectedText);

        String actualText = waitHelper.waitForElementVisible(editorInput).getText();

        Assertions.assertEquals(expectedText, actualText,
                "The text inside the rich text editor did not match!");

        return this;
    }

    public HtmlEditorPage verifyTextIsBold(String wordToVerify) {
        logger.info("Verifying '{}' is BOLD...", wordToVerify);

        // Find the specific element containing this text inside the editor
        // Look for ANY tag that contains the specific text
        WebElement wordElement = waitHelper.waitForElementVisible(
                By.xpath("//div[contains(@class, 'ck-content')]//*[text()='" + wordToVerify + "']")
        );

        String fontWeight = wordElement.getCssValue("font-weight");
        logger.info("Found font-weight: {}", fontWeight);

        // Validate (Browsers return "bold", "700", "800", or "900" for bold)
        boolean isBold = fontWeight.equals("bold") ||
                Integer.parseInt(fontWeight) >= 700;

        Assertions.assertTrue(isBold,
                "Expected '" + wordToVerify + "' to be bold (>=700), but found weight: " + fontWeight);

        return this;
    }

    public HtmlEditorPage verifyTextIsUnderlined(String wordToVerify) {
        logger.info("Verifying '{}' is UNDERLINED...", wordToVerify);

        WebElement wordElement = waitHelper.waitForElementVisible(
                By.xpath("//div[contains(@class, 'ck-content')]//*[text()='" + wordToVerify + "']")
        );

        String textDecoration = wordElement.getCssValue("text-decoration");

        logger.info("Found text-decoration: {}", textDecoration);

        Assertions.assertTrue(textDecoration.contains("underline"),
                "Expected '" + wordToVerify + "' to be underlined, but found: " + textDecoration);

        return this;
    }
}
