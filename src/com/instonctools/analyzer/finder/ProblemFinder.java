package com.instonctools.analyzer.finder;

import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 */
public interface ProblemFinder {

    public void find(PsiFile psiFile);
}
