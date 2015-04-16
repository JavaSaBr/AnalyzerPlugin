package com.instonctools.analyzer.component;

import com.instonctools.analyzer.util.Utils;
import com.intellij.analysis.AnalysisScope;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiTreeChangeAdapter;
import com.intellij.psi.PsiTreeChangeEvent;

/**
 * Created by ronn on 16.04.15.
 */
public class AnalyzerPsiTreeChangeListener extends PsiTreeChangeAdapter {

    private final AnalyzerProjectComponent projectComponent;

    public AnalyzerPsiTreeChangeListener(AnalyzerProjectComponent projectComponent) {
        this.projectComponent = projectComponent;
    }

    public AnalyzerProjectComponent getProjectComponent() {
        return projectComponent;
    }

    @Override
    public void childAdded(PsiTreeChangeEvent event) {

        PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childRemoved(PsiTreeChangeEvent event) {

        PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childReplaced(PsiTreeChangeEvent event) {

        PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childrenChanged(PsiTreeChangeEvent event) {

        PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childMoved(PsiTreeChangeEvent event) {

        PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }
}
