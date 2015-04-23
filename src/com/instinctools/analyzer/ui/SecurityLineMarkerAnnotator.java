package com.instinctools.analyzer.ui;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.model.standard.Standard;
import com.instinctools.analyzer.service.MarkerService;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class SecurityLineMarkerAnnotator implements Annotator {

    public SecurityLineMarkerAnnotator() {
        super();
    }

    @Override
    public void annotate(final PsiElement element, final AnnotationHolder annotationHolder) {

        if (!(element instanceof PsiMethodCallExpression)) {
            return;
        }

        final MarkerService markerService = ServiceManager.getService(MarkerService.class);
        final SecurityMarker marker = markerService.findMarkerFor(element);

        if (marker == null) {
            return;
        }

        final Rule rule = marker.getRule();

        final List<Standard> standards = rule.getStandards();

        final StringBuilder buffer = new StringBuilder();

        for (final Standard standard : standards) {
            buffer.append(standard.getShortDescription());
        }

        annotationHolder.createWarningAnnotation(element, buffer.toString());
    }
}
