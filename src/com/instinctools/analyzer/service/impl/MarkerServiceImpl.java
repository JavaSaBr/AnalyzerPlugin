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
 * Documentation follows here.
 */
public class MarkerServiceImpl implements MarkerService {

    @Override
    public SecurityMarker buildMarker(final Rule rule, final PsiMethodCallExpression expression) {

        final Project project = expression.getProject();
        final PsiFile containingFile = expression.getContainingFile();

        final MutableSecurityMarker marker = (MutableSecurityMarker) SecurityMarkerFactory.create();
        marker.setFile(containingFile.getVirtualFile());
        marker.setTextRange(expression.getTextRange());
        marker.setRule(rule);

        final AnalyzerProjectComponent analyzerComponent = project.getComponent(AnalyzerProjectComponent.class);
        analyzerComponent.addMarker(marker);

        return marker;
    }

    @Override
    public SecurityMarker findMarkerFor(final PsiElement element) {

        final Project project = element.getProject();
        final AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        final List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        if (markers.isEmpty()) {
            return null;
        }

        final PsiFile containingFile = element.getContainingFile();
        final VirtualFile file = containingFile.getVirtualFile();

        for (SecurityMarker marker : markers) {

            final VirtualFile markedFile = marker.getFile();

            if (!file.equals(markedFile)) {
                continue;
            }

            final TextRange textRange = marker.getTextRange();

            if (element.getTextOffset() != textRange.getStartOffset()) {
                continue;
            }

            return marker;
        }

        return null;
    }

    @Override
    public void clearMarkersFor(final Project project, final VirtualFile file) {

        final AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        final List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        if (markers.isEmpty()) {
            return;
        }

        for (final SecurityMarker marker : markers) {

            if (!file.equals(marker.getFile())) {
                continue;
            }

            analyzerProjectComponent.removeMarker(marker);
        }
    }
}
