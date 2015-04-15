package com.instonctools.analyzer.model.marker;

import com.instonctools.analyzer.ui.AnalyzerIcons;

import javax.swing.*;

/**
 * Created by ronn on 14.04.15.
 */
public enum MarkerSeverity {
    MEDIUM(AnalyzerIcons.ICON_16x16_COLOR_M),;

    private final Icon icon;

    private MarkerSeverity(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }
}
