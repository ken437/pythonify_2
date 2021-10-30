package examples;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

public class ExampleContextAction extends BaseIntentionAction {
    private final PsiElement element;

    public ExampleContextAction(PsiElement element) {
        this.element = element;
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "ExampleContextAction";
    }

    @Override
    public @NotNull String getText() {
        return "Do something!";
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
                () -> document.replaceString(start, end, "\"I fixed it!\""));
    }
}
