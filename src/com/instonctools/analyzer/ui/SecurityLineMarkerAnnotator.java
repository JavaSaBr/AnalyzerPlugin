package com.instonctools.analyzer.ui;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.model.rule.Rule;
import com.instonctools.analyzer.model.standard.Standard;
import com.instonctools.analyzer.service.MarkerService;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;

import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
 */
public class SecurityLineMarkerAnnotator implements Annotator {

    public SecurityLineMarkerAnnotator() {
        super();
    }

    @Override
    public void annotate(PsiElement element, AnnotationHolder annotationHolder) {

        if (!(element instanceof PsiMethodCallExpression)) {
            return;
        }

        MarkerService markerService = ServiceManager.getService(MarkerService.class);
        SecurityMarker marker = markerService.findMarkerFor(element);

        if (marker == null) {
            return;
        }

        TextRange textRange = marker.getTextRange();
        Rule rule = marker.getRule();

        List<Standard> standards = rule.getStandards();

        final StringBuilder buffer = new StringBuilder();

        for (Standard standard : standards) {
            buffer.append(standard.getShortDescription());
        }

        annotationHolder.createWarningAnnotation(element, buffer.toString());
    }
}
