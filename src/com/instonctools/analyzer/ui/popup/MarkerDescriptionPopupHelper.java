package com.instonctools.analyzer.ui.popup;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import java.awt.*;

/**
 * Created by ronn on 15.04.15.
 * //TODO need add documentation
 */
public class MarkerDescriptionPopupHelper {

    public static void show(SecurityMarker marker) {

        JBPopupFactory popupFactory = JBPopupFactory.getInstance();

        ComponentPopupBuilder builder = popupFactory.createComponentPopupBuilder(new MarkerDescriptionPopup(marker), null);
        builder.setTitle("Marker description");
        builder.setMinSize(new Dimension(600, 400));
        builder.setResizable(true);

        JBPopup popup = builder.createPopup();
        popup.showInFocusCenter();
    }
}
