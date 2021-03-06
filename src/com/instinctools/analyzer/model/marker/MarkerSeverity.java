package com.instinctools.analyzer.model.marker;

import com.instinctools.analyzer.ui.AnalyzerIcons;

import javax.swing.*;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public enum MarkerSeverity {
    MEDIUM(AnalyzerIcons.ICON_16x16_COLOR_M),;

    private final Icon icon;

    MarkerSeverity(final Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }
}
