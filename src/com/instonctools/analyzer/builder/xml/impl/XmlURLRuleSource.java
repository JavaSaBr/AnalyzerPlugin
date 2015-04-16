package com.instonctools.analyzer.builder.xml.impl;

import com.instonctools.analyzer.builder.xml.XmlRuleSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class XmlURLRuleSource implements XmlRuleSource {

    private final URL url;

    public XmlURLRuleSource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getStream() throws IOException {
        return url.openStream();
    }
}
