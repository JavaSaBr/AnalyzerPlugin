package com.instonctools.analyzer.ui.actions;

import com.instonctools.analyzer.visiter.PsiFileVisiter;
import com.intellij.analysis.AnalysisScope;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * Created by ronn on 10.04.15.
 */
public class StartAnalyzeAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {

        PsiElement element = event.getData(LangDataKeys.PSI_ELEMENT);
        AnalysisScope analysisScope = null;

        if (element instanceof PsiFile) {
            analysisScope = new AnalysisScope((PsiFile) element);
        } else if (element instanceof PsiDirectory) {
            analysisScope = new AnalysisScope((PsiDirectory) element);
        }

        if (analysisScope == null) {
            return;
        }

        analysisScope.accept(new PsiFileVisiter());

        FileEditorManager editorManager = FileEditorManager.getInstance(element.getProject());
        FileEditor[] editors = editorManager.getAllEditors();

        for (FileEditor editor : editors) {
            editor.selectNotify();
        }
    }
}
