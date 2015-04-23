package com.instinctools.analyzer.component;

import com.instinctools.analyzer.util.Utils;
import com.intellij.analysis.AnalysisScope;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiTreeChangeAdapter;
import com.intellij.psi.PsiTreeChangeEvent;

/**
 * Created by ronn on 16.04.15.
 * Documentation follows here.
 */
public class AnalyzerPsiTreeChangeListener extends PsiTreeChangeAdapter {

    private final AnalyzerProjectComponent projectComponent;

    public AnalyzerPsiTreeChangeListener(final AnalyzerProjectComponent projectComponent) {
        this.projectComponent = projectComponent;
    }

    public AnalyzerProjectComponent getProjectComponent() {
        return projectComponent;
    }

    @Override
    public void childAdded(final PsiTreeChangeEvent event) {

        final PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childRemoved(final PsiTreeChangeEvent event) {

        final PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childReplaced(final PsiTreeChangeEvent event) {

        final PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childrenChanged(final PsiTreeChangeEvent event) {

        final PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }

    @Override
    public void childMoved(final PsiTreeChangeEvent event) {

        final PsiFile file = event.getFile();

        if (!(file instanceof PsiJavaFile)) {
            return;
        } else if (!projectComponent.hasMarkersFor(file)) {
            return;
        }

        Utils.startAnalyze(new AnalysisScope(file), file.getProject(), false);
    }
}
