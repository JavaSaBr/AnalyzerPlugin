package com.instinctools.analyzer.ui.tree.model;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.List;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
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
