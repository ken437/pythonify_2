package e7;

import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyAssignmentStatement;
import com.jetbrains.python.psi.PyComprehensionForComponent;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyListCompExpression;
import e6.E6Parser;
import general.CodeBuilder;
import general.NoAntipatternException;
import general.PythonifyQuickFix;
import general.TreeTraverseHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class E7QuickFix extends PythonifyQuickFix {
    public E7QuickFix(PsiElement element) {
        super(element);
    }

    @Override
    public void replace(PsiElement element, CodeBuilder codeBuilder) throws NoAntipatternException {
        E7Parser parser = new E7Parser(element);
        assert parser.isE7();
        TreeTraverseHelper traverseHelper = new TreeTraverseHelper();

        PsiElement previous = traverseHelper.getPreviousNWSibling(element);
        assert previous != null;
        assert previous instanceof PyAssignmentStatement;
        PyAssignmentStatement assignToModify = (PyAssignmentStatement) previous;

        PsiElement replacementListCompElem = codeBuilder.buildExpression("[a for b in c if d]");
        assert replacementListCompElem instanceof PyListCompExpression;
        PyListCompExpression replacementListComp = (PyListCompExpression) replacementListCompElem;
        this.replaceA(replacementListComp, parser.getAppendArg());
        this.replaceB(replacementListComp, parser.getForTarget());
        this.replaceC(replacementListComp, parser.getForSource());
        this.replaceD(replacementListComp, parser.getIfCond());

        PsiElement assignedValToModify = assignToModify.getAssignedValue();
        assert assignedValToModify != null;
        assignedValToModify.replace(replacementListComp);

        element.delete();  // remove original for loop from the PSI tree
    }

    private void replaceA(PyListCompExpression listComp, PyExpression newA)
    {
        PsiElement[] children = listComp.getChildren();
        int childIdxOfA = 0;
        assert children.length >= childIdxOfA + 1;
        PsiElement aElem = children[childIdxOfA];
        aElem.replace(newA);
    }

    private void replaceB(PyListCompExpression listComp, PyExpression newB)
    {
        PyComprehensionForComponent forComp = getForComp(listComp);
        PyExpression iterVar = forComp.getIteratorVariable();
        assert iterVar != null;
        iterVar.replace(newB);
    }

    private void replaceC(PyListCompExpression listComp, PyExpression newC)
    {
        PyComprehensionForComponent forComp = getForComp(listComp);
        PyExpression iterList = forComp.getIteratedList();
        assert iterList != null;
        iterList.replace(newC);
    }

    private void replaceD(PyListCompExpression listComp, PyExpression newD)
    {
        PsiElement[] children = listComp.getChildren();
        int childIdxOfD = 3;
        assert children.length >= childIdxOfD + 1;
        PsiElement dElem = children[childIdxOfD];
        dElem.replace(newD);
    }

    private PyComprehensionForComponent getForComp(PyListCompExpression listComp) {
        List<PyComprehensionForComponent> list = listComp.getForComponents();
        assert list.size() == 1;
        return list.get(0);
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "E7";
    }
}
