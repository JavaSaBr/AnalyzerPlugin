package com.instinctools.analyzer.ui.tree.model;

import com.instinctools.analyzer.component.AnalyzerProjectComponent;
import com.instinctools.analyzer.model.marker.MarkerSeverity;
import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.ui.tree.MaskIcon;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class ProjectTreeNode extends AbstractTreeNode {

    private static final Icon EXPAND_ICON = new MaskIcon(AllIcons.Nodes.Project, JBColor.BLACK);
    private static final Icon COLLAPSE_ICON = EXPAND_ICON;

    public ProjectTreeNode(final Project project) {
        super(project, null);
    }

    @Override
    public void loadChilds() {

        final Project project = (Project) getValue();

        final SeverityTreeNode node = new SeverityTreeNode(project, MarkerSeverity.MEDIUM, this);
        node.loadChilds();

        final List<AbstractTreeNode> children = getChildren();
        children.add(node);
    }

    @Override
    public String getSimpleName() {
        return ((Project) getValue()).getName();
    }

    @Override
    public String getTooltip() {
        return "Project " + getSimpleName();
    }

    public int getClassesCount() {

        final Project project = (Project) getValue();
        final AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        final List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        final Set<VirtualFile> files = new HashSet<VirtualFile>();

        for (final SecurityMarker marker : markers) {
            files.add(marker.getFile());
        }

        return files.size();
    }

    public int getMarkerCount() {

        final Project project = (Project) getValue();
        final AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        final List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        return markers.size();
    }

    public String getLinkHtml() {
        return "";
    }

    @Override
    public Icon getExpandedIcon() {
        return EXPAND_ICON;
    }

    @Override
    public Icon getCollapsedIcon() {
        return COLLAPSE_ICON;
    }
}
