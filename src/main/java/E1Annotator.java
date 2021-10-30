import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyStringLiteralExpression;
import org.jetbrains.annotations.NotNull;

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
        return "E1: This string is not Bobby enough!";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        if (!(element instanceof PyStringLiteralExpression)) {
            return false;
        }
        PyStringLiteralExpression stringElem = (PyStringLiteralExpression) element;
        return !(stringElem.getStringValue().equals("Hello, my name is Bob"));
    }
}
