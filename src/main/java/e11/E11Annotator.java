package e11;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyCallExpression;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyReferenceExpression;
import com.jetbrains.python.psi.PyStringLiteralExpression;
import com.jetbrains.python.refactoring.PyBaseRefactoringAction;
import general.HardcodeUtils;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class E11Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E11: password argument of a function was previously assigned a hardcoded string";
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
            if (kwargVal instanceof PyReferenceExpression)
            {
                System.out.println(kwargVal.getText());
                return true;
            }
        }
        return false;
    }
}
