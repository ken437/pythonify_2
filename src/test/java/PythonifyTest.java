import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;

import java.io.File;

public abstract class PythonifyTest extends LightJavaCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
    }

    /**
     * Invoke all of the available quick fixes in a given file
     * @param myFixture myFixture field available in the subclass
     * @param dataDir name of the subdirectory of testData in which the test files are (e.g. "e1")
     */
    private void invokeAllQuickFixes(JavaCodeInsightTestFixture myFixture, String dataDir)
    {
        String beforeFile = dataDir + "_test_quick_fix_before.py";
        String beforeFilePath = appendPaths(dataDir, beforeFile);
        for (IntentionAction fix: myFixture.getAllQuickFixes(beforeFilePath))
        {
            PsiFile[] files = FilenameIndex.getFilesByName(getProject(), beforeFile,
                    GlobalSearchScope.allScope(getProject()));
            assertEquals(1, files.length);
            fix.invoke(getProject(), getEditor(), files[0]);
        }
    }

    private String appendPaths(String path1, String path2)
    {
        return new File(path1, path2).getPath();
    }

    /**
     * Tests the highlighting of a file containing issues
     * @param myFixture myFixture field available in the subclass
     * @param dataDir name of the subdirectory of testData in which the test files are (e.g. "e1")
     */
    public void checkHighlight(JavaCodeInsightTestFixture myFixture, String dataDir) {
        String highlightFile = this.appendPaths(dataDir, dataDir + "_test_highlight.py");
        myFixture.configureByFile(highlightFile);
        myFixture.checkHighlighting(true, false, false, false);
    }

    /**
     * Tests the quick fixes in a file containing issues
     * @param myFixture myFixture field available in the subclass
     * @param dataDir name of the subdirectory of testData in which the test files are (e.g. "e1")
     */
    public void checkQuickFix(JavaCodeInsightTestFixture myFixture, String dataDir) {
        String afterFile = dataDir + "_test_quick_fix_after.py";
        String afterFilePath = appendPaths(dataDir, afterFile);
        this.invokeAllQuickFixes(myFixture, dataDir);
        myFixture.checkResultByFile(afterFilePath);
    }
}
