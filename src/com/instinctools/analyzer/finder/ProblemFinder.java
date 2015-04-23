package com.instinctools.analyzer.finder;


import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 * Documentation follows here.
 */
public interface ProblemFinder {

    void find(PsiFile psiFile);
}
