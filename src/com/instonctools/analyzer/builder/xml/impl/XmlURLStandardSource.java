package com.instonctools.analyzer.builder.xml.impl;

import com.instonctools.analyzer.builder.xml.XmlStandardSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class XmlURLStandardSource implements XmlStandardSource {

    private final URL url;

    public XmlURLStandardSource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getStream() throws IOException {
        return url.openStream();
    }
}
