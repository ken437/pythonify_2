package e6;

import com.intellij.psi.PsiElement;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

public class E6Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return new E6QuickFix(element);
    }

    @Override
    public @NotNull String getMessage() {
        return "For loop can be collapsed into list comprehension";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        E6Parser parser = new E6Parser(element);
        return parser.isE6();
    }
}
