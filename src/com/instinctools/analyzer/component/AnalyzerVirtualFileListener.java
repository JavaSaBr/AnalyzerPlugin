package com.instinctools.analyzer.component;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.util.Utils;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileEvent;

import java.util.List;

/**
 * Created by ronn on 24.04.15.
 */
public class AnalyzerVirtualFileListener extends VirtualFileAdapter {

    private final AnalyzerProjectComponent projectComponent;

    public AnalyzerVirtualFileListener(AnalyzerProjectComponent projectComponent) {
        this.projectComponent = projectComponent;
    }

    @Override
    public void fileDeleted(VirtualFileEvent event) {

        VirtualFile deletedFile = event.getFile();

        List<SecurityMarker> markers = projectComponent.getMarkers();

        for (SecurityMarker marker : markers) {

            VirtualFile markerFile = marker.getFile();

            if (deletedFile.equals(markerFile)) {
                projectComponent.removeMarker(marker);
            }
        }

        Utils.updateEditors(projectComponent.getProject(), false);
    }
}
