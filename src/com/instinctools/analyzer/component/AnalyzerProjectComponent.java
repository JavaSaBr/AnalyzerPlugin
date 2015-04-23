package com.instinctools.analyzer.component;

import com.instinctools.analyzer.component.state.ProjectComponentState;
import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.marker.impl.SecurityMarkerImpl;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
@State(name = "AnalyzerProjectComponent", storages = @Storage(id = "AnalyzerProjectComponent", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/analyzer_module.xml", scheme = StorageScheme.DIRECTORY_BASED))
public class AnalyzerProjectComponent implements ProjectComponent, PersistentStateComponent<ProjectComponentState> {

    private ProjectComponentState componentState;
    private Project project;

    public AnalyzerProjectComponent(Project project) {
        this.project = project;
        this.componentState = new ProjectComponentState();
    }

    public Project getProject() {
        return project;
    }

    public void initComponent() {

        PsiManager psiManager = PsiManager.getInstance(project);
        psiManager.addPsiTreeChangeListener(new AnalyzerPsiTreeChangeListener(this));
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "AnalyzerProjectComponent";
    }

    public void addMarker(SecurityMarker marker) {
        componentState.addMarker((SecurityMarkerImpl) marker);
    }

    public void removeMarker(SecurityMarker marker) {

        List<SecurityMarkerImpl> markers = componentState.getMarkers();

        int count = markers.size();

        markers.remove(marker);

        if (count - markers.size() != 1) {
            System.out.println("WARNING FOR REMOVE MARKER");
        }
    }

    public boolean hasMarkersFor(PsiFile file) {

        VirtualFile virtualFile = file.getVirtualFile();
        List<SecurityMarker> markers = getMarkers();

        if (markers.isEmpty()) {
            return false;
        }

        for (SecurityMarker marker : componentState.getMarkers()) {
            if (virtualFile.equals(marker.getFile())) {
                return true;
            }
        }

        return false;
    }

    @Nullable
    @Override
    public ProjectComponentState getState() {
        return componentState;
    }

    @Override
    public void loadState(ProjectComponentState componentState) {

        for (SecurityMarker marker : componentState.getMarkers()) {
            marker.init();
        }

        this.componentState = componentState;
    }

    public List<SecurityMarker> getMarkers() {

        List<SecurityMarker> result = new ArrayList<SecurityMarker>();

        for (SecurityMarker marker : componentState.getMarkers()) {
            result.add(marker);
        }

        return result;
    }

    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {

    }
}
