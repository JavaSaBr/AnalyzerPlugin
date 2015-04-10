package com.instonctools.analyzer.builder;

import com.instonctools.analyzer.builder.xml.XmlRuleSource;
import com.instonctools.analyzer.builder.xml.impl.XmlRuleBuilder;

/**
 * Created by ronn on 09.04.15.
 */
public class RuleBuilderFactory {

    public static RuleBuilder getBuilderFor(RuleSource source) {

        if (source instanceof XmlRuleSource) {
            return new XmlRuleBuilder();
        }

        throw new IllegalArgumentException("unknown rule source");
    }
}
