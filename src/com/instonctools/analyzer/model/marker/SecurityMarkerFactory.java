package com.instonctools.analyzer.model.marker;

import com.instonctools.analyzer.model.marker.impl.SecurityMarkerImpl;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public class SecurityMarkerFactory {

    public static SecurityMarker create() {
        return new SecurityMarkerImpl();
    }
}
