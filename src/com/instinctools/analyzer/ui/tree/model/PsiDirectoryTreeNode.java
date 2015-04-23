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
 * //TODO need add documentation
 */
public class PsiDirectoryTreeNode extends AbstractTreeNode {

    private static final Icon EXPAND_ICON = new MaskIcon(AllIcons.Nodes.Package, JBColor.BLACK);
    private static final Icon COLLAPSE_ICON = EXPAND_ICON;

    private Project project;

    private List<SecurityMarker> markers;

    public PsiDirectoryTreeNode(Project project, PsiDirectory directory, List<SecurityMarker> markers, AbstractTreeNode parent) {
        super(directory, parent);

        this.project = project;
        this.markers = markers;
    }

    @Override
    public void loadChilds() {

        Map<PsiDirectory, List<SecurityMarker>> directories = new HashMap<PsiDirectory, List<SecurityMarker>>();
        Map<PsiFile, List<SecurityMarker>> files = new HashMap<PsiFile, List<SecurityMarker>>();

        PsiManager psiManager = PsiManager.getInstance(project);

        PsiDirectory root = (PsiDirectory) getValue();

        for (SecurityMarker marker : markers) {

            PsiFile psiFile = psiManager.findFile(marker.getFile());
            PsiDirectory parent = psiFile.getParent();

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

        List<AbstractTreeNode> children = getChildren();

        for (Map.Entry<PsiDirectory, List<SecurityMarker>> entry : directories.entrySet()) {

            PsiDirectory parent = entry.getKey();

            PsiDirectoryTreeNode child = new PsiDirectoryTreeNode(project, parent, entry.getValue(), this);
            child.loadChilds();

            children.add(child);
        }

        for (Map.Entry<PsiFile, List<SecurityMarker>> entry : files.entrySet()) {

            PsiFile file = entry.getKey();

            PsiFileTreeNode child = new PsiFileTreeNode(project, file, entry.getValue(), this);
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