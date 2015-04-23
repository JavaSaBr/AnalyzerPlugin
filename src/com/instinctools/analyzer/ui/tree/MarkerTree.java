package com.instinctools.analyzer.ui.tree;

import com.instinctools.analyzer.ui.tree.model.MarkerTreeModel;
import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class MarkerTree extends JTree {

    private static final long serialVersionUID = -3592103319585562264L;

    private final Project project;

    public MarkerTree(final Project project) {
        super(new MarkerTreeModel(project));

        setCellRenderer(new TreeNodeCellRenderer());

        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
