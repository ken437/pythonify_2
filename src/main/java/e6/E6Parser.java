package e6;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;
import general.TreeTraverseHelper;

import java.util.Arrays;

public class E6Parser {
    private boolean isE6;
    private String assignTarget;  // variable name on the left side of the initial assignment

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

        if (!this.initFieldsFromAssign(prevAssign) || this.initFieldsFromLoop(forElem))
        {
            this.isE6 = false;
            return;
        }
    }

    /**
     * @param prevAssign Assignment statement before the for loop being examined
     * @return false if something found that is inconsistent with an E6 issue
     *         being present; true otherwise
     */
    private boolean initFieldsFromAssign(PyAssignmentStatement prevAssign) {
        PyExpression leftExpr = prevAssign.getLeftHandSideExpression();
        if (!(leftExpr instanceof PyTargetExpression)) {
            return false;
        }
        PyTargetExpression leftTarget = (PyTargetExpression) leftExpr;
        PsiElement leftName = leftTarget.getNameIdentifier();
        if (leftName == null) {
            return false;
        }
        this.assignTarget = leftName.getText();

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
        System.out.println(forElem.getForPart().getText());
        return true;
    }

    public boolean isE6() {
        return isE6;
    }
}