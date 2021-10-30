package e1;

import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyReferenceExpression;
import e1.E1Parser;
import general.CodeBuilder;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

/**
 * A quick fix for the E1 antipattern
 */
public class E1QuickFix extends PythonifyQuickFix {
    public E1QuickFix(PsiElement element) {
        super(element);
    }

    @Override
    public void replace(PsiElement element, CodeBuilder codeBuilder) throws NotE1Exception {
        E1Parser parser = new E1Parser(element);
        assert parser.isE1();
        PyExpression replacement = codeBuilder.buildExpression("a[n]");
        PyReferenceExpression nameBeforeSub =
                (PyReferenceExpression) codeBuilder.buildExpression(parser.getNameBeforeSub());
        PyExpression subscriptContents =
                codeBuilder.buildExpression("-" + parser.getNumAfterMinus());
        replacement.getChildren()[0].replace(nameBeforeSub);
        replacement.getChildren()[1].replace(subscriptContents);
        element.replace(replacement);
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "E1";
    }
}
