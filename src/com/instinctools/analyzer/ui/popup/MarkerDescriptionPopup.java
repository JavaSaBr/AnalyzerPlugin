package com.instinctools.analyzer.ui.popup;

import com.instinctools.analyzer.model.marker.SecurityMarker;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.model.standard.Standard;
import com.instinctools.analyzer.model.standard.StandartUtils;
import com.intellij.ui.components.JBScrollPane;
import org.xhtmlrenderer.simple.XHTMLPanel;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by ronn on 15.04.15.
 * //TODO need add documentation
 */
public class MarkerDescriptionPopup extends JPanel {

    public MarkerDescriptionPopup(SecurityMarker marker) {
        super(new BorderLayout());

        Rule rule = marker.getRule();
        List<Standard> standards = rule.getStandards();
        Standard standard = standards.get(0);

        String htmlContent = StandartUtils.getHtmlContent(standard);
        htmlContent = htmlContent.replace("xmlns=\"http://www.w3.org/1999/xhtml\"", "");
        htmlContent = htmlContent.replace("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">", "");

        XHTMLPanel xhtmlPanel = new XHTMLPanel();

        try {
            xhtmlPanel.setDocument(new ByteArrayInputStream(htmlContent.getBytes("UTF-8")), "olool");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final JScrollPane scrollPane = new JBScrollPane(null);
        scrollPane.setViewportView(xhtmlPanel);

        add(scrollPane, BorderLayout.CENTER);
    }
}
