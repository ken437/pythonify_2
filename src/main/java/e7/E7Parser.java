package e7;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;
import general.TreeTraverseHelper;

public class E7Parser {
    private boolean isE7;
    private PyExpression assignTarget;  // expression on the left side of the initial assignment
    private PyExpression forTarget;  // expression between the "for" and "in" keywords
    private PyExpression forSource;  // expression after the "in" keyword
    private PyExpression appendArg;  // argument to the append() call in the if statement body
    private PyExpression ifCond;  // condition of the if statement within the for loop body

    /**
     * Given a PsiElement, determine if it is an E7 and
     * if so, extract fields from it.
     * @param element the PsiElement to parse, looking for an E7 issue
     *                and copying necessary fields if it is there
     */
    public E7Parser(PsiElement element) {
        initFields(element);
    }

    // Separate method from constructor to allow returns
    private void initFields(PsiElement element)
    {
        TreeTraverseHelper helper = new TreeTraverseHelper();

        if (!(element instanceof PyForStatement))
        {
            this.isE7 = false;
            return;
        }

        PsiElement prevElem = helper.getPreviousNWSibling(element);

        if (!(prevElem instanceof PyAssignmentStatement))
        {
            this.isE7 = false;
            return;
        }

        PyAssignmentStatement prevAssign = (PyAssignmentStatement) prevElem;
        PyForStatement forElem = (PyForStatement) element;

        if (!(this.initFieldsFromAssign(prevAssign) && this.initFieldsFromLoop(forElem)))
        {
            this.isE7 = false;
            return;
        }
        this.isE7 = true;
    }

    /**
     * @param prevAssign Assignment statement before the for loop being examined
     * @return false if something found that is inconsistent with an E7 issue
     *         being present; true otherwise
     */
    private boolean initFieldsFromAssign(PyAssignmentStatement prevAssign) {
        PyExpression leftExpr = prevAssign.getLeftHandSideExpression();
        this.assignTarget = leftExpr;

        PyExpression valAssigned = prevAssign.getAssignedValue();
        if (!(valAssigned instanceof PyListLiteralExpression))
        {
            return false;
        }
        PyListLiteralExpression assignedList = (PyListLiteralExpression) valAssigned;
        if (assignedList.getElements().length != 0)
        {
            return false;
        }
        return true;
    }

    /**
     * @param forElem For loop being examined
     * @return false if something found that is inconsistent with an E7 issue
     *         being present; true otherwise
     */
    private boolean initFieldsFromLoop(PyForStatement forElem)
    {
        PyForPart forPart = forElem.getForPart();
        if (forPart.getTarget() == null)
        {
            return false;
        }
        this.forTarget = forPart.getTarget();
        if (forPart.getSource() == null)
        {
            return false;
        }
        this.forSource = forPart.getSource();

        PyStatement[] loopBody = forPart.getStatementList().getStatements();
        if (loopBody == null || loopBody.length != 1)
        {
            return false;
        }
        PyStatement firstStatement = loopBody[0];
        if (!(firstStatement instanceof PyIfStatement))
        {
            return false;
        }
        PyIfStatement ifStatement = (PyIfStatement) firstStatement;
        PyIfPart ifPart = ifStatement.getIfPart();
        if (ifStatement.getElifParts().length != 0 || ifStatement.getElsePart() != null)
        {
            return false;
        }
        if (ifPart.getCondition() == null)
        {
            return false;
        }
        this.ifCond = ifPart.getCondition();
        PyStatement[] ifBody =  ifPart.getStatementList().getStatements();
        if (ifBody == null || ifBody.length != 1)
        {
            return false;
        }
        if (!(ifBody[0] instanceof PyExpressionStatement))
        {
            return false;
        }
        PyExpression firstExpr = ((PyExpressionStatement) ifBody[0]).getExpression();
        if (!(firstExpr instanceof PyCallExpression))
        {
            return false;
        }
        PyCallExpression firstCall = (PyCallExpression) firstExpr;
        PyExpression firstCallee = firstCall.getCallee();
        if (firstCallee == null)
        {
            return false;
        }
        PsiElement firstChild = firstCallee.getFirstChild();
        // NOTE: assumes assign statement is parsed before the for loop
        if (firstChild == null || !(firstChild.getText().equals(this.assignTarget.getText())))
        {
            return false;
        }
        PsiElement lastChild = firstCallee.getLastChild();
        if (!lastChild.getText().equals("append"))
        {
            return false;
        }

        PyArgumentList argList = firstCall.getArgumentList();
        if (argList == null || argList.getArguments().length != 1)
        {
            return false;
        }
        this.appendArg = argList.getArguments()[0];
        return true;
    }

    public boolean isE7() {
        return isE7;
    }

    public PyExpression getForTarget() {
        return forTarget;
    }

    public PyExpression getForSource() {
        return forSource;
    }

    public PyExpression getAppendArg() {
        return appendArg;
    }

    public PyExpression getAssignTarget() {
        return assignTarget;
    }

    public PyExpression getIfCond() {
        return ifCond;
    }
}