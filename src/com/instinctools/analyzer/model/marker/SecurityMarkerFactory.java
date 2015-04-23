package com.instinctools.analyzer.model.marker;

import com.instinctools.analyzer.model.marker.impl.SecurityMarkerImpl;

/**
 * Created by ronn on 13.04.15.
 * Documentation follows here.
 */
public class SecurityMarkerFactory {

    public static SecurityMarker create() {
        return new SecurityMarkerImpl();
    }
}
