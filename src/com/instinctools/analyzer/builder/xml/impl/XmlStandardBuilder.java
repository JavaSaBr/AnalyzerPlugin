package com.instinctools.analyzer.builder.xml.impl;

import com.instinctools.analyzer.builder.StandardBuilder;
import com.instinctools.analyzer.builder.StandardSource;
import com.instinctools.analyzer.builder.xml.XmlStandardSource;
import com.instinctools.analyzer.model.standard.MutableStandard;
import com.instinctools.analyzer.model.standard.Standard;
import com.instinctools.analyzer.model.standard.StandardFactory;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class XmlStandardBuilder implements StandardBuilder {

    public static final String NODE_TITLE = "Title";
    public static final String NODE_SHORT_DESCRIPTION = "ShortDescription";
    public static final String NODE_LONG_DESCRIPTION = "LongDescription";

    @Override
    public Standard build(StandardSource source) {

        if (!(source instanceof XmlStandardSource)) {
            throw new IllegalArgumentException("this is builder only for " + XmlStandardSource.class.getName());
        }

        Standard result = null;
        String content = "";
        Document document = null;

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(((XmlStandardSource) source).getStream());

            content = IOUtils.toString(((XmlStandardSource) source).getStream());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Node node = document.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !"Standard".equals(node.getNodeName())) {
                continue;
            }

            MutableStandard standard = parseStandart((Element) node);
            standard.setContent(content);

            result = standard;
            break;
        }

        return result;
    }

    private MutableStandard parseStandart(Element parent) {

        MutableStandard standart = StandardFactory.create();

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;

            if (NODE_TITLE.equals(element.getNodeName())) {
                standart.setTitle(parseContent(element));
            } else if (NODE_SHORT_DESCRIPTION.equals(element.getNodeName())) {
                standart.setShortDescription(parseContent(element));
            } else if (NODE_LONG_DESCRIPTION.equals(element.getNodeName())) {
                standart.setLongDescription(parseContent(element));
            }
        }

        return standart;
    }

    private String parseContent(Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();
        textContent = textContent.replace('\t', ' ');
        textContent = textContent.replace('\n', ' ');
        textContent = textContent.replace("  ", " ");

        return textContent;
    }
}
