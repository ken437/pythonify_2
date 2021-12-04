import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.uast.values.UBooleanConstant;

public class E1Test extends LightJavaCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }

    /**
     * Tests the highlighting of a file containing E1 issues
     */
    public void testE1() {
        myFixture.configureByFile("e1test.py");
        myFixture.checkHighlighting(true, false, false, false);
    }
}
