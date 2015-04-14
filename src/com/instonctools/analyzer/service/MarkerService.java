package com.instonctools.analyzer.service;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.model.rule.Rule;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;

/**
 * Created by ronn on 13.04.15.
 */
public interface MarkerService {

    public SecurityMarker buildMarker(Rule rule, PsiMethodCallExpression expression);

    public SecurityMarker findMarkerFor(PsiElement element);

    public void clearMarkersFor(Project project, VirtualFile file);
}
