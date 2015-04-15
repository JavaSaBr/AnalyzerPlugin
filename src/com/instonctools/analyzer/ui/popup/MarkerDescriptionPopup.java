package com.instonctools.analyzer.ui.popup;

import com.instonctools.analyzer.model.marker.SecurityMarker;
import com.instonctools.analyzer.model.rule.Rule;
import com.instonctools.analyzer.model.standard.Standard;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by ronn on 15.04.15.
 */
public class MarkerDescriptionPopup extends JPanel {

    public MarkerDescriptionPopup(SecurityMarker marker) {
        super(new BorderLayout());

        setMaximumSize(new Dimension(800, 800));
        Rule rule = marker.getRule();
        List<Standard> standards = rule.getStandards();
        Standard standard = standards.get(0);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/plain");
        editorPane.setText(standard.getLongDescription());
        editorPane.setEditable(false);

        final JScrollPane scrollPane = new JBScrollPane(null);
        scrollPane.setViewportView(editorPane);

        add(scrollPane, BorderLayout.CENTER);
    }
}
