public class E5Test extends PythonifyTest {
    public void testE5Highlight() {
        this.checkHighlight(myFixture, "e5");
    }

    public void testE5QuickFix() {
        this.checkQuickFix(myFixture, "e5");
    }
}
