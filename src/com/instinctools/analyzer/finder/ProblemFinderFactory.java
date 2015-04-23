package com.instinctools.analyzer.finder;

import com.instinctools.analyzer.finder.java.impl.JavaFileProblemFinder;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;

/**
 * Created by ronn on 10.04.15.
 * Documentation follows here.
 */
public class ProblemFinderFactory {

    public static ProblemFinder createFor(final PsiFile psiFile) {

        if (psiFile instanceof PsiJavaFile) {
            return new JavaFileProblemFinder();
        }

        return null;
    }
}
