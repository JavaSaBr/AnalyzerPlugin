package com.instinctools.analyzer.builder.xml;

import com.instinctools.analyzer.builder.StandardSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public interface XmlStandardSource extends StandardSource {

    InputStream getStream() throws IOException;
}
