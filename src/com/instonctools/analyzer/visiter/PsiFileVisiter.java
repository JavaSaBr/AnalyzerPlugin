package com.instonctools.analyzer.visiter;

import com.instonctools.analyzer.finder.ProblemFinder;
import com.instonctools.analyzer.finder.ProblemFinderFactory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 */
public class PsiFileVisiter extends PsiElementVisitor {

    @Override
    public void visitElement(PsiElement element) {
        super.visitElement(element);

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
