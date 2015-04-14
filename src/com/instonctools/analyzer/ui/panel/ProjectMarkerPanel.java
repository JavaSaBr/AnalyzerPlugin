package com.instonctools.analyzer.ui.panel;

import com.instonctools.analyzer.ui.tree.MarkerTree;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ronn on 14.04.15.
 */
public class ProjectMarkerPanel extends JPanel {

    private final ToolWindow parent;

    private final Project project;

    public ProjectMarkerPanel(ToolWindow parent, Project project) {
        setLayout(new BorderLayout());

        this.project = project;
        this.parent = parent;

        final JScrollPane treeScrollPane = new JBScrollPane(null);
        treeScrollPane.setViewportView(new MarkerTree(project));

        add(treeScrollPane, BorderLayout.CENTER);
    }
}
