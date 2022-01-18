package e6;

import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.psi.PsiElement;
import general.CodeBuilder;
import general.NoAntipatternException;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E6QuickFix extends PythonifyQuickFix {
    public E6QuickFix(PsiElement element) {
        super(element);
    }

    @Override
    public void replace(PsiElement element, CodeBuilder codeBuilder) throws NoAntipatternException {

    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "E6";
    }
}
