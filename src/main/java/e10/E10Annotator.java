package e10;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyCallExpression;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyStringLiteralExpression;
import general.HardcodeUtils;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import general.TreeTraverseHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class E10Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E10: password argument of a function directly passed a hardcoded string";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        if (!(element instanceof PyCallExpression))
        {
            return false;
        }
        PyCallExpression callExpr = (PyCallExpression) element;
        for (String passwordKwarg: HardcodeUtils.getPasswordKeywords())
        {
            PyExpression kwargVal = callExpr.getKeywordArgument(passwordKwarg);
            if (kwargVal instanceof PyStringLiteralExpression)
            {
                return true;
            }
        }
        return false;
    }
}
