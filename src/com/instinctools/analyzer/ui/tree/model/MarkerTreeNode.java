package com.instinctools.analyzer.ui.tree.model;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.ui.AnalyzerIcons;
import com.instinctools.analyzer.ui.tree.MaskIcon;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.ui.JBColor;

import javax.swing.*;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class MarkerTreeNode extends AbstractTreeNode {

    private static final Icon EXPAND_ICON = new MaskIcon(AnalyzerIcons.ICON_16x16_COLOR_M, JBColor.BLACK);
    private static final Icon COLLAPSE_ICON = EXPAND_ICON;

    private final Project project;

    private int line;

    public MarkerTreeNode(final SecurityMarker marker, final Project project, final AbstractTreeNode parent) {
        super(marker, parent);

        this.project = project;
    }

    @Override
    public void loadChilds() {

        final SecurityMarker marker = (SecurityMarker) getValue();
        final TextRange textRange = marker.getTextRange();

        final FileDocumentManager documentManager = FileDocumentManager.getInstance();
        final Document document = documentManager.getDocument(marker.getFile());

        if (document == null) {
            return;
        }

        this.line = document.getLineNumber(textRange.getStartOffset());
    }

    public Project getProject() {
        return project;
    }

    @Override
    public String toString() {
        return "Line : " + line;
    }

    @Override
    public String getSimpleName() {
        return "Line : " + (line + 1);
    }

    @Override
    public String getTooltip() {
        return "Security marker -> " + getSimpleName();
    }

    @Override
    public Icon getExpandedIcon() {
        return EXPAND_ICON;
    }

    @Override
    public Icon getCollapsedIcon() {
        return COLLAPSE_ICON;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
