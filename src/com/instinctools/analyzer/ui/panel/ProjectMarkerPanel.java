package com.instinctools.analyzer.ui.panel;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.ui.tree.MarkerTree;
import com.instinctools.analyzer.ui.tree.model.MarkerTreeModel;
import com.instinctools.analyzer.ui.tree.model.MarkerTreeNode;
import com.instinctools.analyzer.ui.tree.model.PsiFileTreeNode;
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
 * Documentation follows here.
 */
public class ProjectMarkerPanel extends JPanel {

    private static final long serialVersionUID = -3560970076862223307L;

    private final MarkerTree markerTree;
    private final ToolWindow parent;
    private final Project project;

    public ProjectMarkerPanel(final ToolWindow parent, final Project project) {
        setLayout(new BorderLayout());

        this.project = project;
        this.parent = parent;
        this.markerTree = new MarkerTree(project);

        final JScrollPane treeScrollPane = new JBScrollPane(null);
        treeScrollPane.setViewportView(markerTree);

        add(treeScrollPane, BorderLayout.CENTER);

        final MouseListener mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(final MouseEvent event) {

                final int row = markerTree.getRowForLocation(event.getX(), event.getY());

                if (row == -1) {
                    return;
                }

                final TreePath treePath = markerTree.getPathForLocation(event.getX(), event.getY());

                if (event.getClickCount() == 2) {
                    processOpen(row, treePath);
                }
            }
        };

        markerTree.addMouseListener(mouseListener);
    }

    public void refresh() {
        this.markerTree.setModel(new MarkerTreeModel(project));
    }

    private void processOpen(final int selRow, final TreePath selPath) {

        final Object value = selPath.getLastPathComponent();
        PsiFile file = null;
        SecurityMarker marker = null;

        if (value instanceof MarkerTreeNode) {

            final MarkerTreeNode markerTreeNode = (MarkerTreeNode) value;
            final PsiFileTreeNode node = (PsiFileTreeNode) markerTreeNode.getParent();

            marker = (SecurityMarker) markerTreeNode.getValue();
            file = (PsiFile) node.getValue();

        } else if (value instanceof PsiFileTreeNode) {
            file = (PsiFile) ((PsiFileTreeNode) value).getValue();
        }

        if (file == null) {
            return;
        }

        final VirtualFile virtualFile = file.getVirtualFile();

        final FileEditorProvider[] providers = FileEditorProviderManager.getInstance().getProviders(project, virtualFile);

        if (providers.length < 1) {
            return;
        }

        final OpenFileDescriptor descriptor = new OpenFileDescriptor(project, virtualFile);
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        final Editor editor = fileEditorManager.openTextEditor(descriptor, true);

        if (marker != null) {

            final CaretModel caretModel = editor.getCaretModel();
            caretModel.moveToOffset(marker.getTextRange().getStartOffset());

            final ScrollingModel scrollingModel = editor.getScrollingModel();
            scrollingModel.scrollToCaret(ScrollType.CENTER);
        }
    }

}
