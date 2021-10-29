import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class E1QuickFix extends PythonifyQuickFix {
    public E1QuickFix(PsiElement element) {
        super(element);
    }

    @Override
    public @NotNull String getReplacement(PsiElement element) {
        return "\"Hello, my name is Bob\"";
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "E1";
    }
}
