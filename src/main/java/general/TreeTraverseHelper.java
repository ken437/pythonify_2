package general;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl;
import com.jetbrains.python.psi.PyCallExpression;
import com.jetbrains.python.psi.PyExpression;

/**
 * Contains utility methods to help assist with traversing the PSI tree
 */
public class TreeTraverseHelper {

    /**
     * Get the previous non-whitespace sibling of a PSI element
     * @param element the element to start searching from
     * @return the previous non-whitespace sibling of element; if element is the first
     *         non-whitespace sibling, return null
     */
    public PsiElement getPreviousNWSibling(PsiElement element)
    {
        PsiElement prev = element.getPrevSibling();
        while (prev != null && this.isWhitespace(prev))
        {
            prev = prev.getPrevSibling();
        }
        return prev;
    }

    /**
     * Determine whether a PSI element is whitespace
     * @param element the element to check
     * @return true iff element is some sort of whitespace
     */
    public boolean isWhitespace(PsiElement element)
    {
        return (element instanceof PsiWhiteSpace);
    }

    /**
     * Determine if the argument is a function (not method) with the name 'name'
     * For instance:
     * element='open()', name='open' -> true
     * element='self.open()', name='open' -> false
     * element='object.open()', name='open' -> false
     * element='func()', name='open' -> false
     * @param element the PSI element to be tested
     * @param name the name this element should have
     * @return true iff the argument meets the requirements
     */
    public boolean isFuncWithName(PsiElement element, String name) {
        if (!(element instanceof PyCallExpression))
        {
            return false;
        }
        PyCallExpression callExpr = (PyCallExpression) element;
        PyExpression callee = callExpr.getCallee();
        if (callee == null)
        {
            return false;
        }
        if (callee.getChildren().length != 0) // handles cases like self.name()
        {
            return false;
        }
        return callExpr.isCalleeText(name);
    }
}
