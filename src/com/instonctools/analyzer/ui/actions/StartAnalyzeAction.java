package com.instonctools.analyzer.ui.actions;

import com.instonctools.analyzer.util.Utils;
import com.intellij.analysis.AnalysisScope;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
public class StartAnalyzeAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {

        PsiElement element = event.getData(LangDataKeys.PSI_ELEMENT);
        AnalysisScope analysisScope = null;

        if (element == null) {
            element = event.getData(LangDataKeys.PSI_FILE);
        }

        if (element instanceof PsiFile) {
            analysisScope = new AnalysisScope((PsiFile) element);
        } else if (element instanceof PsiDirectory) {
            analysisScope = new AnalysisScope((PsiDirectory) element);
        } else if (element instanceof PsiClass) {
            analysisScope = new AnalysisScope(element.getContainingFile());
        }

        if (analysisScope == null) {
            return;
        }

        Utils.startAnalyze(analysisScope, element.getProject(), true);
    }
}
