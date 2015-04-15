package com.instonctools.analyzer.builder.xml.impl;

import com.instonctools.analyzer.builder.xml.XmlStandardSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ronn on 09.04.15.
 */
public class XmlFileStandardSource implements XmlStandardSource {

    private final File file;

    public XmlFileStandardSource(File file) {
        this.file = file;
    }

    @Override
    public InputStream getStream() throws IOException {
        return new FileInputStream(file);
    }
}
