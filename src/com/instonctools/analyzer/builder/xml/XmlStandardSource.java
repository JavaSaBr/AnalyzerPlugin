package com.instonctools.analyzer.builder.xml;

import com.instonctools.analyzer.builder.StandardSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface XmlStandardSource extends StandardSource {

    InputStream getStream() throws IOException;
}
