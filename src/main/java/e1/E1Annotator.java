package e1;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.*;
import com.jetbrains.python.psi.impl.references.PyOperatorReference;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Queue;

/**
 * Annotates the E1 issue with a warning highlight and descriptive message
 */
public class E1Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return new E1QuickFix(element);
    }

    @Override
    public @NotNull String getMessage() {
        return "E1: Access from end of array without negative index";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        E1Parser parser = new E1Parser(element);
        return parser.isE1();
    }
}
