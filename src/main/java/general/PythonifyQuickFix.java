package general;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import e1.NotE1Exception;
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

        Runnable tryReplace =  () -> {
            try {
                this.replace(this.element, new CodeBuilder(project));
            } catch (NoAntipatternException e) {
                throw new IncorrectOperationException(
                        "Expected antipattern of type " + e.getAntipatternType() +
                                " when replacing code but it wasn't present");
            }
        };
        WriteCommandAction.runWriteCommandAction(project, tryReplace);
    }

    /**
     * Gets a PSI replacement for the original PSI element in
     * the quick fix.
     *
     * @param element the PSI element to replace
     * @param codeBuilder an object making it easier to generate PSI elements from text
     */
    public abstract void replace(PsiElement element, CodeBuilder codeBuilder) throws NoAntipatternException;
}
