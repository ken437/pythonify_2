package e3;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyGlobalStatement;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E3Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E3: Mutable global variables are not recommended";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        return element instanceof PyGlobalStatement;
    }
}
