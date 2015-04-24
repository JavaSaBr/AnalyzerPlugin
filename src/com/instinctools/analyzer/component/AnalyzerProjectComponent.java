package com.instinctools.analyzer.component;

import com.instinctools.analyzer.component.state.ProjectComponentState;
import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.marker.impl.SecurityMarkerImpl;
import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * Documentation follows here.
 */
@State(name = "AnalyzerProjectComponent", storages = @Storage(id = "AnalyzerProjectComponent", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/analyzer_module.xml", scheme = StorageScheme.DIRECTORY_BASED))
public class AnalyzerProjectComponent implements ProjectComponent, PersistentStateComponent<ProjectComponentState> {

    private final Project project;
    private ProjectComponentState componentState;

    public AnalyzerProjectComponent(final Project project) {
        this.project = project;
        this.componentState = new ProjectComponentState();
    }

    public Project getProject() {
        return project;
    }

    public void initComponent() {

        final PsiManager psiManager = PsiManager.getInstance(project);
        psiManager.addPsiTreeChangeListener(new AnalyzerPsiTreeChangeListener(this));

        VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
        virtualFileManager.addVirtualFileListener(new AnalyzerVirtualFileListener(this));
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "AnalyzerProjectComponent";
    }

    public void addMarker(final SecurityMarker marker) {
        componentState.addMarker((SecurityMarkerImpl) marker);
    }

    public void removeMarker(final SecurityMarker marker) {

        final List<SecurityMarkerImpl> markers = componentState.getMarkers();

        synchronized (markers) {
            markers.remove(marker);
        }
    }

    public boolean hasMarkersFor(final PsiFile file) {

        final VirtualFile virtualFile = file.getVirtualFile();
        final List<SecurityMarker> markers = getMarkers();

        if (markers.isEmpty()) {
            return false;
        }

        for (final SecurityMarker marker : markers) {
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
    public void loadState(final ProjectComponentState componentState) {

        List<SecurityMarkerImpl> markers = componentState.getMarkers();

        for (Iterator<SecurityMarkerImpl> iterator = markers.iterator(); iterator.hasNext(); ) {

            SecurityMarkerImpl marker = iterator.next();
            marker.init();

            VirtualFile file = marker.getFile();

            if (file == null || !file.exists()) {
                iterator.remove();
            }
        }

        this.componentState = componentState;
    }

    public List<SecurityMarker> getMarkers() {

        final List<SecurityMarker> result = new ArrayList<SecurityMarker>();
        final List<SecurityMarkerImpl> markers = componentState.getMarkers();

        synchronized (markers) {
            for (final SecurityMarker marker : markers) {
                result.add(marker);
            }
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
