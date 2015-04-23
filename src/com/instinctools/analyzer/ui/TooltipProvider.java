package com.instinctools.analyzer.ui;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.model.standard.Standard;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;

import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class TooltipProvider implements Function<PsiElement, String> {

    private final SecurityMarker marker;

    public TooltipProvider(final SecurityMarker marker) {
        this.marker = marker;
    }

    @Override
    public String fun(final PsiElement psiElement) {
        return getTooltipText();
    }

    public String getTooltipText() {

        final Rule rule = marker.getRule();
        final List<Standard> standards = rule.getStandards();

        final StringBuilder buffer = new StringBuilder();
        buffer.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        buffer.append("<HTML><BODY>");

        for (final Standard standard : standards) {
            buffer.append(standard.getShortDescription());
        }

        buffer.append("</BODY></HTML>");

        return buffer.toString();
    }
}