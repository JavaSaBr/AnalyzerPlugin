package com.instinctools.analyzer.ui.tree.model;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.ui.tree.MaskIcon;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class PsiFileTreeNode extends AbstractTreeNode {

    private static final Icon EXPAND_ICON = new MaskIcon(AllIcons.Nodes.Class, JBColor.BLACK);
    private static final Icon COLLAPSE_ICON = EXPAND_ICON;

    private final List<SecurityMarker> markers;

    public PsiFileTreeNode(final Project project, final PsiFile file, final List<SecurityMarker> markers, final AbstractTreeNode parent) {
        super(file, parent);

        this.markers = markers;
    }

    @Override
    public void loadChilds() {

        final List<AbstractTreeNode> children = getChildren();

        for (final SecurityMarker marker : markers) {

            final MarkerTreeNode child = new MarkerTreeNode(marker, ((PsiFile) getValue()).getProject(), this);
            child.loadChilds();

            children.add(child);
        }
    }

    @Override
    public String getSimpleName() {
        return ((PsiFile) getValue()).getName();
    }

    @Override
    public String getTooltip() {
        return "Class " + getSimpleName();
    }

    @Override
    public Icon getCollapsedIcon() {
        return COLLAPSE_ICON;
    }

    @Override
    public Icon getExpandedIcon() {
        return EXPAND_ICON;
    }
}