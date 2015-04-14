package com.instonctools.analyzer.ui;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.psi.PsiElement;

import java.awt.event.MouseEvent;

/**
 * Created by ronn on 14.04.15.
 */
public class IconNavigatorHandler implements GutterIconNavigationHandler<PsiElement> {

    private final PsiElement element;

    public IconNavigatorHandler(final PsiElement element) {
        this.element = element;
    }

    @Override
    public void navigate(final MouseEvent event, final PsiElement lement) {
        //TODO
    }
}