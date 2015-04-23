package com.instinctools.analyzer.model.standard;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by ronn on 16.04.15.
 * Documentation follows here.
 */
public class StandartUtils {

    public static String getHtmlContent(final Standard standard) {

        final InputStream resource = StandartUtils.class.getResourceAsStream("/transform/standard_stylesheet.xsl");

        final StringWriter writer = new StringWriter();

        try {

            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final Transformer transformer = transformerFactory.newTransformer(new StreamSource(resource));

            final StreamResult result = new StreamResult(writer);
            transformer.transform(new StreamSource(new StringReader(standard.getContent())), result);

        } catch (final TransformerException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}
