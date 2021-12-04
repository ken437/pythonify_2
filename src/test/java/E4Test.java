public class E4Test extends PythonifyTest {
    public void testE4Highlight() {
        this.checkHighlight(myFixture, "e4");
    }

    public void testE4QuickFix() {
        this.checkQuickFix(myFixture, "e4");
    }
}
