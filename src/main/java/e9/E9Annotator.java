package e9;

import com.intellij.psi.PsiElement;
import com.jetbrains.python.psi.PyCallExpression;
import com.jetbrains.python.psi.PyExpression;
import com.jetbrains.python.psi.PyStringLiteralExpression;
import com.jetbrains.python.psi.PyWithItem;
import general.PythonifyAnnotator;
import general.PythonifyQuickFix;
import general.TreeTraverseHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class E9Annotator extends PythonifyAnnotator {
    @Override
    public PythonifyQuickFix getQuickFix(PsiElement element) {
        return null;
    }

    @Override
    public @NotNull String getMessage() {
        return "E9: input() echoes the password to the terminal; use getpass() or a similar function instead";
    }

    private String removePunctuation(String input)
    {
        StringBuilder output = new StringBuilder();
        String punctuation = "#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        for (int i = 0; i < input.length(); i++)
        {
            if (punctuation.lastIndexOf(input.charAt(i)) == -1)
            {
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    private String cleanPrompt(String prompt)
    {
        String lower = prompt.toLowerCase(Locale.ROOT);
        String puncFree = this.removePunctuation(lower);
        return puncFree.trim();
    }

    private boolean isPasswordPrompt(String prompt)
    {
        // Matches strings that are very likely password prompts
        String passwordPromptRegex = "^(?:please )?(?:enter )?(?:your )?(?:a )?(?:the )?[a-zA-Z]* ?password$";
        String cleanPrompt = this.cleanPrompt(prompt);
        return cleanPrompt.matches(passwordPromptRegex);
    }

    @Override
    public boolean shouldAnnotate(PsiElement element) {
        TreeTraverseHelper helper = new TreeTraverseHelper();
        if (!helper.isFuncWithName(element, "input"))
        {
            return false;
        }
        PyCallExpression callExpr = (PyCallExpression) element;
        PyExpression[] arguments = callExpr.getArguments();
        if (arguments.length != 1)
        {
            return false;
        }
        if (!(arguments[0] instanceof PyStringLiteralExpression))
        {
            return false;
        }
        PyStringLiteralExpression inputArg = (PyStringLiteralExpression) arguments[0];
        String prompt = inputArg.getStringValue();
        return this.isPasswordPrompt(prompt);
    }
}
