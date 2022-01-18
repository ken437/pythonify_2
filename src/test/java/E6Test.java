public class E6Test extends PythonifyTest {
    public void testE6Highlight() {
        this.checkHighlight(myFixture, "e6");
    }

    public void testE6QuickFix() {
        this.checkQuickFix(myFixture, "e6");
    }
}
