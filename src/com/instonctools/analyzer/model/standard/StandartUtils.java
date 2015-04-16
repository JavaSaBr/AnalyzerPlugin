package com.instonctools.analyzer.model.standard;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

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
 */
public class StandartUtils {

    public static String getHtmlContent(Standard standard) {

        InputStream resource = StandartUtils.class.getResourceAsStream("/transform/standard_stylesheet.xsl");

        StringWriter writer = new StringWriter();

        try {

            TransformerFactory transformerFactory = TransformerFactoryImpl.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(resource));

            StreamResult result = new StreamResult(writer);
            transformer.transform(new StreamSource(new StringReader(standard.getContent())), result);

        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }
}
