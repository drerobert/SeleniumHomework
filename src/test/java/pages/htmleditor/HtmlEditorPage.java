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

    /**
     * Uses JavascriptExecutor to inject HTML with Inline CSS directly into the editor.
     */
    public HtmlEditorPage setContentWithInlineCss(String htmlContent) {
        logger.info("Setting text content... ");

        WebElement editor = waitHelper.waitForElementVisible(editorInput);

        // CKEditor 5 attaches the instance to the DOM element as a property '.ckeditorInstance'
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "const domEditableElement = arguments[0];" + "domEditableElement.ckeditorInstance.setData(arguments[1]);"
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
}
