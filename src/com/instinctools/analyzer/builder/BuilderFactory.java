package com.instinctools.analyzer.builder;

import com.instinctools.analyzer.builder.xml.XmlRuleSource;
import com.instinctools.analyzer.builder.xml.XmlStandardSource;
import com.instinctools.analyzer.builder.xml.impl.XmlRuleBuilder;
import com.instinctools.analyzer.builder.xml.impl.XmlStandardBuilder;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class BuilderFactory {

    public static RuleBuilder getRuleBuilderFor(RuleSource source) {

        if (source instanceof XmlRuleSource) {
            return new XmlRuleBuilder();
        }

        throw new IllegalArgumentException("unknown rule source");
    }

    public static StandardBuilder getStandardBuilderFor(StandardSource source) {

        if (source instanceof XmlStandardSource) {
            return new XmlStandardBuilder();
        }

        throw new IllegalArgumentException("unknown standard source");
    }
}
