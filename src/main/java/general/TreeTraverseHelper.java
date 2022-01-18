package general;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl;

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
}
