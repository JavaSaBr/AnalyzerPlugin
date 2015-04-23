package com.instinctools.analyzer.component.state;

import com.instinctools.analyzer.model.marker.impl.SecurityMarkerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public class ProjectComponentState {

    @com.intellij.util.xmlb.annotations.AbstractCollection
    public List<SecurityMarkerImpl> markers;

    public ProjectComponentState() {
        this.markers = new ArrayList<SecurityMarkerImpl>();
    }

    public List<SecurityMarkerImpl> getMarkers() {
        return markers;
    }

    public void addMarker(SecurityMarkerImpl marker) {
        synchronized (markers) {
            markers.add(marker);
        }
    }

    public void clear() {
        synchronized (markers) {
            markers.clear();
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectComponentState that = (ProjectComponentState) o;

        return !(markers != null ? !markers.equals(that.markers) : that.markers != null);

    }

    @Override
    public int hashCode() {
        return markers != null ? markers.hashCode() : 0;
    }
}
