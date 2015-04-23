package com.instinctools.analyzer.ui.tree.model;

import com.intellij.openapi.project.Project;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class MarkerTreeModel implements TreeModel {

    private final AbstractTreeNode root;

    public MarkerTreeModel(final Project project) {
        this.root = new ProjectTreeNode(project);
        this.root.loadChilds();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(final Object parent, final int index) {

        if (parent instanceof AbstractTreeNode) {
            return ((AbstractTreeNode) parent).getChildAt(index);
        }

        return null;
    }

    @Override
    public int getChildCount(final Object parent) {

        if (parent instanceof AbstractTreeNode) {
            return ((AbstractTreeNode) parent).getChildCount();
        }

        return 0;
    }

    @Override
    public boolean isLeaf(final Object node) {

        if (node instanceof AbstractTreeNode) {
            return ((AbstractTreeNode) node).isLeaf();
        }

        return false;
    }

    @Override
    public void valueForPathChanged(final TreePath path, final Object newValue) {

    }

    @Override
    public int getIndexOfChild(final Object parent, final Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(final TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(final TreeModelListener l) {

    }
}
