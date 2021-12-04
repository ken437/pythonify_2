package e5;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyCallExpression;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E5Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return new E5QuickFix(element);
    }

    @Override
    public @NotNull String getMessage() {
        return "E5: Use of constructor rather than literal to create empty dict";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        if (!(element instanceof PyCallExpression))
        {
            return false;
        }
        PyCallExpression callExpr = (PyCallExpression) element;
        int nArgs = callExpr.getArguments().length;
        return callExpr.isCalleeText("dict") && nArgs == 0;
    }
}
