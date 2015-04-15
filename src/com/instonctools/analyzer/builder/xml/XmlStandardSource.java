package com.instonctools.analyzer.builder.xml;

import com.instonctools.analyzer.builder.StandardSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ronn on 09.04.15.
 */
public interface XmlStandardSource extends StandardSource {

    public InputStream getStream() throws IOException;
}
