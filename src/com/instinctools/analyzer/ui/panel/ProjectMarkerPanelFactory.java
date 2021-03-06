package com.instinctools.analyzer.ui.panel;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import javax.swing.*;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class ProjectMarkerPanelFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(final Project project, final ToolWindow toolWindow) {
        final JComponent component = toolWindow.getComponent();
        component.add(new ProjectMarkerPanel(toolWindow, project));
    }
}
