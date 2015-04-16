package com.instinctools.analyzer.test;

import com.instonctools.analyzer.builder.BuilderFactory;
import com.instonctools.analyzer.builder.RuleBuilder;
import com.instonctools.analyzer.builder.xml.impl.XmlURLRuleSource;
import org.junit.Test;

import java.net.URL;

/**
 * Created by ronn on 09.04.15.
 */
public class TestParseRules {

    @Test
    public void testParseRules() {

        URL resource = Thread.class.getResource("/testRule.xml");

        try {

            XmlURLRuleSource fileRuleSource = new XmlURLRuleSource(resource);

            RuleBuilder builder = BuilderFactory.getRuleBuilderFor(fileRuleSource);
            builder.build(fileRuleSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
