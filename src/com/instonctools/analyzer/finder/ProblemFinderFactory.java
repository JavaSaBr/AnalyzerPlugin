package com.instonctools.analyzer.finder;

import com.instonctools.analyzer.finder.impl.JavaFileProblemFinder;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;

/**
 * Created by ronn on 10.04.15.
 */
public class ProblemFinderFactory {

    public static ProblemFinder createFor(PsiFile psiFile) {

        if (psiFile instanceof PsiJavaFile) {
            return new JavaFileProblemFinder();
        }

        return null;
    }
}
