package com.instinctools.analyzer.service.impl;

import com.instinctools.analyzer.component.AnalyzerProjectComponent;
import com.instinctools.analyzer.model.marker.MutableSecurityMarker;
import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.marker.SecurityMarkerFactory;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.service.MarkerService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.List;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public class MarkerServiceImpl implements MarkerService {

    @Override
    public SecurityMarker buildMarker(Rule rule, PsiMethodCallExpression expression) {

        Project project = expression.getProject();
        PsiFile containingFile = expression.getContainingFile();

        MutableSecurityMarker marker = (MutableSecurityMarker) SecurityMarkerFactory.create();
        marker.setFile(containingFile.getVirtualFile());
        marker.setTextRange(expression.getTextRange());
        marker.setRule(rule);

        AnalyzerProjectComponent analyzerComponent = project.getComponent(AnalyzerProjectComponent.class);
        analyzerComponent.addMarker(marker);

        return marker;
    }

    @Override
    public SecurityMarker findMarkerFor(PsiElement element) {

        Project project = element.getProject();
        AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        if (markers.isEmpty()) {
            return null;
        }

        PsiFile containingFile = element.getContainingFile();
        VirtualFile file = containingFile.getVirtualFile();

        for (SecurityMarker marker : markers) {

            VirtualFile markedFile = marker.getFile();

            if (!file.equals(markedFile)) {
                continue;
            }

            TextRange textRange = marker.getTextRange();

            if (element.getTextOffset() != textRange.getStartOffset()) {
                continue;
            }

            return marker;
        }

        return null;
    }

    @Override
    public void clearMarkersFor(Project project, VirtualFile file) {

        AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        if (markers.isEmpty()) {
            return;
        }

        for (SecurityMarker marker : markers) {

            if (!file.equals(marker.getFile())) {
                continue;
            }

            analyzerProjectComponent.removeMarker(marker);
        }
    }
}
