package com.instonctools.analyzer.ui.tree.model;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.List;

/**
 * Created by ronn on 14.04.15.
 */
public class FileTreeNode extends AbstractTreeNode {

    private Project project;

    private List<SecurityMarker> markers;

    public FileTreeNode(Project project, VirtualFile file, List<SecurityMarker> markers, AbstractTreeNode parent) {
        super(file, parent);

        this.project = project;
        this.markers = markers;
    }

    @Override
    public void loadChilds() {

    }
}
