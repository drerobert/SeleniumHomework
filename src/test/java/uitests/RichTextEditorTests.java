package uitests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.htmleditor.HtmlEditorPage;
import uitestbase.BaseUiTest;

public class RichTextEditorTests extends BaseUiTest {
    /// CONSTANTS
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
    public void case3_verifyRichTextEditor() {

        new HtmlEditorPage(driver)
                .open()
                .setContentWithInlineCss(FORMATTED_RICH_TEXT)
                //Verify the text itself
                .verifyEditorText(EXPECTED_TEXT)
                //Verify specific formatting
                .verifyTextIsBold("Automation")
                .verifyTextIsUnderlined("Test");
        ;

    }
}
