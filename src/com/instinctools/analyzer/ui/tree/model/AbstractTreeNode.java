package com.instinctools.analyzer.ui.tree.model;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class AbstractTreeNode implements TreeNode {

    private final List<AbstractTreeNode> children;

    private final AbstractTreeNode parent;

    private final Object value;

    public AbstractTreeNode(final Object value, final AbstractTreeNode parent) {
        this.children = new ArrayList<AbstractTreeNode>();
        this.value = value;
        this.parent = parent;
    }

    public Object getValue() {
        return value;
    }

    public void loadChilds() {
        // load childs if need
    }

    public List<AbstractTreeNode> getChildren() {
        return children;
    }

    @Override
    public TreeNode getChildAt(final int childIndex) {
        return children == null ? null : children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children == null ? 0 : children.size();
    }

    @Override
    public AbstractTreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(final TreeNode node) {
        return children == null ? 0 : children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return children != null && !children.isEmpty();
    }

    @Override
    public boolean isLeaf() {
        return children == null || children.isEmpty();
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(children);
    }

    public Icon getExpandedIcon() {
        return null;
    }

    public Icon getCollapsedIcon() {
        return null;
    }

    public String getTooltip() {
        return null;
    }

    public String getSimpleName() {
        return null;
    }
}
