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
 * Documentation follows here.
 */
public class MarkerDescriptionPopup extends JPanel {

    private static final long serialVersionUID = 2585048217569445023L;

    public MarkerDescriptionPopup(final SecurityMarker marker) {
        super(new BorderLayout());

        final Rule rule = marker.getRule();
        final List<Standard> standards = rule.getStandards();
        final Standard standard = standards.get(0);

        String htmlContent = StandartUtils.getHtmlContent(standard);
        htmlContent = htmlContent.replace("xmlns=\"http://www.w3.org/1999/xhtml\"", "");
        htmlContent = htmlContent.replace("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">", "");

        final XHTMLPanel xhtmlPanel = new XHTMLPanel();

        try {
            xhtmlPanel.setDocument(new ByteArrayInputStream(htmlContent.getBytes("UTF-8")), "olool");
        } catch (final Exception e) {
            e.printStackTrace();
        }

        final JScrollPane scrollPane = new JBScrollPane(null);
        scrollPane.setViewportView(xhtmlPanel);

        add(scrollPane, BorderLayout.CENTER);
    }
}
