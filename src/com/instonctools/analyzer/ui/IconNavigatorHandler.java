package com.instonctools.analyzer.ui;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.ui.popup.MarkerDescriptionPopupHelper;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.psi.PsiElement;

import java.awt.event.MouseEvent;

/**
 * Created by ronn on 14.04.15.
 */
public class IconNavigatorHandler implements GutterIconNavigationHandler<PsiElement> {

    private final PsiElement element;

    private final SecurityMarker marker;

    public IconNavigatorHandler(final SecurityMarker marker, final PsiElement element) {
        this.element = element;
        this.marker = marker;
    }

    @Override
    public void navigate(final MouseEvent event, final PsiElement lement) {
        MarkerDescriptionPopupHelper.show(marker);
    }
}