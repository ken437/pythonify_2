public class E7Test extends PythonifyTest {
    public void testE7Highlight() {
        this.checkHighlight(myFixture, "e7");
    }

    public void testE7QuickFix() {
        this.checkQuickFix(myFixture, "e7");
    }
}
