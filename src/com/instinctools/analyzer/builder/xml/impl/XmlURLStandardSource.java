package com.instinctools.analyzer.builder.xml.impl;

import com.instinctools.analyzer.builder.xml.XmlStandardSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class XmlURLStandardSource implements XmlStandardSource {

    private final URL url;

    public XmlURLStandardSource(final URL url) {
        this.url = url;
    }

    @Override
    public InputStream getStream() throws IOException {
        return url.openStream();
    }
}
