import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.jetbrains.python.psi.PyStringLiteralExpression;
import org.jetbrains.annotations.NotNull;

public class ExampleAnnotation implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

        if (!(element instanceof PyStringLiteralExpression)) {
            return;
        }

        holder.newAnnotation(HighlightSeverity.WARNING, "Annotation message!")
                .withFix(new ExampleContextAction(element))
                .create();
    }
}
