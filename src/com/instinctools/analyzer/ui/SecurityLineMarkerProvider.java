package com.instinctools.analyzer.ui;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.service.MarkerService;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethodCallExpression;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
 */
public class SecurityLineMarkerProvider implements LineMarkerProvider {

    public SecurityLineMarkerProvider() {
        super();
    }

    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(PsiElement element) {

        if (!(element instanceof PsiMethodCallExpression)) {
            return null;
        }

        MarkerService markerService = ServiceManager.getService(MarkerService.class);
        SecurityMarker marker = markerService.findMarkerFor(element);

        if (marker == null) {
            return null;
        }

        TextRange textRange = marker.getTextRange();
        Icon icon = AnalyzerIcons.ICON_16x16_COLOR_M;
        TooltipProvider tooltipProvider = new TooltipProvider(marker);
        IconNavigatorHandler navigatorHandler = new IconNavigatorHandler(marker, element);

        LineMarkerInfo<PsiElement> markerInfo = new LineMarkerInfo<PsiElement>(element, textRange, icon, 4, tooltipProvider, navigatorHandler, GutterIconRenderer.Alignment.LEFT);
        return markerInfo;
    }

    @Override
    public void collectSlowLineMarkers(List<PsiElement> list, Collection<LineMarkerInfo> collection) {

        int i = 0;

        for (PsiElement element : list) {

            LineMarkerInfo markerInfo = getLineMarkerInfo(element);

            if (markerInfo == null) {
                continue;
            }

            collection.add(markerInfo);
        }
    }
}