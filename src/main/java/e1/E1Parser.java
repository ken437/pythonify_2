package e1;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;

import java.util.Objects;

/**
 * Parses and extracts information from a PsiElement
 * that is potentially an E1
 */
public class E1Parser {
    private boolean isE1;
    private String nameBeforeSub;
    private long numAfterMinus;

    /**
     * Given a PsiElement, determine if it is an E1 and
     * if so, extract fields from it.
     * @param element the PsiElement to parse, looking for an E1 issue
     *                and copying necessary fields if it is there
     */
    public E1Parser(PsiElement element)
    {
        initFields(element);
    }

    // Separate method from constructor to allow returns
    private void initFields(PsiElement element)
    {
        if (!(element instanceof PySubscriptionExpression))
        {
            this.isE1 = false;
            return;
        }
        PySubscriptionExpression subElem = (PySubscriptionExpression) element;
        PsiElement[] subChildren = subElem.getChildren();
        if (subChildren.length != 2)
        {
            this.isE1 = false;
            return;
        }
        if (!(subChildren[0] instanceof PyReferenceExpression && subChildren[1] instanceof PyBinaryExpression))
        {
            this.isE1 = false;
            return;
        }
        PyReferenceExpression refBeforeSub = (PyReferenceExpression) subChildren[0];
        PyBinaryExpression binAfterSub = (PyBinaryExpression) subChildren[1];
        PyExpression leftPart = binAfterSub.getLeftExpression();
        PyExpression rightPart = binAfterSub.getRightExpression();
        PyElementType opType = binAfterSub.getOperator();
        if (!(leftPart instanceof PyCallExpression && rightPart instanceof PyNumericLiteralExpression))
        {
            this.isE1 = false;
            return;
        }
        PyCallExpression leftCall = (PyCallExpression) leftPart;
        PyNumericLiteralExpression litRight = (PyNumericLiteralExpression) rightPart;
        Long rightNum = litRight.getLongValue();
        if (opType == null || rightNum == null || rightNum <= 0)
        {
            this.isE1 = false;
            return;
        }
        if (!(opType.toString().equals("Py:MINUS")))
        {
            this.isE1 = false;
            return;
        }
        if (!(leftCall.isCalleeText("len")))
        {
            this.isE1 = false;
            return;
        }
        PyExpression[] leftCallArgs = leftCall.getArguments();
        if (leftCallArgs.length != 1 || !(leftCallArgs[0] instanceof PyReferenceExpression))
        {
            this.isE1 = false;
            return;
        };
        PyReferenceExpression leftCallArg = (PyReferenceExpression) leftCallArgs[0];
        if (Objects.equals(refBeforeSub.getName(), leftCallArg.getName()))
        {
            this.isE1 = true;
            this.nameBeforeSub = refBeforeSub.getName();
            this.numAfterMinus = rightNum;
        }
        else
        {
            this.isE1 = false;
        }
    }

    public boolean isE1() {
        return isE1;
    }

    public String getNameBeforeSub() throws NotE1Exception {
        if (!isE1)
        {
            throw new NotE1Exception();
        }
        return nameBeforeSub;
    }

    public long getNumAfterMinus() throws NotE1Exception {
        if (!isE1)
        {
            throw new NotE1Exception();
        }
        return numAfterMinus;
    }
}
