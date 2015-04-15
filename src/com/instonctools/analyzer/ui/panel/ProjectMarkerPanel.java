package com.instonctools.analyzer.ui.panel;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.ui.tree.MarkerTree;
import com.instonctools.analyzer.ui.tree.model.MarkerTreeNode;
import com.instonctools.analyzer.ui.tree.model.PsiFileTreeNode;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.ScrollingModel;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.fileEditor.ex.FileEditorProviderManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiFile;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ronn on 14.04.15.
 */
public class ProjectMarkerPanel extends JPanel {

    private final MarkerTree markerTree;
    private final ToolWindow parent;
    private final Project project;

    public ProjectMarkerPanel(ToolWindow parent, Project project) {
        setLayout(new BorderLayout());

        this.project = project;
        this.parent = parent;
        this.markerTree = new MarkerTree(project);

        final JScrollPane treeScrollPane = new JBScrollPane(null);
        treeScrollPane.setViewportView(markerTree);

        add(treeScrollPane, BorderLayout.CENTER);

        MouseListener mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent event) {

                int row = markerTree.getRowForLocation(event.getX(), event.getY());

                if (row == -1) {
                    return;
                }

                TreePath treePath = markerTree.getPathForLocation(event.getX(), event.getY());

                if (event.getClickCount() == 2) {
                    processOpen(row, treePath);
                }
            }
        };

        markerTree.addMouseListener(mouseListener);
    }

    private void processOpen(int selRow, TreePath selPath) {

        Object value = selPath.getLastPathComponent();
        PsiFile file = null;
        SecurityMarker marker = null;

        if (value instanceof MarkerTreeNode) {

            MarkerTreeNode markerTreeNode = (MarkerTreeNode) value;
            PsiFileTreeNode node = (PsiFileTreeNode) markerTreeNode.getParent();

            marker = (SecurityMarker) markerTreeNode.getValue();
            file = (PsiFile) node.getValue();

        } else if (value instanceof PsiFileTreeNode) {
            file = (PsiFile) ((PsiFileTreeNode) value).getValue();
        }

        if (file == null) {
            return;
        }

        VirtualFile virtualFile = file.getVirtualFile();

        FileEditorProvider[] providers = FileEditorProviderManager.getInstance().getProviders(project, virtualFile);

        if (providers.length < 1) {
            return;
        }

        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, virtualFile);
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        Editor editor = fileEditorManager.openTextEditor(descriptor, true);

        if (marker != null) {

            CaretModel caretModel = editor.getCaretModel();
            caretModel.moveToOffset(marker.getTextRange().getStartOffset());

            ScrollingModel scrollingModel = editor.getScrollingModel();
            scrollingModel.scrollToCaret(ScrollType.CENTER);
        }
    }

}
