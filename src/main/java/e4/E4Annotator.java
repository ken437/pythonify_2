package e4;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyCallExpression;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E4Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return new E4QuickFix(element);
    }

    @Override
    public @NotNull String getMessage() {
        return "E4: Use of constructor rather than literal to create empty list";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        if (!(element instanceof PyCallExpression))
        {
            return false;
        }
        PyCallExpression callExpr = (PyCallExpression) element;
        int nArgs = callExpr.getArguments().length;
        return callExpr.isCalleeText("list") && nArgs == 0;
    }
}
