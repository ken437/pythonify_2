import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyStringLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class E1Annotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof PyStringLiteralExpression)) {
            return;
        }
        PyStringLiteralExpression stringElem = (PyStringLiteralExpression) element;
        if (stringElem.getStringValue().equals("Hello, my name is Bob")) {
            return;
        }

        holder.newAnnotation(HighlightSeverity.WARNING, "This string is not Bobby enough!")
                .withFix(new E1QuickFix(element))
                .create();
    }
}
