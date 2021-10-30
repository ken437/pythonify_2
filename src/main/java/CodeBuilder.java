import com.intellij.openapi.project.Project;
import com.jetbrains.python.psi.LanguageLevel;
import com.jetbrains.python.psi.PyElementGenerator;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyReferenceExpression;
import org.jetbrains.annotations.NotNull;

/**
 * Allows the generation of PsiElements from text using a
 * consistent language level
 */
public class CodeBuilder {
    private final PyElementGenerator elementGenerator;
    private final LanguageLevel level;

    public CodeBuilder(Project project)
    {
        this.elementGenerator = PyElementGenerator.getInstance(project);
        this.level = LanguageLevel.PYTHON310;
    }

    /**
     * Build an expression from input text
     * @param expression the input expression as text
     * @return a PsiElement representing the expression
     */
    public @NotNull PyExpression buildExpression(@NotNull String expression)
    {
        return this.elementGenerator.createExpressionFromText(this.level, expression);
    }
}
