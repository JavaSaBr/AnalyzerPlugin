package com.instonctools.analyzer.builder.xml.impl;

import com.instonctools.analyzer.builder.xml.XmlRuleSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ronn on 09.04.15.
 */
public class XmlFileRuleSource implements XmlRuleSource {

    private final Path file;

    public XmlFileRuleSource(File file) {
        this.file = Paths.get(file.toURI());
    }

    public XmlFileRuleSource(Path file) {
        this.file = file;
    }

    @Override
    public InputStream getStream() throws IOException {
        return Files.newInputStream(file);
    }
}
