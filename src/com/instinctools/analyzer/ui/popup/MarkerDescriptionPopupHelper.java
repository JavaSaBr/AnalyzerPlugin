package com.instinctools.analyzer.ui.popup;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import java.awt.*;

/**
 * Created by ronn on 15.04.15.
 * Documentation follows here.
 */
public class MarkerDescriptionPopupHelper {

    public static void show(final SecurityMarker marker) {

        final JBPopupFactory popupFactory = JBPopupFactory.getInstance();

        final ComponentPopupBuilder builder = popupFactory.createComponentPopupBuilder(new MarkerDescriptionPopup(marker), null);
        builder.setTitle("Marker description");
        builder.setMinSize(new Dimension(600, 400));
        builder.setResizable(true);

        final JBPopup popup = builder.createPopup();
        popup.showInFocusCenter();
    }
}
