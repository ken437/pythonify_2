package e11;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;
import com.jetbrains.python.refactoring.PyBaseRefactoringAction;
import general.HardcodeUtils;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import general.TreeTraverseHelper;
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
        TreeTraverseHelper helper = new TreeTraverseHelper();
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
                PyReferenceExpression argElem = (PyReferenceExpression) kwargVal;
                PyAssignmentStatement lastAssign = helper.findLastAssignToVar(argElem);
                if (lastAssign == null)
                {
                    return false;
                }
                else
                {
                    return lastAssign.getAssignedValue() instanceof PyStringLiteralExpression;
                }
            }
        }
        return false;
    }
}
