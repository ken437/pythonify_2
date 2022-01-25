package e7;

import com.intellij.psi.PsiElement;
import e6.E6Parser;
import e6.E6QuickFix;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E7Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return new E7QuickFix(element);
    }

    @Override
    public @NotNull String getMessage() {
        return "For loop with if statement can be collapsed into list comprehension";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        E7Parser parser = new E7Parser(element);
        return parser.isE7();
    }
}
