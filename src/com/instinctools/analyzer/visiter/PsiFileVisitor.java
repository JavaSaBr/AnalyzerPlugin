package com.instinctools.analyzer.visiter;

import com.instinctools.analyzer.finder.ProblemFinder;
import com.instinctools.analyzer.finder.ProblemFinderFactory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
public class PsiFileVisitor extends PsiElementVisitor {

    @Override
    public void visitElement(PsiElement element) {
        super.visitElement(element);

        for (PsiElement child : element.getChildren()) {
            child.accept(this);
        }

        if (!(element instanceof PsiFile)) {
            return;
        }

        PsiFile psiFile = (PsiFile) element;
        ProblemFinder finder = ProblemFinderFactory.createFor(psiFile);

        if (finder != null) {
            finder.find(psiFile);
        }
    }
}
