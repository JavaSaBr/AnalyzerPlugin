package com.instonctools.analyzer.builder.xml;

import com.instonctools.analyzer.builder.RuleSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface XmlRuleSource extends RuleSource {

    InputStream getStream() throws IOException;
}
