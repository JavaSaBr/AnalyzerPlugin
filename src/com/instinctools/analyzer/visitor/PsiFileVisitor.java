package com.instinctools.analyzer.visitor;

import com.instinctools.analyzer.finder.ProblemFinder;
import com.instinctools.analyzer.finder.ProblemFinderFactory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 * Documentation follows here.
 */
public class PsiFileVisitor extends PsiElementVisitor {

    @Override
    public void visitElement(final PsiElement element) {
        super.visitElement(element);

        for (final PsiElement child : element.getChildren()) {
            child.accept(this);
        }

        if (!(element instanceof PsiFile)) {
            return;
        }

        final PsiFile psiFile = (PsiFile) element;
        final ProblemFinder finder = ProblemFinderFactory.createFor(psiFile);

        if (finder != null) {
            finder.find(psiFile);
        }
    }
}
