package com.instonctools.analyzer.component;

import com.instonctools.analyzer.component.state.ProjectComponentState;
import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.model.marker.impl.SecurityMarkerImpl;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronn on 10.04.15.
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
        // TODO: insert component initialization logic here
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
        markers.remove(marker);
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
