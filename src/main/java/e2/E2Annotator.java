package e2;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyFromImportStatement;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E2Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E2: Star imports are not recommended";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        if (!(element instanceof PyFromImportStatement))
        {
            return false;
        }
        PyFromImportStatement fromImportElem = (PyFromImportStatement) element;
        return (fromImportElem.getStarImportElement() != null);
    }
}
