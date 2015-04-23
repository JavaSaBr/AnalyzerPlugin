package com.instinctools.analyzer.ui.tree.model;

import com.intellij.openapi.project.Project;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
 */
public class MarkerTreeModel implements TreeModel {

    private Project project;

    private AbstractTreeNode root;

    public MarkerTreeModel(Project project) {
        this.project = project;
        this.root = new ProjectTreeNode(project);
        this.root.loadChilds();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {

        if (parent instanceof AbstractTreeNode) {
            return ((AbstractTreeNode) parent).getChildAt(index);
        }

        return null;
    }

    @Override
    public int getChildCount(Object parent) {

        if (parent instanceof AbstractTreeNode) {
            return ((AbstractTreeNode) parent).getChildCount();
        }

        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {

        if (node instanceof AbstractTreeNode) {
            return ((AbstractTreeNode) node).isLeaf();
        }

        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return 0;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {

    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {

    }
}
