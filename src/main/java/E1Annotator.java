import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;
import com.jetbrains.python.psi.impl.references.PyOperatorReference;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

/**
 * Annotates the E1 issue with a warning highlight and descriptive message
 */
public class E1Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E1: Access from end of array without negative index";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        /*
        if (!(Objects.equals(element.getText(), "b[len(b) - 2]")))
        {
            return false;
        }
        */
        if (!(element instanceof PySubscriptionExpression))
        {
            return false;
        }
        PySubscriptionExpression subElem = (PySubscriptionExpression) element;
        PsiElement[] subChildren = subElem.getChildren();
        if (subChildren.length != 2)
        {
            return false;
        }
        if (!(subChildren[0] instanceof PyReferenceExpression && subChildren[1] instanceof PyBinaryExpression))
        {
            return false;
        }
        PyReferenceExpression refBeforeSub = (PyReferenceExpression) subChildren[0];
        PyBinaryExpression binAfterSub = (PyBinaryExpression) subChildren[1];
        PyExpression leftPart = binAfterSub.getLeftExpression();
        PyExpression rightPart = binAfterSub.getRightExpression();
        PyElementType opType = binAfterSub.getOperator();
        if (!(leftPart instanceof PyCallExpression && rightPart instanceof PyNumericLiteralExpression))
        {
            return false;
        }
        PyCallExpression leftCall = (PyCallExpression) leftPart;
        PyNumericLiteralExpression litRight = (PyNumericLiteralExpression) rightPart;
        Long rightNum = litRight.getLongValue();
        if (opType == null || rightNum == null || rightNum <= 0)
        {
            return false;
        }
        if (!(opType.toString().equals("Py:MINUS")))
        {
            return false;
        }
        if (!(leftCall.isCalleeText("len")))
        {
            return false;
        }
        PyExpression[] leftCallArgs = leftCall.getArguments();
        if (leftCallArgs.length != 1 || !(leftCallArgs[0] instanceof PyReferenceExpression))
        {
            return false;
        };
        PyReferenceExpression leftCallArg = (PyReferenceExpression) leftCallArgs[0];
        return (Objects.equals(refBeforeSub.getName(), leftCallArg.getName()));
    }
}
