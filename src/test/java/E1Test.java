/**
 * Performs testing related to the E1 issue
 */
public class E1Test extends PythonifyTest {
    /**
     * Tests the highlighting of a file containing E1 issues
     */
    public void testE1Highlight() {
        this.checkHighlight(myFixture, "e1");
    }

    /**
     * Tests the quick fixes in a file containing E1 issues
     */
    public void testE1QuickFix() {
        this.checkQuickFix(myFixture, "e1");
    }
}
