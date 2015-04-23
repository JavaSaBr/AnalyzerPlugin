package com.instinctools.analyzer.util;

import com.instinctools.analyzer.ui.panel.ProjectMarkerPanel;
import com.instinctools.analyzer.visiter.PsiFileVisitor;
import com.intellij.analysis.AnalysisScope;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public class Utils {

    private static final int[] EXCEPT_OVERRIDDEN = new int[]{1, 3, 4, 7, 12, 8};

    public static void startAnalyze(final AnalysisScope analysisScope, final Project project, final boolean needRefresh) {

        final AnalysisScope toAnalyze = analysisScope;
        final Task.Backgroundable task = new Task.Backgroundable(project, "Analyzing security problems...") {

            @Override
            public void run(ProgressIndicator progressIndicator) {

                progressIndicator.setText("Analyzing...");

                Application application = ApplicationManager.getApplication();
                application.runReadAction(new Runnable() {

                    @Override
                    public void run() {
                        doAnalyze(toAnalyze, project, needRefresh);
                    }
                });
            }
        };
        task.queue();
    }

    private static void doAnalyze(AnalysisScope scope, final Project project, final boolean needRefresh) {

        synchronized (project) {
            scope.accept(new PsiFileVisitor());
        }

        Application application = ApplicationManager.getApplication();
        application.invokeLater(new Runnable() {

            @Override
            public void run() {
                updateEditors(project, needRefresh);
            }
        });
    }

    private static void updateEditors(final Project project, final boolean needRefresh) {

        if (needRefresh) {
            DaemonCodeAnalyzer codeAnalyzer = DaemonCodeAnalyzer.getInstance(project);
            codeAnalyzer.restart();
        }

        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.getToolWindow("Project Markers");

        if (toolWindow == null) {
            return;
        }

        JComponent component = toolWindow.getComponent();

        for (int i = 0, length = component.getComponentCount(); i < length; i++) {

            Component children = component.getComponent(i);

            if (!(children instanceof ProjectMarkerPanel)) {
                continue;
            }

            ProjectMarkerPanel markerPanel = (ProjectMarkerPanel) children;
            markerPanel.refresh();
        }
    }
}
