package e8;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyCallExpression;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyFromImportStatement;
import com.jetbrains.python.psi.PyWithItem;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import general.TreeTraverseHelper;
import org.jetbrains.annotations.NotNull;

public class E8Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E8: Use a with statement when opening a file using open()";
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        TreeTraverseHelper helper = new TreeTraverseHelper();
        if (!helper.isFuncWithName(element, "open"))
        {
            return false;
        }
        PsiElement parent = element.getParent();
        return !(parent instanceof PyWithItem);
    }
}
