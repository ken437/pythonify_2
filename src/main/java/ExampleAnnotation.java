import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;

public class ExampleAnnotation implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof PsiLiteralExpression))
        {
            return;
        }
        PsiLiteralExpression expression = (PsiLiteralExpression) element;
        if (!(expression.getValue() instanceof String)) {
            return;
        }

        TextRange wholeRange = TextRange.from(0, 10);
        holder.newAnnotation(HighlightSeverity.WARNING, "Annotation message!")
                .create();
    }
}
