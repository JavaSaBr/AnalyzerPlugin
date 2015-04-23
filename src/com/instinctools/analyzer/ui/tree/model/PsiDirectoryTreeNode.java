package com.instinctools.analyzer.ui.tree.model;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.ui.tree.MaskIcon;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class PsiDirectoryTreeNode extends AbstractTreeNode {

    private static final Icon EXPAND_ICON = new MaskIcon(AllIcons.Nodes.Package, JBColor.BLACK);
    private static final Icon COLLAPSE_ICON = EXPAND_ICON;

    private final Project project;

    private final List<SecurityMarker> markers;

    public PsiDirectoryTreeNode(final Project project, final PsiDirectory directory, final List<SecurityMarker> markers, final AbstractTreeNode parent) {
        super(directory, parent);

        this.project = project;
        this.markers = markers;
    }

    @Override
    public void loadChilds() {

        final Map<PsiDirectory, List<SecurityMarker>> directories = new HashMap<PsiDirectory, List<SecurityMarker>>();
        final Map<PsiFile, List<SecurityMarker>> files = new HashMap<PsiFile, List<SecurityMarker>>();

        final PsiManager psiManager = PsiManager.getInstance(project);

        final PsiDirectory root = (PsiDirectory) getValue();

        for (final SecurityMarker marker : markers) {

            final PsiFile psiFile = psiManager.findFile(marker.getFile());

            if (psiFile == null) {
                continue;
            }

            PsiDirectory parent = psiFile.getParent();

            if (parent == null) {
                continue;
            }

            if (root.equals(parent)) {

                List<SecurityMarker> fileMarkers = files.get(psiFile);

                if (fileMarkers == null) {
                    fileMarkers = new ArrayList<SecurityMarker>();
                    files.put(psiFile, fileMarkers);
                }

                fileMarkers.add(marker);
                continue;
            }

            while (parent.getParent() != null) {

                if (root.equals(parent.getParent())) {
                    break;
                }

                parent = parent.getParent();
            }

            List<SecurityMarker> directoryMarkers = directories.get(parent);

            if (directoryMarkers == null) {
                directoryMarkers = new ArrayList<SecurityMarker>();
                directories.put(parent, directoryMarkers);
            }

            directoryMarkers.add(marker);
        }

        final List<AbstractTreeNode> children = getChildren();

        for (final Map.Entry<PsiDirectory, List<SecurityMarker>> entry : directories.entrySet()) {

            final PsiDirectory parent = entry.getKey();

            final PsiDirectoryTreeNode child = new PsiDirectoryTreeNode(project, parent, entry.getValue(), this);
            child.loadChilds();

            children.add(child);
        }

        for (final Map.Entry<PsiFile, List<SecurityMarker>> entry : files.entrySet()) {

            final PsiFile file = entry.getKey();

            final PsiFileTreeNode child = new PsiFileTreeNode(project, file, entry.getValue(), this);
            child.loadChilds();

            children.add(child);
        }
    }

    @Override
    public String getSimpleName() {
        return ((PsiDirectory) getValue()).getName();
    }

    @Override
    public String getTooltip() {
        return "Package " + getSimpleName();
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