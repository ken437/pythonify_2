package e5;

import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyExpression;
import general.CodeBuilder;
import general.NoAntipatternException;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E5QuickFix extends PythonifyQuickFix {
    public E5QuickFix(PsiElement element) {
        super(element);
    }

    @Override
    public void replace(PsiElement element, CodeBuilder codeBuilder) throws NoAntipatternException {
        if (!(new E5Annotator().shouldAnnotate(element)))
        {
            throw new NotE5Exception();
        }
        PyExpression replacement = codeBuilder.buildExpression("{}");
        element.replace(replacement);
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "E5";
    }
}
