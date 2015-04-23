package com.instinctools.analyzer.ui.tree;

import com.instinctools.analyzer.ui.tree.model.MarkerTreeModel;
import com.intellij.openapi.project.Project;

import javax.swing.*;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
 */
public class MarkerTree extends JTree {

    private Project project;

    public MarkerTree(Project project) {
        super(new MarkerTreeModel(project));

        setCellRenderer(new TreeNodeCellRenderer());

        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
