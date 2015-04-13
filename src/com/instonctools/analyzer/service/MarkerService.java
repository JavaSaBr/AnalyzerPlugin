package com.instonctools.analyzer.service;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.model.rule.Rule;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiMethodCallExpression;

/**
 * Created by ronn on 13.04.15.
 */
public interface MarkerService {

    public SecurityMarker buildMarker(Rule rule, Module module, PsiMethodCallExpression expression);
}
