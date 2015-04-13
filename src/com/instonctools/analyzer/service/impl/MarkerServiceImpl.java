package com.instonctools.analyzer.service.impl;

import com.instonctools.analyzer.component.AnalyzerProjectComponent;
import com.instonctools.analyzer.model.marker.MutableSecurityMarker;
import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.model.marker.SecurityMarkerFactory;
import com.instonctools.analyzer.model.rule.Rule;
import com.instonctools.analyzer.service.MarkerService;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethodCallExpression;

/**
 * Created by ronn on 13.04.15.
 */
public class MarkerServiceImpl implements MarkerService {

    @Override
    public SecurityMarker buildMarker(Rule rule, Module module, PsiMethodCallExpression expression) {

        PsiFile containingFile = expression.getContainingFile();

        MutableSecurityMarker marker = (MutableSecurityMarker) SecurityMarkerFactory.create();
        marker.setFile(containingFile.getVirtualFile());
        marker.setRule(rule);
        marker.setTextRange(expression.getTextRange());

        AnalyzerProjectComponent analyzerComponent = module.getProject().getComponent(AnalyzerProjectComponent.class);
        analyzerComponent.addMarker(marker);

        return marker;
    }
}
