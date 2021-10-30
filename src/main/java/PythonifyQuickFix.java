import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a quick fix that the Pythonify tool can perform when it encounters
 * an antipattern
 */
public abstract class PythonifyQuickFix extends BaseIntentionAction {
    private final PsiElement element;

    public PythonifyQuickFix(PsiElement element) {
        this.element = element;
    }

    @Override
    public @NotNull String getText() {
        return "Quick fix";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        TextRange range = this.element.getTextRange();
        int start = range.getStartOffset();
        int end = range.getEndOffset();

        Document document = editor.getDocument();
        WriteCommandAction.runWriteCommandAction(project,
                () -> document.replaceString(start, end, this.getReplacement(this.element)));
    }

    /**
     * Gets a textual replacement for the original PSI element in
     * the quick fix. Remember to escape double quotes.
     *
     * @param element the PSI element to replace
     * @return the text to replace the PSI element as a string
     */
    public abstract @NotNull String getReplacement(PsiElement element);
}
