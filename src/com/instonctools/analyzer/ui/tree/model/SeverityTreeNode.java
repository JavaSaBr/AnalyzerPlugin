package com.instonctools.analyzer.ui.tree.model;

import com.instonctools.analyzer.component.AnalyzerProjectComponent;
import com.instonctools.analyzer.model.marker.MarkerSeverity;
import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.ui.tree.MaskIcon;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
 */
public class SeverityTreeNode extends AbstractTreeNode {

    private MaskIcon icon;

    private Project project;

    public SeverityTreeNode(Project project, MarkerSeverity severity, AbstractTreeNode parent) {
        super(severity, parent);
        this.project = project;
        this.icon = new MaskIcon(severity.getIcon(), JBColor.BLACK);
    }

    @Override
    public void loadChilds() {

        AnalyzerProjectComponent analyzerProjectComponent = project.getComponent(AnalyzerProjectComponent.class);
        List<SecurityMarker> markers = analyzerProjectComponent.getMarkers();

        Map<PsiDirectory, List<SecurityMarker>> roots = new HashMap<PsiDirectory, List<SecurityMarker>>();

        PsiManager psiManager = PsiManager.getInstance(project);

        for (SecurityMarker marker : markers) {

            PsiFile psiFile = psiManager.findFile(marker.getFile());
            Module module = ModuleUtil.findModuleForFile(marker.getFile(), project);

            VirtualFile baseDir = project.getBaseDir();

            if (module != null) {
                baseDir = module.getModuleFile().getParent();
            }

            PsiDirectory root = psiFile.getParent();

            while (root.getParent() != null) {

                root = root.getParent();

                if (baseDir.equals(root.getVirtualFile())) {
                    break;
                }
            }

            List<SecurityMarker> rootMarkers = roots.get(root);

            if (rootMarkers == null) {
                rootMarkers = new ArrayList<SecurityMarker>();
                roots.put(root, rootMarkers);
            }

            rootMarkers.add(marker);
        }

        List<AbstractTreeNode> children = getChildren();

        for (Map.Entry<PsiDirectory, List<SecurityMarker>> entry : roots.entrySet()) {

            PsiDirectory root = entry.getKey();

            PsiDirectoryTreeNode child = new PsiDirectoryTreeNode(project, root, entry.getValue(), this);
            child.loadChilds();

            children.add(child);
        }
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public String getSimpleName() {
        return getValue().toString();
    }

    @Override
    public String getTooltip() {
        return "Severity " + getSimpleName();
    }

    @Override
    public Icon getExpandedIcon() {
        return icon;
    }

    @Override
    public Icon getCollapsedIcon() {
        return icon;
    }
}
