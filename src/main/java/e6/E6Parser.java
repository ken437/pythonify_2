package e6;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;
import general.TreeTraverseHelper;

import java.util.Arrays;

public class E6Parser {
    private boolean isE6;
    private PyExpression assignTarget;  // expression on the left side of the initial assignment
    private PyExpression forTarget;  // expression between the "for" and "in" keywords
    private PyExpression forSource;  // expression after the "in" keyword
    private PyArgumentList appendArgs; // arguments to the append() call in the for loop body

    /**
     * Given a PsiElement, determine if it is an E6 and
     * if so, extract fields from it.
     * @param element the PsiElement to parse, looking for an E6 issue
     *                and copying necessary fields if it is there
     */
    public E6Parser(PsiElement element) {
        initFields(element);
    }

    // Separate method from constructor to allow returns
    private void initFields(PsiElement element)
    {
        TreeTraverseHelper helper = new TreeTraverseHelper();

        if (!(element instanceof PyForStatement))
        {
            this.isE6 = false;
            return;
        }

        PsiElement prevElem = helper.getPreviousNWSibling(element);

        if (!(prevElem instanceof PyAssignmentStatement))
        {
            this.isE6 = false;
            return;
        }

        PyAssignmentStatement prevAssign = (PyAssignmentStatement) prevElem;
        PyForStatement forElem = (PyForStatement) element;

        if (!(this.initFieldsFromAssign(prevAssign) && this.initFieldsFromLoop(forElem)))
        {
            this.isE6 = false;
            return;
        }
        this.isE6 = true;
    }

    /**
     * @param prevAssign Assignment statement before the for loop being examined
     * @return false if something found that is inconsistent with an E6 issue
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
     * @return false if something found that is inconsistent with an E6 issue
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

        PyStatement[] statements = forPart.getStatementList().getStatements();
        if (statements == null || statements.length != 1)
        {
            return false;
        }
        PyStatement firstStatement = statements[0];
        if (!(firstStatement instanceof PyExpressionStatement))
        {
            return false;
        }
        PyExpression firstExpr = ((PyExpressionStatement) firstStatement).getExpression();
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
        if (argList == null)
        {
            return false;
        }
        this.appendArgs = argList;
        return true;
    }

    public boolean isE6() {
        return isE6;
    }

    public PyExpression getForTarget() {
        return forTarget;
    }

    public PyExpression getForSource() {
        return forSource;
    }

    public PyArgumentList getAppendArgs() {
        return appendArgs;
    }

    public PyExpression getAssignTarget() {
        return assignTarget;
    }
}