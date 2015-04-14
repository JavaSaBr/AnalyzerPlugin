package com.instonctools.analyzer.ui;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;

/**
 * Created by ronn on 14.04.15.
 */
public class TooltipProvider implements Function<PsiElement, String> {

    private SecurityMarker marker;

    public TooltipProvider(SecurityMarker marker) {
        this.marker = marker;
    }

    @Override
    public String fun(final PsiElement psiElement) {
        return getTooltipText();
    }

    public String getTooltipText() {

        final StringBuilder buffer = new StringBuilder();
        buffer.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        buffer.append("<HTML><HEAD>");
        buffer.append("fWEFEFWEFWEWEFA EFAE FEGAE G");
        buffer.append("</BODY></HTML>");

        return buffer.toString();
    }
}