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
 * Documentation follows here.
 */
public class SecurityLineMarkerProvider implements LineMarkerProvider {

    public SecurityLineMarkerProvider() {
        super();
    }

    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(final PsiElement element) {

        if (!(element instanceof PsiMethodCallExpression)) {
            return null;
        }

        final MarkerService markerService = ServiceManager.getService(MarkerService.class);
        final SecurityMarker marker = markerService.findMarkerFor(element);

        if (marker == null) {
            return null;
        }

        final TextRange textRange = marker.getTextRange();
        final Icon icon = AnalyzerIcons.ICON_16x16_COLOR_M;
        final TooltipProvider tooltipProvider = new TooltipProvider(marker);
        final IconNavigatorHandler navigatorHandler = new IconNavigatorHandler(marker, element);

        final LineMarkerInfo<PsiElement> markerInfo = new LineMarkerInfo<PsiElement>(element, textRange, icon, 4, tooltipProvider, navigatorHandler, GutterIconRenderer.Alignment.LEFT);
        return markerInfo;
    }

    @Override
    public void collectSlowLineMarkers(final List<PsiElement> list, final Collection<LineMarkerInfo> collection) {

        final int i = 0;

        for (final PsiElement element : list) {

            final LineMarkerInfo markerInfo = getLineMarkerInfo(element);

            if (markerInfo == null) {
                continue;
            }

            collection.add(markerInfo);
        }
    }
}
