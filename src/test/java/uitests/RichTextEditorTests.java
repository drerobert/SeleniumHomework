package uitests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.htmleditor.HtmlEditorPage;
import uitestbase.BaseUiTest;

import static uitestbase.config.Config.BASE_ONLINE_HTML_EDITOR_URL;

public class RichTextEditorTests extends BaseUiTest {
    /// CONSTANTS
    ///
    // Define the input with Inline CSS
    private static final String FORMATTED_RICH_TEXT =
            "<span style='font-weight: bold;'>Automation</span> " +
                    "<span style='text-decoration: underline;'>Test</span> " +
                    "Example";

    private final static String EXPECTED_TEXT = "Automation Test Example";


    /// TESTS
    /**
     * Case 3.
     * Open the Rich text editor site
     * Write in Automation Test Example
     * Automation with bold
     * Test with underline
     * Validate the result
     */
    @Test
    @DisplayName("Case_3: Rich Text Editor test")
    public void case1_PurchaseProcessTest() {

        new HtmlEditorPage(driver)
                .open()
                .setContentWithInlineCss(FORMATTED_RICH_TEXT)
                .verifyEditorText(EXPECTED_TEXT);
    }
}
