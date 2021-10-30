package general;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Annotates an antipattern in a Python file with the warning highlight
 * level, a message, and possibly a quick fix.
 */
public abstract class PythonifyAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (this.shouldAnnotate(element)) {
            PythonifyQuickFix quickFix = this.getQuickFix(element);
            if (quickFix == null)
            {
                holder.newAnnotation(HighlightSeverity.WARNING, this.getMessage())
                        .create();
            }
            else
            {
                holder.newAnnotation(HighlightSeverity.WARNING, this.getMessage())
                        .withFix(quickFix).create();
            }
        }
    }

    /**
     * Given a PsiElement, gets a quick fix for that element
     * @param element the PsiElement that the quick fix must be able to replace
     * @return a quick fix that can be used to textually replace this PsiElement, or
     *      null if no quick fix should be available for this issue
     */
    public abstract PythonifyQuickFix getQuickFix(PsiElement element);

    /**
     * Gets a string describing the issue
     * that should be displayed when the annotation is hovered over
     * @return the message
     */
    public abstract @NotNull String getMessage();

    /**
     * Determine whether this PsiElement should be annotated
     * @param element the PsiElement to check
     * @return true iff the element should be highlighted as an issue
     */
    public abstract boolean shouldAnnotate(PsiElement element);
}
