package com.instinctools.analyzer.service;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.rule.Rule;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public interface MarkerService {

    SecurityMarker buildMarker(Rule rule, PsiMethodCallExpression expression);

    SecurityMarker findMarkerFor(PsiElement element);

    void clearMarkersFor(Project project, VirtualFile file);
}
